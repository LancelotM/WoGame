package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.unicom.game.center.utils.Constant;
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
	
	public List<KeywordInfo> fetchTopSearchKeyword(){
		List<KeywordInfo> keywords = null;
		
		try{
			keywords = keywordDao.getTop50Keyword();
		}catch(Exception ex){
			Logging.logError("Error occur in fetchTopKeyword", ex);
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
            keywordDomain.setCount(keyWord.getCount());
            keywordDomain.setDateCreated(keyWord.getDateCreated());
            keywordDomain.setDateModified(keyWord.getDateModified());
            list.add(keywordDomain);
        }
        keywordDao.saveKeywordDomainList(list,Constant.HIBERNATE_FLUSH_NUM);
    }
    
    public KeywordDomain getKeyWord(String keyword){
      KeywordDomain keywordDomain = keywordDao.getByKeyWord(keyword);
      return keywordDomain;
  }    
}
