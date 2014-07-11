package com.unicom.game.center.loganalyser.imp;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import net.sf.json.JSONObject;

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

    @Value("#{properties['latest.log.file']}")
    private String latestLogFile;

    private static HashMap<String,Object> productValue(JSONObject dataJson){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        String product_id = dataJson.getString("productId");
        String product_name = dataJson.getString("productName");
        String product_icon = dataJson.getString("productIcon");
        int channel_id = Integer.parseInt(dataJson.getString("channelId"));
        hashMap.put("product_id",product_id);
        hashMap.put("product_name",product_name);
        hashMap.put("product_icon",product_icon);
        hashMap.put("channel_id",channel_id);
        return hashMap;
    }

    private static <E> boolean checkKey(HashMap<String,E> hashMap,String value){
        boolean flag = false;
        E mapValue = hashMap.get(value);
        if(null != mapValue){
           flag = true;
        }
        return flag;
    }
    private static <E> boolean checkKey(HashMap<Integer,E> hashMap,int channelId){
        boolean flag = false;
        E mapValue = hashMap.get(channelId);
        if(null != mapValue){
            flag = true;
        }
        return flag;
    }

    private Product saveProduct(boolean flag,HashMap<String,Object> hashMap){
        Date today = new Date();
        Product product = new Product();
        Date yesterday = DateUtils.getDayByInterval(today,-1);
        if (flag && !(productBusiness.checkId(hashMap.get("product_id").toString()))){
            product.setProduct_id(hashMap.get("product_id").toString());
            product.setProduct_name(hashMap.get("product_name").toString());
            product.setProduct_icon(hashMap.get("product_icon").toString());
            product.setDateCreated(yesterday);
        }
        return product;
    }

	@Override
	public void doLogAnalyse(){
		Logging.logDebug("----- doLogAnaylyse start -----");
		try{
            boolean banner_flag = false;
            boolean file_flag = true;
            int channelId = 0;
            int sort = 0;
            int downloadNum = 0;
            int gameTrafficNum = 0;
            Date today = new Date();
            Date yesterday = new DateUtils().getDayByInterval(today,-1);
            String fileDate = DateUtils.formatDateToString(yesterday,"yyyy-MM-dd");
            String currentFileName = "wogamecenter_info."+fileDate+".log";
            List<String> currentFileList = FileUtils.readFileByRow(latestLogFile);
            for(String fileName : currentFileList){
                 if(currentFileName.equalsIgnoreCase(fileName)){
                    file_flag = false;
                    break;
                }
            }
            if (file_flag){
                FileUtils.writeFileOverWrite(latestLogFile,currentFileName);
                File file = new File(logFilePath+"/"+currentFileName);
                if(!file.exists()){
                    file.createNewFile();
                }
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GB2312"));
                    String tempString = null;
                    int id = 0;
                    HashMap<Integer,UserCount> userCountHashMap = new HashMap<Integer,UserCount>();
                    HashMap<Integer,PageTraffic> pageTrafficHashMap = new HashMap<Integer,PageTraffic>();
                    HashMap<String,Product> productHashMap = new HashMap<String,Product>();
                    HashMap<Integer,GameTraffic> gameTrafficHashMap = new HashMap<Integer,GameTraffic>();
                    HashMap<Integer,DownLoadInfo> downLoadInfoHashMap = new HashMap<Integer,DownLoadInfo>();
                    HashMap<String,KeyWord> keyWordMapSave = new HashMap<String,KeyWord>();
                    HashMap<String,KeyWord> keyWordMapUpdate = new HashMap<String,KeyWord>();
                    while ((tempString = reader.readLine()) != null){
                        String cookie = null;
                        int pgeId = 0;
                        String keyword = null;
                        UserCount userCount = null;
                        PageTraffic pageTraffic = null;
                        Product product = null;
                        DownLoadInfo downLoadInfo = null;
                        KeyWord newKeyWord = null;
                        GameTraffic gameTraffic = new GameTraffic();
                        HashMap<String,Object> hashMapBanner = new HashMap<String,Object>();
                        String tempReplace = tempString.replace("\"", "");
                        String DataInfo = tempString.split(" - ")[1].trim();
                        JSONObject dataJson = JSONObject.fromObject(DataInfo);
                        String [] strTempString = tempReplace.split("\\u007B");

                        if((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("userCount")) {
                            JSONObject userCountJson = dataJson.getJSONObject("userCount");
                            cookie = userCountJson.getString("cookie");
                            channelId = Integer.parseInt(userCountJson.getString("channelId"));
                            boolean flag = LogAnalyser.checkKey(userCountHashMap,channelId);
                            if (flag){
                                userCount = userCountHashMap.get(channelId);
                                if(userCount != null){
                                    if(cookie.equalsIgnoreCase("true")){
                                        userCount.setOld_user_count(userCount.getOld_user_count() + 1);
                                    }else if(cookie.equalsIgnoreCase("false")){
                                        userCount.setNew_user_count(userCount.getNew_user_count() + 1);
                                    }
                                } else{
                                    userCount = new UserCount();
                                    if(cookie.equalsIgnoreCase("true")){
                                        userCount.setOld_user_count(1);
                                    }else if(cookie.equalsIgnoreCase("false")){
                                        userCount.setNew_user_count(1);
                                    }
                                }
                            } else {
                                userCount = new UserCount();
                                if(cookie.equalsIgnoreCase("true")){
                                    userCount.setOld_user_count(1);
                                }else if(cookie.equalsIgnoreCase("false")){
                                    userCount.setNew_user_count(1);
                                }
                            }
                            userCount.setDateCreated(yesterday);
                            userCount.setChannelId(channelId);
                            userCountHashMap.put(channelId, userCount);
                        }
                        if((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("pageTraffic")) {
                            JSONObject pagetraffic = dataJson.getJSONObject("pageTraffic");
                            pgeId = Integer.parseInt(pagetraffic.getString("pageId"));
                            channelId = Integer.parseInt(pagetraffic.getString("channelId"));
                            boolean flag = LogAnalyser.checkKey(pageTrafficHashMap,channelId);
                            if (flag){
                                pageTraffic = pageTrafficHashMap.get(channelId);
                                if(null != pageTraffic){
                                    if(pgeId == 1){
                                        pageTraffic.setHomepage(pageTraffic.getHomepage() + 1);
                                    } else if(pgeId == 2){
                                        pageTraffic.setCategory(pageTraffic.getCategory() + 1);
                                    } else if(pgeId == 3){
                                        pageTraffic.setHotlist(pageTraffic.getHotlist() + 1);
                                    } else if(pgeId == 4){
                                        pageTraffic.setLatest(pageTraffic.getLatest() + 1);
                                    }
                                }else{
                                    pageTraffic = new PageTraffic();
                                    if(pgeId == 1){
                                        pageTraffic.setHomepage(1);
                                    } else if(pgeId == 2){
                                        pageTraffic.setCategory(1);
                                    } else if(pgeId == 3){
                                        pageTraffic.setHotlist(1);
                                    } else if(pgeId == 4){
                                        pageTraffic.setLatest(1);
                                    }
                                }
                            } else {
                                pageTraffic = new PageTraffic();
                                if(pgeId == 1){
                                    pageTraffic.setHomepage(1);
                                } else if(pgeId == 2){
                                    pageTraffic.setCategory(1);
                                } else if(pgeId == 3){
                                    pageTraffic.setHotlist(1);
                                } else if(pgeId == 4){
                                    pageTraffic.setLatest(1);
                                }
                            }
                            pageTraffic.setDateCreated(yesterday);
                            pageTraffic.setChannelId(channelId);
                            pageTrafficHashMap.put(channelId, pageTraffic);
                        }
                        if((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("keyword")) {
                            JSONObject kwJson = dataJson.getJSONObject("keyword");
                            keyword = kwJson.getString("keyword");
                            boolean flag_save = LogAnalyser.checkKey(keyWordMapSave,keyword);
                            boolean flag_update = LogAnalyser.checkKey(keyWordMapUpdate,keyword);
                            KeywordDomain keywordDomain = keywordBusiness.getKeyWord(keyword);
                            if(keywordDomain == null){
                                if (!flag_save){
                                    newKeyWord = new KeyWord();
                                    newKeyWord.setKeyword(keyword);
                                    newKeyWord.setCount(1);
                                    newKeyWord.setDateCreated(yesterday);
                                    newKeyWord.setDateModified(today);
                                    keyWordMapSave.put(keyword, newKeyWord);
                                } else{
                                    newKeyWord = keyWordMapSave.get(keyword) ;
                                    newKeyWord.setKeyword(keyword);
                                    newKeyWord.setCount(newKeyWord.getCount() + 1);
                                    newKeyWord.setDateCreated(yesterday);
                                    newKeyWord.setDateModified(today);
                                    keyWordMapSave.put(keyword, newKeyWord);
                                }
                            } else {
                                if(!flag_update){
                                    newKeyWord = new KeyWord();
                                    newKeyWord.setId(keywordDomain.getId());
                                    newKeyWord.setKeyword(keyword);
                                    newKeyWord.setCount(keywordDomain.getCount());
                                    keyWordMapUpdate.put(keyword,newKeyWord);
                                    newKeyWord = keyWordMapUpdate.get(keyword);
                                    newKeyWord.setKeyword(keyword);
                                    newKeyWord.setCount(newKeyWord.getCount() + 1);
                                    newKeyWord.setDateCreated(yesterday);
                                    newKeyWord.setDateModified(today);
                                    keyWordMapUpdate.put(keyword, newKeyWord);
                                }else{
                                    newKeyWord = keyWordMapUpdate.get(keyword) ;
                                    newKeyWord.setId(newKeyWord.getId());
                                    newKeyWord.setKeyword(keyword);
                                    newKeyWord.setCount(newKeyWord.getCount() + 1);
                                    newKeyWord.setDateCreated(yesterday);
                                    newKeyWord.setDateModified(today);
                                    keyWordMapUpdate.put(keyword, newKeyWord);
                                }
                            }

                        }
                        if((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("bannerTraffic")||(strTempString[1].split(":"))[0].trim().equalsIgnoreCase("hotGameTraffic")) {
                            if((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("bannerTraffic")){
                                banner_flag = true;
                                JSONObject bannerTrafficJson = dataJson.getJSONObject("bannerTraffic");
                                hashMapBanner = LogAnalyser.productValue(bannerTrafficJson);
                                sort = Integer.parseInt(bannerTrafficJson.getString("sort"));
                                channelId = Integer.parseInt(hashMapBanner.get("channel_id").toString());
                                hashMapBanner.put("banner_flag", banner_flag);
                                hashMapBanner.put("sort",sort);
                                Product gameTrafficProduct = new Product();
                                gameTrafficProduct.setProduct_id(hashMapBanner.get("product_id").toString());
                                gameTrafficProduct.setProduct_icon(hashMapBanner.get("product_icon").toString());
                                gameTrafficProduct.setProduct_name(hashMapBanner.get("product_name").toString());
                                gameTrafficProduct.setDateCreated(yesterday);
                                if((sort <30) && productBusiness.checkId(hashMapBanner.get("product_id").toString())) {
                                    gameTraffic = new GameTraffic();
                                    gameTraffic.setClick_through(gameTrafficNum);
                                    gameTraffic.setDownload_count(downloadNum);
                                    gameTraffic.setChannel_id(channelId);
                                    gameTraffic.setProduct_id(hashMapBanner.get("product_id").toString());
                                    gameTraffic.setSort(Integer.parseInt(hashMapBanner.get("sort").toString()));
                                    gameTraffic.setBanner_flag(Boolean.parseBoolean(hashMapBanner.get("banner_flag").toString()));
                                    gameTraffic.setDateCreated(yesterday);
                                    gameTraffic.setProduct(gameTrafficProduct);
                                    gameTrafficHashMap.put(++id,gameTraffic);
                                }
                            } else if ((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("hotGameTraffic")){
                                banner_flag = false;
                                JSONObject hotGameJson = dataJson.getJSONObject("hotGameTraffic");
                                hashMapBanner = LogAnalyser.productValue(hotGameJson);
                                sort = Integer.parseInt(hotGameJson.getString("sort"));
                                channelId = Integer.parseInt(hashMapBanner.get("channel_id").toString());
                                hashMapBanner.put("banner_flag",banner_flag);
                                hashMapBanner.put("sort",sort);
                                boolean flag = LogAnalyser.checkKey(gameTrafficHashMap,channelId);
                                Product gameTrafficProduct = new Product();
                                gameTrafficProduct.setProduct_id(hashMapBanner.get("product_id").toString());
                                gameTrafficProduct.setProduct_icon(hashMapBanner.get("product_icon").toString());
                                gameTrafficProduct.setProduct_name(hashMapBanner.get("product_name").toString());
                                gameTrafficProduct.setDateCreated(yesterday);
                                if((sort <30) && !flag && productBusiness.checkId(hashMapBanner.get("product_id").toString())) {
                                    gameTraffic = new GameTraffic();
                                    gameTraffic.setClick_through(1);
                                    gameTraffic.setChannel_id(channelId);
                                    gameTraffic.setDownload_count(downloadNum);
                                    gameTraffic.setProduct_id(hashMapBanner.get("product_id").toString());
                                    gameTraffic.setSort(Integer.parseInt(hashMapBanner.get("sort").toString()));
                                    gameTraffic.setBanner_flag(Boolean.parseBoolean(hashMapBanner.get("banner_flag").toString()));
                                    gameTraffic.setDateCreated(yesterday);
                                    gameTraffic.setProduct(gameTrafficProduct);
                                    gameTrafficNum = gameTraffic.getClick_through();
                                    gameTrafficHashMap.put(++id,gameTraffic);
                                }else if((sort <30) && flag && productBusiness.checkId(hashMapBanner.get("product_id").toString())) {
                                    gameTraffic = new GameTraffic();
                                    gameTraffic.setClick_through(gameTrafficNum + 1);
                                    gameTraffic.setDownload_count(downloadNum);
                                    gameTraffic.setChannel_id(channelId);
                                    gameTraffic.setProduct_id(hashMapBanner.get("product_id").toString());
                                    gameTraffic.setSort(Integer.parseInt(hashMapBanner.get("sort").toString()));
                                    gameTraffic.setBanner_flag(Boolean.parseBoolean(hashMapBanner.get("banner_flag").toString()));
                                    gameTraffic.setDateCreated(yesterday);
                                    gameTraffic.setProduct(gameTrafficProduct);
                                    gameTrafficNum = gameTraffic.getClick_through();
                                    gameTrafficHashMap.put(++id,gameTraffic);
                                }
                            }

                            boolean flag =  LogAnalyser.checkKey(productHashMap,hashMapBanner.get("product_id").toString());
                            if (!flag && !(productBusiness.checkId(hashMapBanner.get("product_id").toString()))){
                                product = new LogAnalyser().saveProduct(flag,hashMapBanner);
                                productHashMap.put(hashMapBanner.get("product_id").toString(), product);
                            }
                        }else if ((strTempString[1].split(":"))[0].trim().equalsIgnoreCase("downloadInfo")) {
                            gameTraffic.setDownload_count(gameTraffic.getDownload_count() + 1);
                            JSONObject downLoadJson = dataJson.getJSONObject("downloadInfo");
                            hashMapBanner = LogAnalyser.productValue(downLoadJson);
                            channelId = Integer.parseInt(hashMapBanner.get("channel_id").toString());
                            boolean flag = LogAnalyser.checkKey(downLoadInfoHashMap,channelId);
                            if(!flag && productBusiness.checkId(hashMapBanner.get("product_id").toString())){
                                downLoadInfo = new DownLoadInfo();
                                downLoadInfo.setDownload_count(1);
                                downLoadInfo.setChannel_id(channelId);
                                downLoadInfo.setProduct_id(hashMapBanner.get("product_id").toString());
                                downLoadInfo.setDateCreated(yesterday);
                                downloadNum = downLoadInfo.getDownload_count();
                                downLoadInfoHashMap.put(channelId, downLoadInfo);
                            } else if(flag && productBusiness.checkId(hashMapBanner.get("product_id").toString())){
                                downLoadInfo = downLoadInfoHashMap.get(channelId);
                                downLoadInfo.setDownload_count(downLoadInfo.getDownload_count() + 1);
                                downLoadInfo.setChannel_id(channelId);
                                downLoadInfo.setProduct_id(hashMapBanner.get("product_id").toString());
                                downLoadInfo.setDateCreated(yesterday);
                                downloadNum = downLoadInfo.getDownload_count();
                                downLoadInfoHashMap.put(channelId, downLoadInfo);
                            }
                            flag = LogAnalyser.checkKey(productHashMap,hashMapBanner.get("product_id").toString());
                            if (!flag && !(productBusiness.checkId(hashMapBanner.get("product_id").toString()))){
                                product = new LogAnalyser().saveProduct(flag,hashMapBanner);
                                productHashMap.put(hashMapBanner.get("product_id").toString(), product);
                            }
                        }
                    }
                    userCountBusiness.typeConversion(userCountHashMap);
                    pageTrafficBusiness.typeConversion(pageTrafficHashMap);
                    gameTrafficBusiness.typeConversion(gameTrafficHashMap);
                    if(keyWordMapUpdate.size()>=1){
                        keywordBusiness.typeConversionUpdate(keyWordMapUpdate);
                    }
                    keywordBusiness.typeConversionSave(keyWordMapSave);
                    productBusiness.typeConversion(productHashMap);
                    downLoadInfoBusiness.typeConversion(downLoadInfoHashMap);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
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
