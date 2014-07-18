package com.unicom.game.center.loganalyser.imp;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.unicom.game.center.business.DownLoadInfoBusiness;
import com.unicom.game.center.business.AdTrafficBusiness;
import com.unicom.game.center.business.KeywordBusiness;
import com.unicom.game.center.business.LoginInfoBusiness;
import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.business.PageTrafficBusiness;
import com.unicom.game.center.business.ProductBusiness;
import com.unicom.game.center.db.domain.KeywordDomain;
import com.unicom.game.center.db.domain.PackageInfoDomain;
import com.unicom.game.center.log.model.DownLoadInfo;
import com.unicom.game.center.log.model.GameTraffic;
import com.unicom.game.center.log.model.KeyWord;
import com.unicom.game.center.log.model.PageTraffic;
import com.unicom.game.center.log.model.Product;
import com.unicom.game.center.log.model.UserCount;
import com.unicom.game.center.loganalyser.ILogAnalyser;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.FileUtils;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.SFTPHelper;
import com.unicom.game.center.utils.Utility;

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
    @Autowired
    private DownLoadInfoBusiness downLoadInfoBusiness;
    @Autowired
    private PackageInfoBusiness packageInfoBusiness;
    @Autowired
    private SFTPHelper sftpHelper;

    @Value("#{properties['response.file.path']}")
    private String responseFilePath;

    @Value("#{properties['latest.handdle.file']}")
    private String latestHanddleFile;

    @Value("#{properties['log.file.path']}")
    private String logFilePath;

    @Value("#{properties['latest.log.fileInfoNumber']}")
    private String logInfoNumberFile;

    @Value("#{properties['latest.log.fileInfo']}")
    private String logInfoFile;

    @Override
    public void doPackageReportDomainsSave() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void doDownloadCountDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    @Override
    public void doExtractReportDomainsSave() throws Exception{
        // TODO Auto-generated method stub
    }

    private void  keyWordDispose(String value, Map<String,KeyWord> keyMapSave, Map<String,KeyWord> keyMapUpdate){
        KeyWord keyWord = null;
        Date today = new Date();
        Date yesterday = DateUtils.getDayByInterval(today,-1);
        KeywordDomain keywordDomain = keywordBusiness.getKeyWord(value);
        if (keywordDomain == null) {
            if(keyMapSave.containsKey(value)){
                keyWord = keyMapSave.get(value) ;
                keyWord.setCount(keyWord.getCount() + 1);
            }else{
                keyWord = new KeyWord();
                keyWord.setCount(1);
            }
            keyWord.setKeyword(value);
            keyWord.setDateCreated(yesterday);
            keyWord.setDateModified(today);
            keyMapSave.put(value, keyWord);
        }else{
            if(!keyMapUpdate.containsKey(value)){
                keyWord = new KeyWord();
                keyWord.setId(keywordDomain.getId());
                keyWord.setKeyword(value);
                keyWord.setCount(keywordDomain.getCount());
                keyMapUpdate.put(value,keyWord);
                keyWord = keyMapUpdate.get(value);
            } else {
                keyWord = keyMapUpdate.get(value) ;
                keyWord.setId(keyWord.getId());
            }
            keyWord.setKeyword(value);
            keyWord.setCount(keyWord.getCount() + 1);
            keyWord.setDateCreated(yesterday);
            keyWord.setDateModified(today);
            keyMapUpdate.put(value, keyWord);
        }
    }


    private static Map woGameInfoNumberReader(File file){
        String fileContent = "";
        String contentTemp = "";
        BufferedReader reader = null;
        Map<String,Integer> numberCountMap = new HashMap<String, Integer>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((contentTemp = reader.readLine()) !=  null){
                if(fileContent.equals("")){
                    fileContent = contentTemp;
                } else{
                    fileContent = fileContent + " " + contentTemp;
                }
            }
        } catch (IOException e) {
            Logging.logError("Error occurs in woGameInfoNumberReader", e);
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
            int channelId = Integer.parseInt(key.substring(2,key.length()));
            if(firstTwoCharacters.equalsIgnoreCase("80")||firstTwoCharacters.equalsIgnoreCase("81")){
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
            } else if(firstTwoCharacters.equalsIgnoreCase("61")||firstTwoCharacters.equalsIgnoreCase("62")||firstTwoCharacters.equalsIgnoreCase("63")||firstTwoCharacters.equalsIgnoreCase("64")){
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
            }else if(firstTwoCharacters.equalsIgnoreCase("50")||firstTwoCharacters.equalsIgnoreCase("51")){
                if (firstTwoCharacters.equalsIgnoreCase("50")){
                    channelId = Integer.parseInt(key.substring(8,key.length()));
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
                } else if(firstTwoCharacters.equalsIgnoreCase("51")){
                    channelId = Integer.parseInt(key.substring(4,key.length()));
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
        numberCountMap.clear();
        userCountBusiness.typeConversion(userCountMap);
        userCountMap.clear();
        pageTrafficBusiness.typeConversion(pageTrafficMap);
        pageTrafficMap.clear();
        gameTrafficBusiness.typeConversion(gameTrafficMap);
        gameTrafficMap.clear();
    }

    private void woGameInfoParse(String tempString, Date fileDate, Map<String,KeyWord> keyMapSave, Map<String,KeyWord> keyMapUpdate, Map<String,Product> productMap,Map<String,DownLoadInfo> downLoadInfoMap){
        Product product = null;
        DownLoadInfo downLoadInfo = null;
        String firstTwoCharacters = tempString.substring(0, 2);
        if(firstTwoCharacters.equalsIgnoreCase("40")){
            String surplus = tempString.substring(2,tempString.length());
            keyWordDispose(surplus,keyMapSave,keyMapUpdate);
        } else if(firstTwoCharacters.equalsIgnoreCase("30")){
            String product_id = tempString.substring(5,15).trim();
            String channel_id = tempString.substring(2,5).trim();
            String product_name = tempString.substring(15,257).trim();
            String product_icon = tempString.substring(257,tempString.length()).trim();
            keyWordDispose(product_name,keyMapSave,keyMapUpdate);
            boolean flag =  productBusiness.checkId(product_id);
            if(flag){
                product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_icon(product_icon);
                product.setDateCreated(fileDate);
                productMap.put(product_id,product);
            }
            if(downLoadInfoMap.containsKey(channel_id)){
                downLoadInfo = downLoadInfoMap.get(channel_id);
                downLoadInfo.setDownload_count(downLoadInfo.getDownload_count() + 1);
            }else{
                downLoadInfo = new DownLoadInfo();
                downLoadInfo.setDownload_count(1);
            }
            downLoadInfo.setChannel_id(Integer.parseInt(channel_id));
            downLoadInfo.setProduct_id(product_id);
            downLoadInfo.setDateCreated(fileDate);
            downLoadInfoMap.put(channel_id, downLoadInfo);
        }
    }

    private void doLogAnalyse1(){
        Map<String,Integer> numberCountMap = null;
        String dateBefore = null;
        Date today = new Date();
        Date yesterday = DateUtils.getDayByInterval(today,-1);
        String fileDate = DateUtils.formatDateToString(yesterday,"yyyy-MM-dd");
        String currentFileName = "wogamecenter_info_number."+fileDate+".log";
        try {
            List<String> currentFileList = FileUtils.readFileByRow(logInfoNumberFile);
            if(currentFileList.size()>0){
                dateBefore = currentFileList.get(0);
            }else{
                dateBefore = new SimpleDateFormat("yyyyMMddHHmmss").format(DateUtils.getDayByInterval(today,-2));
            }
            String dateNow = new SimpleDateFormat("yyyyMMddHHmmss").format(yesterday);
            int compareDateNum = DateUtils.compareDate(dateBefore,dateNow);
            switch (compareDateNum){
                case -1:
                    FileUtils.writeFileOverWrite(logInfoNumberFile,dateNow);
                    File file = new File(logFilePath+"/"+currentFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    numberCountMap = woGameInfoNumberReader(file);
                    woGameInfoNumberParse(numberCountMap, yesterday);
                    break;
                case 1:
                case 0:
                    break;
            }
        } catch (Exception e) {
            Logging.logError("Error occurs in doLogAnalyse1 ", e);
        }
    }

    private void doLogAnalyse2(){
        String dateBefore = null;
        Date today = new Date();
        Date yesterday = DateUtils.getDayByInterval(today,-1);
        String fileDate = DateUtils.formatDateToString(yesterday,"yyyy-MM-dd");
        Map<String,KeyWord> keyMapSave = new HashMap<String, KeyWord>();
        Map<String,KeyWord> keyMapUpdate = new HashMap<String, KeyWord>();
        Map<String,Product> productMap = new HashMap<String, Product>();
        Map<String,DownLoadInfo> downLoadInfoMap = new HashMap<String,DownLoadInfo>();
        String currentFileName = "wogamecenter_info."+fileDate+".log";
        try {
            List<String> currentFileList = FileUtils.readFileByRow(logInfoFile);
            if(currentFileList.size()>0){
                dateBefore = currentFileList.get(0);
            }else{
                dateBefore = new SimpleDateFormat("yyyyMMddHHmmss").format(DateUtils.getDayByInterval(today,-2));
            }
            String dateNow = new SimpleDateFormat("yyyyMMddHHmmss").format(yesterday);
            int compareDateNum = DateUtils.compareDate(dateBefore,dateNow);
            switch (compareDateNum){
                case -1:
                    FileUtils.writeFileOverWrite(logInfoFile,dateNow);
                    File file = new File(logFilePath+"/"+currentFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    BufferedReader reader = new BufferedReader(new UnicodeReader(new FileInputStream(file), "UTF-8"));

                    String tempString = null;
                    while ((tempString = reader.readLine()) != null){
                        woGameInfoParse(tempString,yesterday,keyMapSave,keyMapUpdate,productMap,downLoadInfoMap);
                    }
                    break;
                case 1:
                case 0:
                    break;
            }
            keywordBusiness.typeConversionSave(keyMapSave);
            if(keyMapUpdate.size()>=1){
                keywordBusiness.typeConversionUpdate(keyMapUpdate);
            }
            productBusiness.typeConversion(productMap);
            downLoadInfoBusiness.typeConversion(downLoadInfoMap);
        } catch (Exception e) {
            Logging.logError("Error occurs in doLogAnalyse2 ", e);
        } finally {
            keyMapSave.clear();
            keyMapUpdate.clear();
            productMap.clear();
            downLoadInfoMap.clear();
        }
    }
    @Override
    public void doLogAnalyse(){
        Logging.logDebug("----- doLogAnaylyse start -----");
        try{
            doLogAnalyse1();
            doLogAnalyse2();
        }catch(Exception e){
            Logging.logError("Error occurs in doLogAnaylyse ", e);
        }
        Logging.logDebug("----- doLogAnaylyse end -----");
    }


    @Override
    public void doPackageInfoDomainsSave() throws Exception {
        Logging.logDebug("----- doPackageInfoDomainsSave start -----");

        String currentFileName = "";
        ChannelSftp sftp = null;
        try {
            List<String> currentFileNameList = FileUtils.readFileByRow(latestHanddleFile);
            if (currentFileNameList.size() > 0) {
                currentFileName = currentFileNameList.get(0);
            }

            List<String> fileList = sftpHelper.getFileList(responseFilePath);
            fileList = Utility.getSubStringList(fileList, currentFileName);

            sftp = sftpHelper.connectServer();
            for (String fileName : fileList) {
                List<PackageInfoDomain> packageInfoDomains = new ArrayList<PackageInfoDomain>();
                List<String> contentList = sftpHelper.readRemoteFileByRow(responseFilePath, fileName, sftp);

                for (String content : contentList) {
                    String[] contentArr = Utility.splitString(content, Constant.RESPONSE_FIEL_SEPARATOR);
                    PackageInfoDomain domain = packageInfoBusiness.convertPackageInfoFromFile(contentArr);
                    packageInfoDomains.add(domain);
                }

                packageInfoBusiness.savePackageInfoList(packageInfoDomains, Constant.HIBERNATE_FLUSH_NUM);
                currentFileName = fileName;
            }
        } catch(Exception e){
            Logging.logError("Error occurs in doPackageInfoDomainsSave ", e);
        } finally{
            FileUtils.writeFileOverWrite(latestHanddleFile, currentFileName);
            if (sftp != null) {
                sftpHelper.closeChannel(sftp.getSession(), sftp);
            }
        }
        Logging.logDebug("----- doPackageInfoDomainsSave end -----");
    }

}
