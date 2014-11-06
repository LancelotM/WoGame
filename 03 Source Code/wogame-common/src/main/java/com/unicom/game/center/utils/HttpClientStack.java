package com.unicom.game.center.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
public class HttpClientStack {
	
	private String logUrl;

	private String eventKey;
	
	public Boolean execute(String content) throws Exception{
		if(logUrl == null || logUrl.equals("") || eventKey == null || eventKey.equals("")){
			throw new Exception("Logserver url or eventKey is null");
		}
		CloseableHttpResponse response2 = null;
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
	
			HttpPost httpPost = new HttpPost(logUrl + eventKey);
			httpPost.addHeader("Content-Type", "UTF-8");
			HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
			httpPost.setEntity(entity);
			response2 = httpclient.execute(httpPost);

			if(response2.getStatusLine().getStatusCode() == 200){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
		    try {
				response2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public String getLogUrl() {
		return logUrl;
	}

	public HttpClientStack setLogUrl(String logUrl) {
		if(!logUrl.endsWith("/")){
			logUrl += "/";
		}
		this.logUrl = logUrl;
		return this;
	}

	public String getEventKey() {
		return eventKey;
	}

	public HttpClientStack setEventKey(String eventKey) {
		this.eventKey = eventKey;
		return this;
	}

}
