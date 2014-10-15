package com.unicom.game.center.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.HomepageConfigDao;
import com.unicom.game.center.db.domain.HomepageConfigDomain;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.SFTPHelper;
import com.unicom.game.center.utils.Utility;

/**
 * @author Alex
 *
 */
@Component
@Transactional
public class BannerBusiness {
	
	@Autowired
	private HomepageConfigDao bannerDao;
	
	@Autowired
	private SFTPHelper sftpHelper;
	
	@Value("#{properties['banner.image.path']}")
	private String imagePath;
	
	public List<BannerInfo> fetchBannerInfos(int type){
		List<BannerInfo> bannerList = null;
		
		try{
			bannerList = bannerDao.fetchBannerInfoByType(type);
		}catch(Exception e){
			Logging.logError("Error occur in fetchBannerInfos", e);
		}	
		
		return bannerList;
	}
	
	public boolean createBanner(BannerInfo banner){
		boolean flag = false;
		
		try{
			boolean uploadflag = false;
			long timestamp = System.currentTimeMillis();
			if(null != banner && !Utility.isEmpty(banner.getImageName())){
				uploadflag = sftpHelper.uploadFile(banner.getImageName(), (imagePath + String.valueOf(timestamp)));
			}
			
			if(uploadflag){
				HomepageConfigDomain homepageConfig = convertToBannerDomain(banner, timestamp);
				bannerDao.save(homepageConfig);
				flag = true;
			}
		}catch(Exception e){
			Logging.logError("Error occur in createBanner", e);
		}
		
		return flag;
	}
	
	public boolean modifyBanner(BannerInfo banner){
		boolean flag = false;
		
		try{
			if(null != banner){
				HomepageConfigDomain domain = bannerDao.getById(banner.getId());
				if(null != domain){
					domain.setDateModified(new Date());
					domain.setDescription(banner.getDescription());
					if(banner.getImageName().startsWith("http://")){
						domain.setImageName(banner.getImageName());
					}else{
						long timestamp = System.currentTimeMillis();
						boolean	uploadflag = sftpHelper.uploadFile(banner.getImageName(), (imagePath + String.valueOf(timestamp)));
						
						if(uploadflag){
							domain.setImageName("http://channel.wostore.cn:8080/images/" + String.valueOf(timestamp));
						}else{
							return flag;
						}
					}
					
					domain.setPosition(banner.getPosition());
					domain.setTitle(banner.getTitle());
					domain.setUrl(banner.getUrl());
					
					bannerDao.update(domain);
					flag = true;
				}
			}
		}catch(Exception e){
			Logging.logError("Error occur in modifyBanner", e);
		}
		
		return flag;
	}
	
	public boolean deleteBanner(Integer id){
		boolean flag = true;
		
		try{
			HomepageConfigDomain banner = bannerDao.getById(id);
			if(null != banner){
				banner.setStatus(false);
				bannerDao.update(banner);
			}
		}catch(Exception e){
			Logging.logError("Error occur in deleteBanner", e);
			flag = false;
		}
		
		return flag;
	}
	
	private HomepageConfigDomain convertToBannerDomain(BannerInfo banner, long timestamp){
		HomepageConfigDomain homepageConfig = null;
		if(null != banner){
			Date date = new Date();
			homepageConfig = new HomepageConfigDomain();
			homepageConfig.setAdType(banner.getAdType());
			homepageConfig.setDescription(banner.getDescription());
			homepageConfig.setImageName("http://channel.wostore.cn:8080/images/" + String.valueOf(timestamp));
			homepageConfig.setPosition(banner.getPosition());
			homepageConfig.setStatus(true);
			homepageConfig.setTitle(banner.getTitle());
			homepageConfig.setUrl(banner.getUrl());
			homepageConfig.setDateCreated(date);
			homepageConfig.setDateModified(date);
		}
		
		return homepageConfig;
	}

}
