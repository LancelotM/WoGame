package com.unicom.game.center.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.unicom.game.center.db.domain.HomepageConfigDomain;
import com.unicom.game.center.model.BannerInfo;

/**
 * @author Alex
 *
 */
@Component
public class HomepageConfigDao extends HibernateDao<HomepageConfigDomain>{

	public void save(HomepageConfigDomain homepageConfig){
		getSession().save(homepageConfig);
		getSession().flush();
	}
	
	public void update(HomepageConfigDomain homepageConfig){
		getSession().update(homepageConfig);
		getSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<HomepageConfigDomain> fetchAll(){
		return getSession().createQuery("from HomepageConfigDomain").list();
	}	
	
	public HomepageConfigDomain getById(int id){
		return (HomepageConfigDomain)getSession().get(HomepageConfigDomain.class, id);
	}
	
	public List<HomepageConfigDomain> fetchBannerInfo(int type, boolean isAll){
		StringBuffer sb = new StringBuffer();
		sb.append(" from HomepageConfigDomain banner");
		sb.append(" where banner.status = true");
		
		if(!isAll){
			sb.append(" and banner.adType = ");
			sb.append(type);
			sb.append(" order by banner.position asc ");
		}else{
			sb.append(" order by banner.adType asc, banner.position asc ");
		}		
		
		@SuppressWarnings("unchecked")
		List<HomepageConfigDomain> bannerInfos= getSession().createQuery(sb.toString())
									.list();
		
		return bannerInfos;
	}	
	
	public List<BannerInfo> fetchBannerInfoByType(int type){
		StringBuffer sb = new StringBuffer();
		sb.append(" select hc.id as id,  hc.title as title,");
		sb.append(" hc.description as description, hc.url as url,");
		sb.append(" hc.image_name as imageName, hc.ad_type as adType,");
		sb.append(" hc.position as position");
		sb.append(" from homepage_config hc");
		sb.append(" where hc.status = true and hc.ad_type = ");
		sb.append(type);
		sb.append(" order by hc.position asc");
		
		Query query = getSession().createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		List<BannerInfo> bannerInfoList= convertToBannerInfo(list);
		
		return bannerInfoList;			
	}
	
	private List<BannerInfo> convertToBannerInfo(List<Object[]> list){
		List<BannerInfo> bannerInfoList = null;
		if(null != list && list.size() > 0){
			bannerInfoList = new ArrayList<BannerInfo>();
			for(Object[] object : list){
				BannerInfo info = new BannerInfo();
				
				info.setId(Integer.valueOf(String.valueOf(object[0])));
				if(null != object[1]){
					info.setTitle(String.valueOf(object[1]));
				}
				
				if(null != object[2]){
					info.setDescription(String.valueOf(object[2]));
				}				
				
				info.setUrl(String.valueOf(object[3]));
				info.setImageName(String.valueOf(object[4]));
				info.setAdType(Integer.valueOf(String.valueOf(object[5])));
				
				if(null != object[6]){
					info.setPosition(Integer.valueOf(String.valueOf(object[6])));
				}

				bannerInfoList.add(info);
			}
		}
		
		return bannerInfoList;
	}
}
