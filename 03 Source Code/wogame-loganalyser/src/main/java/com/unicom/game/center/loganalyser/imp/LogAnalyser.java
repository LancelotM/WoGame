package com.unicom.game.center.loganalyser.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    private void compareWithKeyWord(Map<String,KeyWord> keyWordMap, Map<String,KeyWord> keyMapUpdate, Date fileDate){
        KeyWord keyWord = null;

        List<String> list = new ArrayList<String>();

        Iterator iterator = keyWordMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            String keywordValue = key.substring(3,key.length());
            int channelId = Integer.parseInt(key.substring(0,3).trim());
            KeywordDomain keywordDomain = keywordBusiness.getKeyWord(keywordValue,channelId);
            if(keywordDomain != null){
                keyWord = new KeyWord();
                keyWord.setId(keywordDomain.getId());
                keyWord.setChannelId(channelId);
                keyWord.setKeyword(keywordValue);
                keyWord.setCount(keywordDomain.getCount() + ((KeyWord) entry.getValue()).getCount());
                keyWord.setDateCreated(keywordDomain.getDateCreated());
                keyWord.setDateModified(fileDate);
                keyMapUpdate.put(key,keyWord);
                list.add(key);
            }
        }
        for(String mapKey: list){
            keyWordMap.remove(mapKey);
        }
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

        Map<String,KeyWord> keyMapUpdate = new HashMap<String, KeyWord>();
        Map<String,Product> productMap = new HashMap<String, Product>();
        Map<String,KeyWord> keyWordMap = new HashMap<String, KeyWord>();

        String tempString = null;

        try {
        	System.out.println("file name is : " + fileName);
        	file = new File(logFilePath+"/"+fileName);        	
            
            reader = new BufferedReader(new UnicodeReader(new FileInputStream(file), "UTF-8"));
            
            Date date = DateUtils.stringToDate(fileDate, "yyyy-MM-dd");
        	
            while ((tempString = reader.readLine()) != null){
                woGameInfoParse(tempString,date,keyWordMap,productMap);
            }

            compareWithKeyWord(keyWordMap,keyMapUpdate, date);

            try{
            	keywordBusiness.typeConversionSave(keyWordMap, false);	
            }catch(Exception e){
            	e.printStackTrace();
            	Logging.logError("Error occur in typeConversion.", e);
            	keywordBusiness.typeConversionSave(keyWordMap, true);
            }            
            
            if(keyMapUpdate.size()>=1){
                try{
                	keywordBusiness.typeConversionUpdate(keyMapUpdate, false);	
                }catch(Exception e){
                	e.printStackTrace();
                	Logging.logError("Error occur in typeConversion.", e);
                	keywordBusiness.typeConversionUpdate(keyMapUpdate, true);
                }             	
                
            }
            productBusiness.typeConversion(productMap);


        } catch (Exception e) {
            Logging.logError("Error occurs in doInfoLogAnalyse ", e);
            e.printStackTrace();
        } finally {
            keyWordMap.clear();
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


    private void  keyWordDispose(String value,Map<String,KeyWord> keyWordMap,  Date fileDate){
        KeyWord keyWord = null;
        if(!value.substring(0,3).trim().equals("")){
            int channelId = Integer.parseInt(value.substring(0,3).trim());
            if(channelId != 0){
                String keywordValue = value.substring(3,value.length());
                if(keyWordMap.containsKey(value)){
                    keyWord = keyWordMap.get(value) ;
                    keyWord.setCount(keyWord.getCount() + 1);
                }else{
                    keyWord = new KeyWord();
                    keyWord.setCount(1);
                }
                keyWord.setKeyword(keywordValue);
                keyWord.setChannelId(channelId);
                keyWord.setDateCreated(fileDate);
                keyWord.setDateModified(fileDate);
                keyWordMap.put(value, keyWord);
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
        String keyValueTemp = null;
        int id = 0;
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
                    keyValueTemp = key.substring(8,key.length()).trim().replaceAll("^(0+)", "");
                    if(!keyValueTemp.equals("")){
                        gameTraffic = new GameTraffic();
                        gameTraffic.setSort(Integer.parseInt(key.substring(6,8)));
                        gameTraffic.setAdType(Integer.parseInt(key.substring(4,6)));
                        gameTraffic.setAdId(Integer.parseInt(key.substring(2, 4)));
                        gameTraffic.setClickThrough(val);
                        gameTraffic.setChannelId(Integer.parseInt(keyValueTemp));
                        gameTraffic.setDateCreated(fileDate);
                        gameTrafficMap.put(++id, gameTraffic);
                    }
                } else if(firstTwoCharacters.equalsIgnoreCase("51")){
                    keyValueTemp = key.substring(4,key.length()).trim().replaceAll("^(0+)", "");
                    if(!keyValueTemp.equals("")){
                        gameTraffic = new GameTraffic();
                        gameTraffic.setClickThrough(val);
                        gameTraffic.setSort(Integer.parseInt(key.substring(2,4)));
                        gameTraffic.setAdType(0);
                        gameTraffic.setAdId(0);
                        gameTraffic.setChannelId(Integer.parseInt(keyValueTemp));
                        gameTraffic.setDateCreated(fileDate);
                        gameTrafficMap.put(++id,gameTraffic);
                    }
                }
            }
        }
        numberCountMap.clear();
        
        
        try{
        	userCountBusiness.typeConversion(userCountMap, false);
        }catch(Exception e){
        	e.printStackTrace();
        	Logging.logError("Error occur in typeConversion.", e);
        	userCountBusiness.typeConversion(userCountMap, true);
        }
        
        userCountMap.clear();
        
        try{
        	pageTrafficBusiness.typeConversion(pageTrafficMap, false);
        }catch(Exception e){
        	e.printStackTrace();
        	Logging.logError("Error occur in typeConversion.", e);
        	pageTrafficBusiness.typeConversion(pageTrafficMap, true);
        }        
        
        pageTrafficMap.clear();
        
        
        try{
        	gameTrafficBusiness.typeConversion(gameTrafficMap, false);
        }catch(Exception e){
        	e.printStackTrace();
        	Logging.logError("Error occur in typeConversion.", e);
        	gameTrafficBusiness.typeConversion(gameTrafficMap, true);
        }        
        
        gameTrafficMap.clear();
    }

    private void woGameInfoParse(String tempString, Date fileDate,Map<String,KeyWord> keyWordMap, Map<String,Product> productMap){
        try{
	    	Product product = null;
	        String surplus = null;
	        String firstTwoCharacters = tempString.substring(0, 2);
	        if(firstTwoCharacters.equalsIgnoreCase("40")){
	            surplus = tempString.substring(2,tempString.length());
	            if(Integer.parseInt(surplus.substring(0,3).trim()) != 0) {
                    keyWordDispose(surplus,keyWordMap, fileDate);
	            }
	        } else if(firstTwoCharacters.equalsIgnoreCase("30")){
	            String product_id = tempString.substring(5,15).trim();
	            String product_name = tempString.substring(15,257).trim();
	            String product_icon = tempString.substring(257,tempString.length()).trim();
                product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_icon(product_icon);
                product.setDateCreated(fileDate);
                productMap.put(product_id,product);
            }
        }catch(Exception e){
        	e.printStackTrace();
        	Logging.logError("Error occurs in parse product_id to Int ", e);
        }        
    }
}
