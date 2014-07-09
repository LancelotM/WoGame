package com.unicom.game.center.business;

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

import com.unicom.game.center.db.dao.GameTrafficDao;
import com.unicom.game.center.db.domain.GameTrafficDomain;
import com.unicom.game.center.db.domain.ProductDomain;
import com.unicom.game.center.log.model.GameTraffic;
import com.unicom.game.center.model.GameDisplayModel;
import com.unicom.game.center.model.GameInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date Jun 22, 2014
 */
@Component
@Transactional
public class GameTrafficBusiness {

	@Autowired
	private GameTrafficDao gameTrafficDao;
	
	/**
	 * 
	 * @param channelId
	 * @param bannerFlag
	 * @return
	 */
	public List<GameInfo> fetchGameInfoByDate(Integer channelId, boolean bannerFlag){
		List<GameInfo>  gameList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			Date previousDate = DateUtils.getDayByInterval(today, -5);
			String startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
			gameList = gameTrafficDao.fetchGameInfoByDate(startDate, endDate, bannerFlag, channelId);
			if(null != gameList && !gameList.isEmpty()){
				//avoid analyze log job not running, user can't check the log
				yesterday = DateUtils.getDayByInterval(today, -2);
				endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
				previousDate = DateUtils.getDayByInterval(today, -6);
				startDate = DateUtils.formatDateToString(previousDate, "yyyy-MM-dd");
				gameList = gameTrafficDao.fetchGameInfoByDate(startDate, endDate, bannerFlag, channelId);
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
	public List<GameInfo> fetchGameInfoByMonth(Integer channelId, boolean bannerFlag){
		List<GameInfo>  gameList = null;
		
		try{
			Date today = new Date();
			Date yesterday = DateUtils.getDayByInterval(today, -1);
			String endDate = DateUtils.formatDateToString(yesterday, "yyyy-MM-dd");
			String startDate = DateUtils.getMonthFirstByInterval(today, -4);
			gameList = gameTrafficDao.fetchGameInfoByMonth(startDate, endDate, bannerFlag, channelId);
			if(null != gameList && !gameList.isEmpty()){
				//avoid analyze log job not running, user can't check the log
				Date beforeYesterday = DateUtils.getDayByInterval(today, -2);
				endDate = DateUtils.formatDateToString(beforeYesterday, "yyyy-MM-dd");
				startDate = DateUtils.getMonthFirstByInterval(yesterday, -4);
				gameList = gameTrafficDao.fetchGameInfoByMonth(startDate, endDate, bannerFlag, channelId);			
			}
            return gameList;
		}catch(Exception ex){
			Logging.logError("Error occur in fetchGameInfoByMonth.", ex);
		}
		
		return null;
	}

    public List<GameDisplayModel> getGameMonthModel(Integer channelId,int page){
        List<GameInfo> gameInfos = fetchGameInfoByMonth(channelId,false);
        List<GameDisplayModel> gameDisplayModelList = getGameDisplayModel(gameInfos,"month");
        int rowPerPages = 10;
        int start = (page - 1) * rowPerPages;
        int end = start + rowPerPages;
        if(end > gameDisplayModelList.size()){
            end = gameDisplayModelList.size();
        }
        return gameDisplayModelList.subList(start,end);
    }

    public List<GameDisplayModel> getGameDayModel(Integer channelId,int page){
        List<GameDisplayModel> gameDisplayModelList = getGameDisplayModel(fetchGameInfoByDate(channelId, false),"day");
        int rowPerPages = 10;
        int start = (page - 1) * rowPerPages;
        int end = start + rowPerPages;
        if(end >= gameDisplayModelList.size()){
            end = gameDisplayModelList.size();
        }
        return gameDisplayModelList.subList(start,end);
    }

    public List<List<GameInfo>> getBannerDateModel(Integer channelId){
        Map<String,List<GameInfo>> map = getBannerDisplayModel(fetchGameInfoByDate(channelId,true));
        List<List<GameInfo>> gameByDate = new ArrayList<List<GameInfo>>();
        for(String key : map.keySet()){
            gameByDate.add(map.get(key));
        }
        return gameByDate;
    }

    public List<List<GameInfo>> getBannerMothModel(Integer channelId){
        TreeMap<String,List<GameInfo>> map = getBannerDisplayModel(fetchGameInfoByMonth(channelId, true));
        List<List<GameInfo>> gameByDate = new ArrayList<List<GameInfo>>();
        for(String key : map.keySet()){
            gameByDate.add(map.get(key));
        }
        return gameByDate;
    }

    public List<GameDisplayModel> getGameDisplayModel(List<GameInfo> gameInfos,String dateType){
        Map<String,List<GameInfo>>  data = new HashMap<String, List<GameInfo>>();
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
            for(GameInfo gameInfo : gameInfos){
                getMap(data,gameInfo.getName(),gameInfo);
            }
        }
        List<List<GameInfo>> dataList = new ArrayList<List<GameInfo>>();
        for(String name : data.keySet()){
            List<GameInfo> list = new ArrayList<GameInfo>();
            if(data.get(name) != null && data.get(name).size()>0){
                for(GameInfo gameInfo : data.get(name)){
                    list.add(gameInfo);
                }
            }
            Map<String,List<GameInfo>> dateKeyGameinfo = new HashMap<String, List<GameInfo>>();
            for(GameInfo game : data.get(name)){
                getMap(dateKeyGameinfo,game.getDate(),game);
            }
            for(String dateStr : dateList){
                if(!dateKeyGameinfo.keySet().contains(dateStr)){
                    GameInfo gameModel = new GameInfo();
                    gameModel.setDownloadCount("0");
                    gameModel.setClickThrough("0");
                    gameModel.setName(name);
                    gameModel.setDate(dateStr);
                    gameModel.setIcon(data.get(name).get(0).getIcon());
                    list.add(gameModel);
                }
            }
            sortList(list);
            dataList.add(list);
        }

        for(List<GameInfo> games : dataList){
            gameDisplayModel = new GameDisplayModel();
            gameDisplayModel.setGameName(games.get(0).getName());
            gameDisplayModel.setIcon(games.get(0).getIcon());
            gameDisplayModel.setThisTimeData(games.get(0).getClickThrough()+"|"+games.get(0).getDownloadCount());
            gameDisplayModel.setLastTimeData(games.get(1).getClickThrough()+"|"+games.get(1).getDownloadCount());
            gameDisplayModel.setLast2TimeData(games.get(2).getClickThrough()+"|"+games.get(2).getDownloadCount());
            gameDisplayModel.setLast3TimeData(games.get(3).getClickThrough()+"|"+games.get(3).getDownloadCount());
            gameDisplayModel.setLast4TimeData(games.get(4).getClickThrough()+"|"+games.get(4).getDownloadCount());
            gameDisplayModels.add(gameDisplayModel);

        }
        return gameDisplayModels;
    }

    public TreeMap<String,List<GameInfo>> getBannerDisplayModel(List<GameInfo> gameInfos){
        TreeMap<String,List<GameInfo>>  data = new TreeMap<String, List<GameInfo>>();
        if(gameInfos != null && gameInfos.size() > 0){
            for(GameInfo gameInfo : gameInfos){
                getMap(data,gameInfo.getDate(),gameInfo);
            }
        }
        return data;
    }

    public void getMap(Map<String,List<GameInfo>> map,String key,GameInfo value){
        for(String name : map.keySet()){
            if(key.equals(name)){
               map.get(name).add(value);
                return;
            }
        }
        List<GameInfo> gameInfos = new ArrayList<GameInfo>();
        gameInfos.add(value);
        map.put(key,gameInfos);
    }

    private void sortList(List<GameInfo> list){
        if(list != null && list.size() >0){
            Collections.sort(list,new Comparator<GameInfo>() {
                @Override
                public int compare(GameInfo obj1, GameInfo obj2) {
                    return obj2.getDate().compareTo(obj1.getDate());  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
        }
    }

    public void typeConversion(HashMap<Integer,GameTraffic> gameTrafficHashMap){
        List<GameTrafficDomain> list = new ArrayList<GameTrafficDomain>();
        Iterator iterator = gameTrafficHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            ProductDomain productDomain = new ProductDomain();
            GameTrafficDomain gameTrafficDomain = new GameTrafficDomain();
            Map.Entry<Integer, GameTraffic> entry = (Map.Entry)iterator.next();
            GameTraffic gameTraffic = entry.getValue();
            productDomain.setProductId(gameTraffic.getProduct().getProduct_id());
            productDomain.setProductIcon(gameTraffic.getProduct().getProduct_icon());
            productDomain.setProductName(gameTraffic.getProduct().getProduct_name());
            productDomain.setDateCreated(gameTraffic.getProduct().getDateCreated());
            gameTrafficDomain.setSort(gameTraffic.getSort());
            gameTrafficDomain.setClickThrough(gameTraffic.getClick_through());
            gameTrafficDomain.setDownloadCount(gameTraffic.getDownload_count());
            gameTrafficDomain.setDateCreated(gameTraffic.getDateCreated());
            gameTrafficDomain.setFlag(gameTraffic.isBanner_flag());
            gameTrafficDomain.setChannelId(gameTraffic.getChannel_id());
            gameTrafficDomain.setProduct(productDomain);
            list.add(gameTrafficDomain);
        }
        gameTrafficDao.saveGameTrafficDomainList(list,100);
    }


	
}
