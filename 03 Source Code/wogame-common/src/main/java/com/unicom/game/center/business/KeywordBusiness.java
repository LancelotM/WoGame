package com.unicom.game.center.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.KeywordDao;
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

}
