package com.unicom.game.center.business;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.AdTrafficDao;
import com.unicom.game.center.db.domain.AdTrafficDomain;
import com.unicom.game.center.db.domain.ProductDomain;
import com.unicom.game.center.log.model.GameTraffic;
import com.unicom.game.center.model.GameDisplayModel;
import com.unicom.game.center.model.AdInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */
@Component
@Transactional
public class AdTrafficBusiness {

	@Autowired
	private AdTrafficDao gameTrafficDao;
	
	/**
	 * 
	 * @param channelId
	 * @param bannerFlag
	 * @return
	 */
	public List<AdInfo> fetchGameInfoByDate(Integer channelId, boolean bannerFlag){
		List<AdInfo>  gameList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -5);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			if(bannerFlag){
                gameList = gameTrafficDao.fetchBannerInfoByDate(startDate, endDate, channelId);
			}else{
                gameList = gameTrafficDao.fetchAdInfoByDate(startDate, endDate, channelId);
			}

		}catch(Exception ex){
			Logging.logError("Error occur in fetchGameInfoByDate.", ex);
		}
		
		return gameList;
	}

	/**
	 * 
	 * @param channelId
	 * @param bannerFlag
	 * @return
	 */
	public List<AdInfo> fetchGameInfoByMonth(Integer channelId, boolean bannerFlag){
		List<AdInfo>  gameList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -4);
			if(bannerFlag){
                gameList = gameTrafficDao.fetchBannerInfoByMonth(startDate, endDate, channelId);
			}else{
                gameList = gameTrafficDao.fetchAdInfoByMonth(startDate, endDate, channelId);

			}
            return gameList;
		}catch(Exception ex){
			Logging.logError("Error occur in fetchGameInfoByMonth.", ex);
		}
		
		return null;
	}

    public List<GameDisplayModel> getGameMonthModel(Integer channelId){
        List<AdInfo> gameInfos = fetchGameInfoByMonth(channelId,false);
        List<GameDisplayModel> gameDisplayModelList = getGameDisplayModel(gameInfos,"month");
        return gameDisplayModelList;
    }

    public List<GameDisplayModel> getGameDayModel(Integer channelId){
        List<GameDisplayModel> gameDisplayModelList = getGameDisplayModel(fetchGameInfoByDate(channelId, false),"day");
        return gameDisplayModelList;
    }

    public List<List<AdInfo>> getBannerDateModel(Integer channelId,int type){
        TreeMap<String,List<AdInfo>> map = null;
        if(type == 1){
            map = getBannerDisplayModel(fetchGameInfoByDate(channelId,true));
        }else if(type == 2){
            map = getBannerDisplayModel(fetchGameInfoByMonth(channelId, true));
        }
        List<List<AdInfo>> gameByDate = new ArrayList<List<AdInfo>>();
        List<AdInfo> games = null;
        for(String key : map.keySet()){
            games = map.get(key);
            gameByDate.add(games);
        }
        return gameByDate;
    }

    public List<GameDisplayModel> getGameDisplayModel(List<AdInfo> gameInfos,String dateType){
        Map<String,List<AdInfo>>  data = new HashMap<String, List<AdInfo>>();
        GameDisplayModel gameDisplayModel = null;
        List<String> dateList = new ArrayList<String>();
        for(int i = 0;i<5;i++){
            if("month".equals(dateType)){
                dateList.add(DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(), -(i + 1)), "yy-MM"));
            }else if("day".equals(dateType)){
                dateList.add(DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(),-(i+1)),"MM-dd"));
            }
        }
        List<GameDisplayModel> gameDisplayModels = new ArrayList<GameDisplayModel>();
        if(gameInfos != null && gameInfos.size() > 0){
            for(AdInfo gameInfo : gameInfos){
                getMap(data,gameInfo.getAdId(),gameInfo);
            }
        }
        List<List<AdInfo>> dataList = new ArrayList<List<AdInfo>>();
        for(String name : data.keySet()){
            List<AdInfo> list = new ArrayList<AdInfo>();
            if(data.get(name) != null && data.get(name).size()>0){
                for(AdInfo gameInfo : data.get(name)){
                    list.add(gameInfo);
                }
            }
            Map<String,List<AdInfo>> dateKeyGameinfo = new HashMap<String, List<AdInfo>>();
            for(AdInfo game : data.get(name)){
                getMap(dateKeyGameinfo,game.getDate(),game);
            }
            for(String dateStr : dateList){
                if(!dateKeyGameinfo.keySet().contains(dateStr)){
                    AdInfo gameModel = new AdInfo();
                    gameModel.setClickThrough("0");
                    gameModel.setAdId(name);
                    gameModel.setDate(dateStr);
                    list.add(gameModel);
                }
            }
            sortList(list);
            dataList.add(list);
        }

        for(List<AdInfo> games : dataList){
            gameDisplayModel = new GameDisplayModel();
            gameDisplayModel.setGameName(games.get(0).getAdId());
            gameDisplayModel.setThisTimeData(games.get(0).getClickThrough());
            gameDisplayModel.setLastTimeData(games.get(1).getClickThrough());
            gameDisplayModel.setLast2TimeData(games.get(2).getClickThrough());
            gameDisplayModel.setLast3TimeData(games.get(3).getClickThrough());
            gameDisplayModel.setLast4TimeData(games.get(4).getClickThrough());
            gameDisplayModels.add(gameDisplayModel);

        }
        return gameDisplayModels;
    }

    public TreeMap<String,List<AdInfo>> getBannerDisplayModel(List<AdInfo> gameInfos){
        TreeMap<String,List<AdInfo>>  data = new TreeMap<String, List<AdInfo>>(new Comparator() {
            Collator collator = Collator.getInstance();

            public int compare(Object o1, Object o2) {
                //如果有空值，直接返回0
                if (o1 == null || o2 == null)
                    return 0;
                CollationKey key1 = collator.getCollationKey(o1.toString());
                CollationKey key2 = collator.getCollationKey(o2.toString());
                return String.valueOf(o2).compareTo(String.valueOf(o1));
            }
        });
        if(gameInfos != null && gameInfos.size() > 0){
            for(AdInfo gameInfo : gameInfos){
                getMap(data,gameInfo.getDate(),gameInfo);
            }
        }
        return data;
    }

    public void getMap(Map<String,List<AdInfo>> map,String key,AdInfo value){
        for(String name : map.keySet()){
            if(key.equals(name)){
               map.get(name).add(value);
                return;
            }
        }
        List<AdInfo> gameInfos = new ArrayList<AdInfo>();
        gameInfos.add(value);
        map.put(key,gameInfos);
    }

    private void sortList(List<AdInfo> list){
        if(list != null && list.size() >0){
            Collections.sort(list,new Comparator<AdInfo>() {
                @Override
                public int compare(AdInfo obj1, AdInfo obj2) {
                    return obj2.getDate().compareTo(obj1.getDate());  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
        }
    }

    public void typeConversion(HashMap<Integer,GameTraffic> gameTrafficHashMap){
        List<AdTrafficDomain> list = new ArrayList<AdTrafficDomain>();
        Iterator iterator = gameTrafficHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            ProductDomain productDomain = new ProductDomain();
            AdTrafficDomain gameTrafficDomain = new AdTrafficDomain();
            Map.Entry<Integer, GameTraffic> entry = (Map.Entry)iterator.next();
            GameTraffic gameTraffic = entry.getValue();
            productDomain.setProductId(gameTraffic.getProduct().getProduct_id());
            productDomain.setProductIcon(gameTraffic.getProduct().getProduct_icon());
            productDomain.setProductName(gameTraffic.getProduct().getProduct_name());
            productDomain.setDateCreated(gameTraffic.getProduct().getDateCreated());
            gameTrafficDomain.setSort(gameTraffic.getSort());
            gameTrafficDomain.setClickThrough(gameTraffic.getClick_through());
//            gameTrafficDomain.setDownloadCount(gameTraffic.getDownload_count());
//            gameTrafficDomain.setDateCreated(gameTraffic.getDateCreated());
//            gameTrafficDomain.setFlag(gameTraffic.isBanner_flag());
//            gameTrafficDomain.setChannelId(gameTraffic.getChannel_id());
//            gameTrafficDomain.setProduct(productDomain);
            list.add(gameTrafficDomain);
        }
        gameTrafficDao.saveAdTrafficDomainList(list,100);
    }


	
}
