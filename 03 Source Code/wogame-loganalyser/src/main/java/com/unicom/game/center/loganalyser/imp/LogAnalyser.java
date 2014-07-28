package com.unicom.game.center.loganalyser.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.unicom.game.center.business.AdTrafficBusiness;
import com.unicom.game.center.business.KeywordBusiness;
import com.unicom.game.center.business.LoginInfoBusiness;
import com.unicom.game.center.business.PageTrafficBusiness;
import com.unicom.game.center.business.ProductBusiness;
import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.log.model.GameTraffic;
import com.unicom.game.center.log.model.KeyWord;
import com.unicom.game.center.log.model.PageTraffic;
import com.unicom.game.center.log.model.Product;
import com.unicom.game.center.log.model.UserCount;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;

@Component
public class LogAnalyser implements ILogAnalyser {
    @Autowired
    private LoginInfoBusiness userCountBusiness;
    @Autowired
    private PageTrafficBusiness pageTrafficBusiness;
    @Autowired
    private AdTrafficBusiness gameTrafficBusiness;
    @Autowired
    private KeywordBusiness keywordBusiness;
    @Autowired
    private ProductBusiness productBusiness;

    @Value("#{properties['log.file.path']}")
    private String logFilePath;

    @Value("#{properties['log.bak.path']}")
    private String logBakPath;

    @Override
    public void doDownloadCountDomainsSave() throws Exception{

    }

    @Override
    public void doReportDomainsSave() throws Exception{

    }
    
    @Override
    public void doPackageInfoDomainsSave() throws Exception {
    	
    }       
    
    @Override
    public void doLogAnalyse(){
        Logging.logDebug("----- doLogAnaylyse start -----");
        System.out.println("=====doLogAnaylyse start========");
        try{                   
            List<String> fileList = FileUtils.getFileList(logFilePath);
            System.out.println("Files size :" + fileList.size());            
            
            if(null != fileList && !fileList.isEmpty()){
                for(String fileName : fileList){
                	String fileDate = validateLogDate(fileName);
                	
                	if(null != fileDate){
                    	if(fileName.contains("number")){
                            doNumberLogAnalyse(fileName, fileDate);                		
                    	}else{
                            doInfoLogAnalyse(fileName, fileDate);                		
                    	}                 		
                	}
                }            	
            }
        }catch(Exception e){
            Logging.logError("Error occurs in doLogAnaylyse ", e);
            e.printStackTrace();
        }
        
        Logging.logDebug("----- doLogAnaylyse end -----");
        System.out.println("=====doLogAnaylyse end========");
    }
    
    /**
     * 
     * @param fileName
     * @return
     *  wogamecenter_info_number.2014-07-26.log			: 			2014-07-26
     *  wogamecenter_info_number.log					:			null
     */
    private String validateLogDate(String fileName){
    	String date = null;
        try {       
        	String[] nameInfos = (fileName.split("\\."));
        	if(3 == nameInfos.length){
        		date = nameInfos[1];
        	}
        } catch (Exception e) {
            Logging.logError("Error occurs in validateLogDate ", e);
        }

        return date;
    } 
    
    /**
     * parse number log file(wogamecenter_info_number.2014-07-26.log)
     * @param fileName
     * @param fileDate
     */
    private void doNumberLogAnalyse(String fileName, String fileDate){
        Map<String,Integer> numberCountMap = null;
        File file = null;
     
        try {
        	System.out.println("file name is : " + fileName);
        	file = new File(logFilePath+"/"+fileName);
            
            numberCountMap = woGameInfoNumberReader(file);
            
            Date date = DateUtils.stringToDate(fileDate, "yyyy-MM-dd");
            
            woGameInfoNumberParse(numberCountMap, date);
            
            File bakPath = new File(logBakPath);
            if(!bakPath.exists()){
                bakPath.mkdirs();
            }
            
          org.apache.commons.io.FileUtils.copyFileToDirectory(file, bakPath);
          file.getAbsoluteFile().delete();
        } catch (Exception e) {
            Logging.logError("Error occurs in doNumberLogAnalyse ", e);
            e.printStackTrace();
        }
    }
    
    /**
     * parse number log file(wogamecenter_info.2014-07-26.log)
     * @param fileName
     * @param fileDate
     */
    private void doInfoLogAnalyse(String fileName, String fileDate){
        File file = null;
        BufferedReader reader = null;
        
        Map<String,KeyWord> keyMapSave = new HashMap<String, KeyWord>();
        Map<String,KeyWord> keyMapUpdate = new HashMap<String, KeyWord>();
        Map<String,Product> productMap = new HashMap<String, Product>();
        
        String tempString = null;

        try {
        	System.out.println("file name is : " + fileName);
        	file = new File(logFilePath+"/"+fileName);        	
            
            reader = new BufferedReader(new UnicodeReader(new FileInputStream(file), "UTF-8"));
            
            Date date = DateUtils.stringToDate(fileDate, "yyyy-MM-dd");
        	
            while ((tempString = reader.readLine()) != null){
                woGameInfoParse(tempString,date,keyMapSave,keyMapUpdate,productMap);
            }
        	
            keywordBusiness.typeConversionSave(keyMapSave);
            if(keyMapUpdate.size()>=1){
                keywordBusiness.typeConversionUpdate(keyMapUpdate);
            }
            productBusiness.typeConversion(productMap);


        } catch (Exception e) {
            Logging.logError("Error occurs in doInfoLogAnalyse ", e);
            e.printStackTrace();
        } finally {
            keyMapSave.clear();
            keyMapUpdate.clear();
            productMap.clear();
            if(null != reader){
            	try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            
            File bakPath = new File(logBakPath);
            if(!bakPath.exists()){
                bakPath.mkdirs();
            }
            
            try{
                org.apache.commons.io.FileUtils.copyFileToDirectory(file, bakPath);
                file.getAbsoluteFile().delete();            	
            }catch(Exception ex){
            	ex.printStackTrace();
            }
           
        }
    }
    

 

    private void  keyWordDispose(String value, Map<String,KeyWord> keyMapSave, Map<String,KeyWord> keyMapUpdate){
        KeyWord keyWord = null;
        Date today = new Date();
        Date yesterday = DateUtils.getDayByInterval(today,-1);
        if(!value.substring(0,3).trim().equals("")){
            int channelId = Integer.parseInt(value.substring(0,3).trim());
            if(channelId != 0){
                String keywordValue = value.substring(3,value.length());
                KeywordDomain keywordDomain = keywordBusiness.getKeyWord(keywordValue,channelId);
                if (keywordDomain == null) {
                    if(keyMapSave.containsKey(value)){
                        keyWord = keyMapSave.get(value) ;
                        keyWord.setCount(keyWord.getCount() + 1);
                    }else{
                        keyWord = new KeyWord();
                        keyWord.setCount(1);
                    }
                    keyWord.setKeyword(keywordValue);
                    keyWord.setChannelId(channelId);
                    keyWord.setDateCreated(yesterday);
                    keyWord.setDateModified(today);
                    keyMapSave.put(value, keyWord);
                }else{
                    if(!keyMapUpdate.containsKey(value)){
                        keyWord = new KeyWord();
                        keyWord.setId(keywordDomain.getId());
                        keyWord.setKeyword(keywordValue);
                        keyWord.setChannelId(channelId);
                        keyWord.setCount(keywordDomain.getCount());
                        keyMapUpdate.put(value,keyWord);
                        keyWord = keyMapUpdate.get(value);
                    } else {
                        keyWord = keyMapUpdate.get(value) ;
                        keyWord.setId(keyWord.getId());
                    }
                    keyWord.setKeyword(keywordValue);
                    keyWord.setChannelId(channelId);
                    keyWord.setCount(keyWord.getCount() + 1);
                    keyWord.setDateCreated(yesterday);
                    keyWord.setDateModified(today);
                    keyMapUpdate.put(value, keyWord);
                }
            }
        }
    }


    private static Map<String,Integer> woGameInfoNumberReader(File file){
        String fileContent = "";
        String contentTemp = "";
        BufferedReader reader = null;
        Map<String,Integer> numberCountMap = new HashMap<String, Integer>();
        try {
            reader = new BufferedReader(new UnicodeReader(new FileInputStream(file), "UTF-8"));
            while ((contentTemp = reader.readLine()) !=  null){
                if(fileContent.equals("")){
                    fileContent = contentTemp;
                } else{
                    fileContent = fileContent + " " + contentTemp;
                }
            }
        } catch (IOException e) {
            Logging.logError("Error occurs in woGameInfoNumberReader", e);
        }finally{
        	if(null != reader){
        		try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        if(fileContent.equals("")){
            return numberCountMap;
        }else{
            StringTokenizer tokenizer = new StringTokenizer(fileContent);
            while(tokenizer.hasMoreTokens()){
                String splitWord = tokenizer.nextToken();
                if(numberCountMap.containsKey(splitWord)){
                    int count = numberCountMap.get(splitWord);
                    numberCountMap.put(splitWord,count+1);
                }else{
                    numberCountMap.put(splitWord,1);
                }
            }
        }
        return numberCountMap;
    }

    private void woGameInfoNumberParse(Map<String,Integer> numberCountMap,Date fileDate){
        Map<Integer,UserCount> userCountMap = new HashMap<Integer, UserCount>();
        Map<Integer,PageTraffic> pageTrafficMap = new HashMap<Integer, PageTraffic>();
        Map<Integer,GameTraffic> gameTrafficMap = new HashMap<Integer,GameTraffic>();
        UserCount userCount = null;
        PageTraffic pageTraffic = null;
        GameTraffic gameTraffic = null;
        Integer channelId = null;
        int id = 0;
        int clickThrough = 0;
        int adType = 0;
        int adId = 0;
        Iterator iterator = numberCountMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry =(Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            int val = Integer.parseInt(entry.getValue().toString());
            String firstTwoCharacters = key.substring(0,2);
            if(firstTwoCharacters.equalsIgnoreCase("80")||firstTwoCharacters.equalsIgnoreCase("81")){
                if(!key.substring(2,key.length()).trim().equals("")){
                    channelId = Integer.parseInt(key.substring(2,key.length()).trim());
                    if(channelId != 0){
                        if (userCountMap.containsKey(channelId)){
                            userCount = userCountMap.get(channelId);
                            if(userCount != null){
                                if(firstTwoCharacters.equalsIgnoreCase("80")){
                                    userCount.setNew_user_count(userCount.getNew_user_count() + val);
                                } else if(firstTwoCharacters.equalsIgnoreCase("81")){
                                    userCount.setOld_user_count(userCount.getOld_user_count() + val);
                                }
                            } else{
                                userCount = new UserCount();
                                if(firstTwoCharacters.equalsIgnoreCase("80")){
                                    userCount.setNew_user_count(val);
                                } else if(firstTwoCharacters.equalsIgnoreCase("81")){
                                    userCount.setOld_user_count(val);
                                }
                            }
                        } else {
                            userCount = new UserCount();
                            if(firstTwoCharacters.equalsIgnoreCase("80")){
                                userCount.setNew_user_count(val);
                            } else if(firstTwoCharacters.equalsIgnoreCase("81")){
                                userCount.setOld_user_count(val);
                            }
                        }
                        userCount.setChannelId(channelId);
                        userCount.setDateCreated(fileDate);
                        userCountMap.put(channelId,userCount);
                    }
                }
            } else if(firstTwoCharacters.equalsIgnoreCase("61")||firstTwoCharacters.equalsIgnoreCase("62")||firstTwoCharacters.equalsIgnoreCase("63")||firstTwoCharacters.equalsIgnoreCase("64")){
                if(!key.substring(2,key.length()).trim().equals("")){
                    channelId = Integer.parseInt(key.substring(2,key.length()).trim());
                    if(channelId != 0){
                        if (pageTrafficMap.containsKey(channelId)){
                            pageTraffic = pageTrafficMap.get(channelId);
                            if(pageTraffic != null){
                                if (firstTwoCharacters.equalsIgnoreCase("61")){
                                    pageTraffic.setHomepage(pageTraffic.getHomepage() + val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("62")){
                                    pageTraffic.setCategory(pageTraffic.getCategory() + val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("63")){
                                    pageTraffic.setHotlist(pageTraffic.getHotlist() + val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("64")){
                                    pageTraffic.setLatest(pageTraffic.getLatest() + val);
                                }
                            } else {
                                pageTraffic = new PageTraffic();
                                if (firstTwoCharacters.equalsIgnoreCase("61")){
                                    pageTraffic.setHomepage(val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("62")){
                                    pageTraffic.setCategory(val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("63")){
                                    pageTraffic.setHotlist(val);
                                } else if (firstTwoCharacters.equalsIgnoreCase("64")){
                                    pageTraffic.setLatest(val);
                                }
                            }
                        } else {
                            pageTraffic = new PageTraffic();
                            if (firstTwoCharacters.equalsIgnoreCase("61")){
                                pageTraffic.setHomepage(val);
                            } else if (firstTwoCharacters.equalsIgnoreCase("62")){
                                pageTraffic.setCategory(val);
                            } else if (firstTwoCharacters.equalsIgnoreCase("63")){
                                pageTraffic.setHotlist(val);
                            } else if (firstTwoCharacters.equalsIgnoreCase("64")){
                                pageTraffic.setLatest(val);
                            }
                        }
                        pageTraffic.setChannelId(channelId);
                        pageTraffic.setDateCreated(fileDate);
                        pageTrafficMap.put(channelId,pageTraffic);
                    }
                }
            }else if(firstTwoCharacters.equalsIgnoreCase("50")||firstTwoCharacters.equalsIgnoreCase("51")){
                if (firstTwoCharacters.equalsIgnoreCase("50")){
                    if(!key.substring(8,key.length()).trim().equals("")){
                        channelId = Integer.parseInt(key.substring(8,key.length()).trim().replaceAll("^(0+)", ""));
                        if(channelId != 0){
                            gameTraffic = new GameTraffic();
                            gameTraffic.setSort(Integer.parseInt(key.substring(6,8)));
                            gameTraffic.setAdType(Integer.parseInt(key.substring(4,6)));
                            gameTraffic.setAdId(Integer.parseInt(key.substring(2, 4)));
                            gameTraffic.setClickThrough(clickThrough);
                            gameTraffic.setChannelId(channelId);
                            gameTraffic.setDateCreated(fileDate);
                            adId = gameTraffic.getAdId();
                            adType = gameTraffic.getAdType();
                            gameTrafficMap.put(++id, gameTraffic);
                        }
                    }
                } else if(firstTwoCharacters.equalsIgnoreCase("51")){
                    if(!key.substring(4,key.length()).trim().equals("")){
                        channelId = Integer.parseInt(key.substring(4,key.length()).trim().replaceAll("^(0+)", ""));
                        if(channelId != 0){
                            if(!gameTrafficMap.containsKey(channelId)){
                                gameTraffic = new GameTraffic();
                                gameTraffic.setClickThrough(val);
                                clickThrough = gameTraffic.getClickThrough();
                            } else{
                                gameTraffic = new GameTraffic();
                                gameTraffic.setClickThrough(gameTraffic.getClickThrough() + val);
                                clickThrough = gameTraffic.getClickThrough();
                            }
                            gameTraffic.setSort(Integer.parseInt(key.substring(2,4)));
                            gameTraffic.setAdType(adType);
                            gameTraffic.setAdId(adId);
                            gameTraffic.setChannelId(channelId);
                            gameTraffic.setDateCreated(fileDate);
                            gameTrafficMap.put(++id,gameTraffic);
                        }
                    }
                }
            }
        }
        numberCountMap.clear();
        userCountBusiness.typeConversion(userCountMap);
        userCountMap.clear();
        pageTrafficBusiness.typeConversion(pageTrafficMap);
        pageTrafficMap.clear();
        gameTrafficBusiness.typeConversion(gameTrafficMap);
        gameTrafficMap.clear();
    }

    private void woGameInfoParse(String tempString, Date fileDate, Map<String,KeyWord> keyMapSave, Map<String,KeyWord> keyMapUpdate, Map<String,Product> productMap){
        try{
	    	Product product = null;
	        String surplus = null;
	        String firstTwoCharacters = tempString.substring(0, 2);
	        if(firstTwoCharacters.equalsIgnoreCase("40")){
	            surplus = tempString.substring(2,tempString.length());
	            if(Integer.parseInt(surplus.substring(0,3).trim()) != 0) {
	                keyWordDispose(surplus,keyMapSave,keyMapUpdate);
	            }
	        } else if(firstTwoCharacters.equalsIgnoreCase("30")){
	            String product_id = tempString.substring(5,15).trim();
	            String product_name = tempString.substring(15,257).trim();
	            String product_icon = tempString.substring(257,tempString.length()).trim();
	            surplus = tempString.substring(2,5) + product_name;
	            if(Integer.parseInt(surplus.substring(0,3).trim()) != 0) {
	                keyWordDispose(surplus,keyMapSave,keyMapUpdate);
	            }
	            boolean flag =  productBusiness.checkId(product_id);
	            
	
	                if(flag && Integer.parseInt(product_id) != 0){
	                    product = new Product();
	                    product.setProduct_id(product_id);
	                    product.setProduct_name(product_name);
	                    product.setProduct_icon(product_icon);
	                    product.setDateCreated(fileDate);
	                    productMap.put(product_id,product);
	                }            		
	        }        
        }catch(Exception e){
        	e.printStackTrace();
        	Logging.logError("Error occurs in parse product_id to Int ", e);
        }        
    }
}
