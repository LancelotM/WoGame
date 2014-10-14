package com.unicom.game.center.business;

import java.util.*;

import com.unicom.game.center.model.ChannelModel;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.ChannelInfoDao;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;
import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.utils.Logging;

/**
 * @author Alex Yin
 * 
 * @Date Jun 21, 2014
 */
@Component
@Transactional
public class ChannelInfoBusiness {
	
	@Autowired
	private ChannelInfoDao channelInfoDao;
	
	@Value("#{properties['site.secret.key']}")
	private String siteKey;
	
	@Value("#{properties['backend.secret.key']}")
	private String backendKey;	
	
	@Value("#{properties['channel.info.path']}")
	private String channelInfoPath;	
	
	
	public List<ChannelInfo> fetchActiveChannelInfos(){
		List<ChannelInfo> channelInfos = null;
		try{
			channelInfos = channelInfoDao.fetchActiveChannels();
		}catch(Exception e){
			Logging.logError("Error occur in fetchActiveChannelInfos", e);
		}
		
		return channelInfos;
	}
	
	public ChannelInfo fetchChannelInfoById(int channelId){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelById(channelId);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoById", e);
		}
		
		return channel;
	}
		
	public ChannelInfo fetchChannelInfoByName(String channelName){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelByName(channelName);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoByName", e);
		}
		
		return channel;
	}
	
	public ChannelInfo fetchChannelInfoByCode(String channelCode){
		ChannelInfo channel = null;
		
		try{
			channel = channelInfoDao.fetchChannelInfoByCode(channelCode);
		}catch(Exception e){
			Logging.logError("Error occur in fetchChannelInfoByCode", e);
		}
		
		return channel;
	}
	
	public boolean updateChannel(int channelId, String channelCode, String cpId){
        if(channelCode.trim().length() != 8 && channelCode.trim().length() == 5){
            channelCode = "000"+channelCode;
        }
		boolean flag = false;
		try{
			ChannelInfoDomain channel = channelInfoDao.getById(channelId);
			if(null != channel){
				channel.setStatus(true);
				channel.setDateModified(new Date());
				channel.setCpId(cpId);
				channel.setChannelCode(channelCode);
				channelInfoDao.update(channel);					
			}
			flag = true;
		}catch(Exception ex){
			Logging.logError("Error occur in update channel", ex);
		}
		
		return flag;
	}
	
	public ChannelInfoDomain startChannel(String channelCode, String channelName, String cpId){
		ChannelInfoDomain channel = null;
        if(channelCode.trim().length() != 8 && channelCode.trim().length() == 5){
            channelCode = "000"+channelCode;
        }
		
		try{
			Date date = new Date();
			channel = channelInfoDao.fetchChannelByCode(channelCode);
			if(null != channel){
				if(!channel.isStatus()){
					channel.setStatus(true);
					channel.setDateModified(date);
					channel.setDateCreated(date);
					channel.setChannelName(channelName);
					channel.setCpId(cpId);
					channel.setChannelCode(channelCode);
					channel.setSync_type(0);
					channel.setSync_status(true);
					channelInfoDao.update(channel);					
				}
			}else{
				channel = new ChannelInfoDomain();
				channel.setStatus(true);

				channel.setDateModified(date);
				channel.setDateCreated(date);
				channel.setChannelName(channelName);
				channel.setCpId(cpId);
				channel.setChannelCode(channelCode);
				channel.setSync_type(0);
				channel.setSync_status(true);
				channelInfoDao.save(channel);
				
				channel = channelInfoDao.fetchChannelByCode(channelCode);
				String siteToken = AESEncryptionHelper.encrypt(String.valueOf(channel.getChannelId()), siteKey);
				String backendToken = AESEncryptionHelper.encrypt(String.valueOf(channel.getChannelId()), backendKey);
				channel.setWapToken(siteToken);
				channel.setLogToken(backendToken);
				channelInfoDao.update(channel);
			}
		}catch(Exception e){
			Logging.logError("Error occur in startChannel", e);
			channel = null;
		}
		
		return channel;		
	}

    public List<ChannelModel> getChannelMap(){
        Map<String,List<String>> channelMap = getData();
        List<ChannelModel> channelModels = new ArrayList<ChannelModel>();
        ChannelModel  channelModel = null;
        for(String key : channelMap.keySet()){
            channelModel = new ChannelModel();
            channelModel.setKey(key);
            channelModel.setChannels(channelMap.get(key));
            channelModels.add(channelModel);
        }
        return channelModels;
    }

    public Map<String,List<String>> getData(){
        SAXReader reader = new SAXReader();
        TreeMap<String,List<String>> data = new TreeMap<String, List<String>>();
        List<String> ranges = new ArrayList<String>();
        ranges.add("A-G");
        ranges.add("H-J");
        ranges.add("L-S");
        ranges.add("T-Z");
        try {
            Document document = reader.read(channelInfoPath);
            Element element = document.getRootElement();
            List nodes = element.elements("channel");
            for(Iterator it = nodes.iterator();it.hasNext();){
                Element channel = (Element) it.next();
                String channelInfo = channel.getText();
                char firstAlph = getPinYinHeadChar(channelInfo);
                for(String range : ranges){
                    char first = range.charAt(0);
                    char end = range.charAt(range.length()-1);
                    if(firstAlph>=first && firstAlph <= end){
                        getMap(data,range,channelInfo);
                    }
                }
            }

        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    // 返回中文的首字母
    public char getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase().charAt(0);
    }

    public void getMap(TreeMap<String,List<String>> map,String key,String value){
        for(String name : map.keySet()){
            if(key.equals(name)){
                map.get(name).add(value);
                return;
            }
        }
        List<String> gameInfos = new ArrayList<String>();
        gameInfos.add(value);
        map.put(key,gameInfos);
    }

}
