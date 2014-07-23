package com.unicom.game.center.business;

import java.util.*;

import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.KeywordDao;
import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.log.model.KeyWord;
import com.unicom.game.center.model.KeywordInfo;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-23
 */
@Component
@Transactional
public class KeywordBusiness {
	@Autowired
	private KeywordDao keywordDao;
	
	public List<KeywordInfo> fetchTopSearchKeyword(Integer channelId){
		List<KeywordInfo> keywords = null;
		
		try{
			keywords = keywordDao.getTop50Keyword(channelId);
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTopKeyword", ex);
            ex.printStackTrace();
        }
		return keywords;
	}

    public void typeConversionSave(Map<String,KeyWord> keyWordHashMap){
        List<KeywordDomain> list = new ArrayList<KeywordDomain>();
        Iterator iterator = keyWordHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            KeywordDomain keywordDomain = new KeywordDomain();
            Map.Entry<Integer, KeyWord> entry = (Map.Entry)iterator.next();
            KeyWord keyWord = entry.getValue();
            keywordDomain.setKeyword(keyWord.getKeyword());
            keywordDomain.setChannelId(keyWord.getChannelId());
            keywordDomain.setCount(keyWord.getCount());
            keywordDomain.setDateCreated(keyWord.getDateCreated());
            keywordDomain.setDateModified(keyWord.getDateModified());
            list.add(keywordDomain);
        }
        keywordDao.saveKeywordDomainList(list, Constant.HIBERNATE_FLUSH_NUM);
    }

    public void typeConversionUpdate(Map<String,KeyWord> keyWordHashMap){
        List<KeywordDomain> list = new ArrayList<KeywordDomain>();
        Iterator iterator = keyWordHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            KeywordDomain keywordDomain = new KeywordDomain();
            Map.Entry<Integer, KeyWord> entry = (Map.Entry)iterator.next();
            KeyWord keyWord = entry.getValue();
            keywordDomain.setId(keyWord.getId());
            keywordDomain.setKeyword(keyWord.getKeyword());
            keywordDomain.setChannelId(keyWord.getChannelId());
            keywordDomain.setCount(keyWord.getCount());
            keywordDomain.setDateCreated(keyWord.getDateCreated());
            keywordDomain.setDateModified(keyWord.getDateModified());
            list.add(keywordDomain);
        }
        keywordDao.saveKeywordDomainList(list,Constant.HIBERNATE_FLUSH_NUM);
    }
    
    public KeywordDomain getKeyWord(String keyword,int channelId){
      KeywordDomain keywordDomain = keywordDao.getByKeyWord(keyword,channelId);
      return keywordDomain;
    }

    public int getDayCount(Integer channelId,String date){
        return  keywordDao.getDayCount(date,channelId);
    }

    public int getThirtyDayCount(Integer channelId){
        String endDate = DateUtils.formatDateToString(new Date(),"yyyy-MM-dd");
        String startDate = DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(), -30), "yyyy-MM-dd");
        return keywordDao.getThirtyCount(startDate,endDate,channelId);
    }

}
