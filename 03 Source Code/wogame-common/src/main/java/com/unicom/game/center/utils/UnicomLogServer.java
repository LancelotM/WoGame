package com.unicom.game.center.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 *
 * @version Nov 6, 2014
 */
@Component
public class UnicomLogServer {

	@Value("#{properties['log.server.path']}")
	private String logUrl;
	
	@Value("#{properties['log.pageview.key']}")
	private String pageviewKey;
	
	@Autowired
	private HttpClientStack httpClient;
	
	/**
	 * 
	 * @param content 内容为 json字符串["\"date\":\"2014年8月11日15:07:45\""]
	 * @return
	 */
	public boolean pageviewLog(String content){
		boolean result = false;
		try {
			result = httpClient.setLogUrl(logUrl).setEventKey(pageviewKey).execute(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} 
}
