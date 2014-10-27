package com.unicom.game.center.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.business.BannerBusiness;
import com.unicom.game.center.model.BannerInfoList;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.vo.ActivityInfoItemVo;
import com.unicom.game.center.vo.ActivityInfoListVo;
import com.unicom.game.center.vo.CategoryItemVo;
import com.unicom.game.center.vo.CategoryListVo;
import com.unicom.game.center.vo.NetGameServerItemVo;
import com.unicom.game.center.vo.NetGameServerListVo;
import com.unicom.game.center.vo.NewItemListVo;
import com.unicom.game.center.vo.NewListVo;
import com.unicom.game.center.vo.RecommendDataListVo;
import com.unicom.game.center.vo.RecommendDataVo;
import com.unicom.game.center.vo.RecommendedListVo;
import com.unicom.game.center.vo.RollingAdListVo;
import com.unicom.game.center.vo.RollingAdVo;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
public class IndexController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private BannerBusiness bannerBusiness;

	@Value("#{properties['site.secret.key']}")
	private String siteKey;    
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	
    @Autowired
    private StatisticsLogger statisticsLogger;

    @RequestMapping(value = "/banner", method = RequestMethod.GET)
     @ResponseBody
     public List<RollingAdVo> rollingAdList(Model model) {
        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();

        return rollingAdListVo.getData();
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, @RequestParam(value = "token", required = false) String token, Model model, HttpServletRequest request, HttpSession session) {

    	String channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
    	try {
    		if(null != token && !"".equals(token)){
    			channel = AESEncryptionHelper.decrypt(token, siteKey);
    		}    		
		} catch (Exception e) {
			channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
			e.printStackTrace();
			logger.error("Error channel token!");
		}
    	
    	String clientIP = HttpClientUtil.getClientIp(request);
    	String[] logData = new String[]{"60", channel, clientIP};
    	statisticsLogger.pageview(StringUtils.join(logData, "|"));    	
    	
        session.setAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);

        model.addAttribute("isIndex", true);
        
        //轮播广告
        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        model.addAttribute("adList", rollingAdListVo.getData());
        
        //火热
        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum,Constants.PAGE_SIZE_DEFAULT);
        
        RecommendDataListVo recommendedDataList = new RecommendDataListVo();
        if(null != recommendedListVo && null != recommendedListVo.getRecommendedListData()){
        	List<RecommendDataVo> recommendedList = new ArrayList<RecommendDataVo>();
        	for (RecommendDataVo recommendDataVo : recommendedListVo.getRecommendedListData().getRecommendData()){
        		if(null == recommendDataVo.getBanner() ||
        				(null != recommendDataVo.getBanner() && recommendDataVo.getBanner().getResType() == 2)){
        			recommendedList.add(recommendDataVo);
        		}
        		
        		if(recommendedList.size() >= 8){
        			break;
        		}
        	}
        	
        	recommendedDataList.setRecommendData(recommendedList);
        }


        model.addAttribute("recommendedList", recommendedDataList);


        //活动
        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(1, 3);
        List<ActivityInfoItemVo> activity = activityInfoListVo.getActivityInfoVo().getActivityInfoItemVoList();
        model.addAttribute("activity", activity);

        //最新
        NewListVo newListVo = gameService.readNewList(1, 5);
        List<NewItemListVo> newest = newListVo.getDataList().getNewItemListVo();
        model.addAttribute("newest", newest);

        //分类
        CategoryListVo categoryListVo = gameService.readCategoryList();
        List<CategoryItemVo> category = categoryListVo.getCategoryData();
        model.addAttribute("category", category);

        
        //开服信息
        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(1, 100);
        
    	Date today = DateUtils.beginOfDate(new Date());
    	Date latest = DateUtils.getDayByInterval(today, -7);        
        if(null != netGameServerListVo && null != netGameServerListVo.getNetGameServerVo() && netGameServerListVo.getNetGameServerVo().getTotalNum() > 0){
        	int index = 0;
        	for(NetGameServerItemVo  item : netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList()){
        		if(item.getOpenTime() < latest.getTime()){
        			break;
        		}
        		
        		index++;
        	}
        	
        	List<NetGameServerItemVo> items = netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList().subList(0, index);
        	netGameServerListVo.getNetGameServerVo().setNetGameServerItemVoList(items);
        	netGameServerListVo.getNetGameServerVo().setTotalNum(items.size());
        }
        
        List<NetGameServerItemVo> netGame = netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList();
        model.addAttribute("netGame", netGame);

        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(@RequestParam("pageNum") int pageNum, Model model, HttpSession session) {

        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum,Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("adList", rollingAdListVo.getData());
        model.addAttribute("recommendedList", recommendedListVo.getRecommendedListData());
        return "index";
    }

    
    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    @ResponseBody
    public BannerInfoList fetchAllBannerList(Model model) {
    	BannerInfoList banner = bannerBusiness.fetchAllBanner();

    	return banner;
   }    
}
