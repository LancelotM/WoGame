package com.unicom.game.center.web;

import com.unicom.game.center.business.BannerBusiness;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.model.BannerInfoList;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.vo.*;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private StatisticsLogger statisticsLogger;

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/indexlist/banner", method = RequestMethod.GET)
    @ResponseBody
    public List<RollingAdVo> rollingAdList(Model model) {
        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();

        return rollingAdListVo.getData();
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, @RequestParam(value = "token", required = false) String token, Model model, HttpServletRequest request, HttpSession session) {

        String channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        try {
            if (null != token && !"".equals(token)) {
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

        initIndexData(model, pageNum);
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, Model model, HttpSession session) {
        initIndexData(model, pageNum);
        return "index";
    }


    @RequestMapping(value = "/indexlist/ad", method = RequestMethod.GET)
    @ResponseBody
    public BannerInfoList fetchAllBannerList(Model model) {
        BannerInfoList banner = bannerBusiness.fetchAllBanner();

        return banner;
    }


    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String member() {
        return "member";
    }

    //方法
    public void initIndexData(Model model, int pageNum) {
        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        List<RollingAdVo> list = rollingAdListVo.getData();

        BannerInfoList banner = bannerBusiness.fetchAllBanner();
        //追加滚动
        List<BannerInfo> topBanner = banner.getTopBanner();
        for (BannerInfo b : topBanner) {
            if (b.getAdType() == 2) {

                RollingAdVo r = new RollingAdVo();
                r.setTitle(b.getTitle());
                Banner banner1 = new Banner();
                banner1.setBannerUrl(b.getUrl());
                banner1.setResType(b.getAdType());
                r.setBanner(banner1);

                list.add(r);
            }
        }

        model.addAttribute("adList", list);

        //火热
        RecommendedListVo recommendedListVo = gameService.readRecommendedList(1, Constants.PAGE_SIZE_DEFAULT);

        RecommendDataListVo recommendedDataList = new RecommendDataListVo();
        if (null != recommendedListVo && null != recommendedListVo.getRecommendedListData()) {
            List<RecommendDataVo> recommendedList = new ArrayList<RecommendDataVo>();
            for (RecommendDataVo recommendDataVo : recommendedListVo.getRecommendedListData().getRecommendData()) {
                if (null == recommendDataVo.getBanner() ||
                        (null != recommendDataVo.getBanner() && recommendDataVo.getBanner().getResType() == 2)) {
                    recommendedList.add(recommendDataVo);
                }

                if (recommendedList.size() >= 8) {
                    break;
                }
            }

            recommendedDataList.setRecommendData(recommendedList);
        }
        model.addAttribute("hot", recommendedDataList.getRecommendData());

        //最新
        NewListVo newListVo = gameService.readNewList(1, 5);
        List<NewItemListVo> newest = newListVo.getDataList().getNewItemListVo();
        model.addAttribute("newest", newest);

        //分类
        CategoryListVo categoryListVo = gameService.readCategoryList();
        List<CategoryItemVo> category = categoryListVo.getCategoryData();
        model.addAttribute("category", category);

        //活动
        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(1, 3);
        List<ActivityInfoItemVo> activity = activityInfoListVo.getActivityInfoVo().getActivityInfoItemVoList();
        model.addAttribute("activity", activity);

        //开服信息
        NetGameServerVo netGame = gameService.netGameLatestServerList();
        model.addAttribute("netGame", netGame);

        //公告
        model.addAttribute("b", banner);

        //网游活动 活动
        List<BannerInfo> bannerInfoList = banner.getActivityModule();
        List<BannerInfo> bannerList = new ArrayList<BannerInfo>();
        if (bannerInfoList != null) {
            for (BannerInfo b : bannerInfoList) {
                if (b.getAdType() == 3 && bannerList.size() < 2) {
                    bannerList.add(b);
                }
            }
        }
        model.addAttribute("bannerList", bannerList);

        //三个活动
        List<BannerInfo> BannerInfoList = banner.getActivityBanner();
        List<BannerInfo> bList = new ArrayList<BannerInfo>();
        if (BannerInfoList != null) {
            for (BannerInfo b : BannerInfoList) {
                if (b.getAdType() == 4 && bList.size() < 3) {
                    bList.add(b);
                }
            }
        }
        model.addAttribute("bList", bList);
    }
}
