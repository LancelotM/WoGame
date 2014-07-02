package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.GameTrafficDao;
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
			String startDate = DateUtils.getMonthFirstByInterval(today, -5);
			gameList = gameTrafficDao.fetchGameInfoByMonth(startDate, endDate, bannerFlag, channelId);
			if(null != gameList && !gameList.isEmpty()){
				//avoid analyze log job not running, user can't check the log
				Date beforeYesterday = DateUtils.getDayByInterval(today, -2);
				endDate = DateUtils.formatDateToString(beforeYesterday, "yyyy-MM-dd");
				startDate = DateUtils.getMonthFirstByInterval(yesterday, -5);
				gameList = gameTrafficDao.fetchGameInfoByMonth(startDate, endDate, bannerFlag, channelId);			
			}
            return gameList;
		}catch(Exception ex){
			Logging.logError("Error occur in fetchGameInfoByMonth.", ex);
		}
		
		return null;
	}

    public List<GameDisplayModel> getGameMonthModel(Integer channelId){
        return getGameDisplayModel(fetchGameInfoByMonth(channelId,false));
    }

    public List<GameDisplayModel> getGameDayModel(Integer channelId){
        return getGameDisplayModel(fetchGameInfoByDate(channelId, false));
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

    public List<GameDisplayModel> getGameDisplayModel(List<GameInfo> gameInfos){
        Map<String,List<GameInfo>>  data = new HashMap<String, List<GameInfo>>();
        GameDisplayModel gameDisplayModel = null;
        List<GameDisplayModel> gameDisplayModels = new ArrayList<GameDisplayModel>();
        if(gameInfos != null && gameInfos.size() > 0){
            for(GameInfo gameInfo : gameInfos){
                getMap(data,gameInfo.getName(),gameInfo);
            }
        }

        for(String name : data.keySet()){
            gameDisplayModel = new GameDisplayModel();
            gameDisplayModel.setGameName(data.get(name).get(0).getName());
            gameDisplayModel.setIcon(data.get(name).get(0).getIcon());
            gameDisplayModel.setThisTimeData(data.get(name).get(0).getClickThrough()+"|"+data.get(name).get(0).getDownloadCount());
            gameDisplayModel.setLastTimeData(data.get(name).get(1).getClickThrough()+"|"+data.get(name).get(1).getDownloadCount());
            gameDisplayModel.setLast2TimeData(data.get(name).get(2).getClickThrough()+"|"+data.get(name).get(2).getDownloadCount());
            gameDisplayModel.setLast3TimeData(data.get(name).get(3).getClickThrough()+"|"+data.get(name).get(3).getDownloadCount());
            gameDisplayModel.setLast4TimeData(data.get(name).get(4).getClickThrough()+"|"+data.get(name).get(4).getDownloadCount());
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
	
}
