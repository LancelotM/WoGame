package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.HomepageConfigDao;
import com.unicom.game.center.db.domain.HomepageConfigDomain;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.model.BannerInfoList;
import com.unicom.game.center.utils.Constant;
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
			List<HomepageConfigDomain> domainList = bannerDao.fetchBannerInfo(type, false);
			if(null != domainList && !domainList.isEmpty()){
				bannerList = new ArrayList<BannerInfo>();
				for(HomepageConfigDomain domain : domainList){
					BannerInfo bannerInfo = convertToBannerInfo(domain);
					bannerList.add(bannerInfo);
				}
			}
		}catch(Exception e){
			Logging.logError("Error occur in fetchBannerInfos", e);
		}	
		
		return bannerList;
	}
	
	public BannerInfo fetchBannerInfoById(int id){
		BannerInfo banner = null;
		
		try{
			HomepageConfigDomain domain = bannerDao.getById(id);
			if(null != domain){
				banner =  convertToBannerInfo(domain);
			}
		}catch(Exception e){
			Logging.logError("Error occur in fetchBannerInfoById", e);
		}	
		
		return banner;
	}	
	
	public BannerInfoList fetchAllBanner(){
		BannerInfoList bannerInfoList = null;
		
		try{
			List<HomepageConfigDomain> bannerList = bannerDao.fetchBannerInfo(0, true);
			if(null != bannerList && !bannerList.isEmpty()){
				bannerInfoList = new BannerInfoList();
				
				List<BannerInfo> topBanner = new ArrayList<BannerInfo>();				
				List<BannerInfo> activityModule = new ArrayList<BannerInfo>();					
				List<BannerInfo> activityBanner = new ArrayList<BannerInfo>();
				
				for(HomepageConfigDomain domain : bannerList){
					BannerInfo bannerInfo = convertToBannerInfo(domain);
					
					if(null != bannerInfo){
						switch(bannerInfo.getAdType()){
						case Constant.HOMEPAGE_TOP_AD : bannerInfoList.setTopAD(bannerInfo);
						
						case Constant.HOMEPAGE_TOP_BANNER : topBanner.add(bannerInfo);
						
						case Constant.HOMEPAGE_ACTIVITY_MODULE : activityModule.add(bannerInfo);
						
						case Constant.HOMEPAGE_ACTIVITY_BANNER : activityBanner.add(bannerInfo);
						
						case Constant.HOMEPAGE_FOOTER_AD : bannerInfoList.setBottomAD(bannerInfo);
						
						default : ;
						
						}
					}
					
				}
				
				bannerInfoList.setTopBanner(topBanner);
				bannerInfoList.setActivityModule(activityModule);
				bannerInfoList.setActivityBanner(activityBanner);
			}
		}catch(Exception e){
			Logging.logError("Error occur in fetchAllBanner", e);
		}
		
		return bannerInfoList;
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
	
	private BannerInfo convertToBannerInfo(HomepageConfigDomain domain){
		BannerInfo info = null;
		
		if(null != domain){
			info = new BannerInfo();
			
			info.setId(domain.getId());
			
			if(null != domain.getTitle()){
				info.setTitle(domain.getTitle());
			}
			
			if(null != domain.getDescription()){
				info.setDescription(domain.getDescription());
			}				
			
			info.setUrl(domain.getUrl());
			info.setImageName(domain.getImageName());
			info.setAdType(domain.getAdType());
			info.setPosition(domain.getPosition());		
		}
		
		return info;
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
