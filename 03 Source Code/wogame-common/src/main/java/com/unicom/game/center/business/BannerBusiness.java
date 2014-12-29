package com.unicom.game.center.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.unicom.game.center.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicom.game.center.db.dao.HomepageConfigDao;
import com.unicom.game.center.db.domain.HomepageConfigDomain;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.model.BannerInfoList;

/**
 * @author Alex
 */
@Component
@Transactional
public class BannerBusiness {

    @Autowired
    private HomepageConfigDao bannerDao;

    @Value("#{properties['banner.image.path']}")
    private String imagePath;

    public List<BannerInfo> fetchBannerInfos(int type) {
        List<BannerInfo> bannerList = null;

        try {
            List<HomepageConfigDomain> domainList = bannerDao.fetchBannerInfo(type, false);
            if (null != domainList && !domainList.isEmpty()) {
                bannerList = new ArrayList<BannerInfo>();
                for (HomepageConfigDomain domain : domainList) {
                    BannerInfo bannerInfo = convertToBannerInfo(domain);
                    bannerList.add(bannerInfo);
                }
            }
        } catch (Exception e) {
            Logging.logError("Error occur in fetchBannerInfos", e);
        }

        return bannerList;
    }

    public BannerInfo fetchBannerInfoById(int id) {
        BannerInfo banner = null;

        try {
            HomepageConfigDomain domain = bannerDao.getById(id);
            if (null != domain) {
                banner = convertToBannerInfo(domain);
            }
        } catch (Exception e) {
            Logging.logError("Error occur in fetchBannerInfoById", e);
        }

        return banner;
    }

    public BannerInfoList fetchAllBanner() {
        BannerInfoList bannerInfoList = new BannerInfoList();

        try {
            List<HomepageConfigDomain> bannerList = bannerDao.fetchBannerInfo(0, true);
            if (null != bannerList && !bannerList.isEmpty()) {
                List<BannerInfo> topBanner = new ArrayList<BannerInfo>();
                List<BannerInfo> activityModule = new ArrayList<BannerInfo>();
                List<BannerInfo> activityBanner = new ArrayList<BannerInfo>();

                for (HomepageConfigDomain domain : bannerList) {
                    BannerInfo bannerInfo = convertToBannerInfo(domain);

                    if (null != bannerInfo) {
                        switch (bannerInfo.getAdType().intValue()) {
                            case Constant.HOMEPAGE_TOP_AD:
                                bannerInfoList.setTopAD(bannerInfo);
                                break;

                            case Constant.HOMEPAGE_TOP_BANNER:
                                topBanner.add(bannerInfo);
                                break;

                            case Constant.HOMEPAGE_ACTIVITY_MODULE:
                                activityModule.add(bannerInfo);
                                break;

                            case Constant.HOMEPAGE_ACTIVITY_BANNER:
                            	if(Constant.HOMEPAGE_SMALL_ACTIVITY_BANNER_FLAG == bannerInfo.getPosition().intValue()){
                            		activityBanner.add(bannerInfo);
                            	}
                                
                                break;

                            case Constant.HOMEPAGE_FOOTER_AD:
                                bannerInfoList.setBottomAD(bannerInfo);
                                break;

                            default:
                                ;

                        }
                    }

                }

                bannerInfoList.setTopBanner(topBanner);
                bannerInfoList.setActivityModule(activityModule);
                bannerInfoList.setActivityBanner(activityBanner);
            }
        } catch (Exception e) {
            Logging.logError("Error occur in fetchAllBanner", e);
        }

        return bannerInfoList;
    }

    public void save(HomepageConfigDomain homepageConfig) {
        bannerDao.save(homepageConfig);
    }

    public void update(HomepageConfigDomain homepageConfig) {
        bannerDao.update(homepageConfig);
    }

    public boolean modifyBanner(BannerInfo banner, String timestamp) {
        boolean flag = false;

        try {
            if (null != banner && !Utility.isEmpty(timestamp)) {
                HomepageConfigDomain domain = bannerDao.getById(banner.getId());
                if (null != domain) {
                    domain.setDateModified(new Date());
                    domain.setDescription(banner.getDescription());
                    domain.setImageName(banner.getImageName());
                    domain.setPosition(banner.getPosition());
                    domain.setTitle(banner.getTitle());
                    domain.setUrl(banner.getUrl());
                    domain.setStatus(banner.isStatus());
                    bannerDao.update(domain);
                    flag = true;
                }

            }
        } catch (Exception e) {
            Logging.logError("Error occur in modifyBanner", e);
        }

        return flag;
    }

    public boolean deleteBanner(Integer id) {
        boolean flag = true;

        try {
            HomepageConfigDomain banner = bannerDao.getById(id);
            if (null != banner) {
                banner.setStatus(false);
                bannerDao.update(banner);
            }
        } catch (Exception e) {
            Logging.logError("Error occur in deleteBanner", e);
            flag = false;
        }

        return flag;
    }

    private BannerInfo convertToBannerInfo(HomepageConfigDomain domain) {
        BannerInfo info = null;

        if (null != domain) {
            info = new BannerInfo();

            info.setId(domain.getId());

            if (null != domain.getTitle()) {
                info.setTitle(domain.getTitle());
            }

            if (null != domain.getDescription()) {
                info.setDescription(domain.getDescription());
            }

            if(domain.isStatus()){
                info.setStatus(domain.isStatus());
            }

            info.setUrl(domain.getUrl());
            info.setImageName(domain.getImageName());
            info.setAdType(domain.getAdType());
            info.setPosition(domain.getPosition());
        }

        return info;
    }

    public  HomepageConfigDomain convertToBannerDomain(BannerInfo banner, Operator operator) {
        HomepageConfigDomain homepageConfig = new HomepageConfigDomain();
        if (null == banner) {
            return null;
        }
        Date date = new Date();

        if(operator.equals(Operator.update)){
            homepageConfig.setId(banner.getId());
            homepageConfig.setDateCreated(date);
        }else if(operator.equals(Operator.create)){
            homepageConfig.setDateCreated(date);
        }

        homepageConfig.setUrl(banner.getUrl());
        homepageConfig.setImageName(banner.getImageName());
        homepageConfig.setAdType(banner.getAdType());
        homepageConfig.setDescription(banner.getDescription());
        homepageConfig.setPosition((null == banner.getPosition()) ? 0 : banner.getPosition());
        homepageConfig.setStatus(banner.isStatus());
        homepageConfig.setTitle(banner.getTitle());
        homepageConfig.setDateModified(date);

        return homepageConfig;
    }

}
