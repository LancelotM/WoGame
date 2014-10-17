package com.unicom.game.center.controller;

import com.unicom.game.center.business.BannerBusiness;
import com.unicom.game.center.model.*;
import com.unicom.game.center.utils.Constant;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BannerController {

    @Autowired
    private BannerBusiness bannerBusiness;

    @RequestMapping(value = "/topbanner", method = {RequestMethod.GET})
    public ModelAndView topBanner(HttpServletRequest request, HttpSession session) throws IOException {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            List<BannerInfo> topBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
           /* List<BannerInfo> topBannerInfos = new ArrayList<BannerInfo>();
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setId(1);
            bannerInfo.setUrl("http://www.baidu.com");
            bannerInfo.setTitle("");
            bannerInfo.setAdType(Constant.HOMEPAGE_TOP_BANNER);
            bannerInfo.setDescription("吐血推荐！不好玩你打我，别打死我就行，哈哈，来打我啊");
            bannerInfo.setPosition(1);
            bannerInfo.setStatus(true);
            bannerInfo.setImageName("D:\\wogame\\trunk\\03 Source Code\\wogame-backend\\src\\main\\webapp\\static\\images\\large.png");
            topBannerInfos.add(bannerInfo);*/
            if(null != topBannerInfos){
                model.put("topBannerInfos", topBannerInfos);
            }
            return new ModelAndView("/topbanner", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }

    @RequestMapping(value = "/createtopbanner", method = {RequestMethod.POST})
    public ModelAndView createTopBanner(@RequestParam(value = "imageName", required = true) String imageName,
                                        @RequestParam(value = "url", required = true) String url){
        ModelMap modelMap = new ModelMap();
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setAdType(Constant.HOMEPAGE_TOP_BANNER);
        bannerInfo.setImageName(imageName);
        bannerInfo.setUrl(url);
        boolean flag = bannerBusiness.createBanner(bannerInfo);
        modelMap.put("createFlag",flag);
        List<BannerInfo> topBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
        if(null != topBannerInfos){
            modelMap.put("topBannerInfos", topBannerInfos);
        }
        return new ModelAndView("/topbanner", modelMap);
    }

    @RequestMapping(value = "/topbannerinfo", method = {RequestMethod.GET})
    public @ResponseBody BannerInfo fetchTopBannerInfoById(@RequestParam(value = "id", required = true) int id,HttpServletResponse response){
        return bannerBusiness.fetchBannerInfoById(id);
    }

    @RequestMapping(value = "/updatetopbanner", method = {RequestMethod.POST})
    public ModelAndView updateTopBanner(@RequestParam(value = "id", required = true) int id,
                                    @RequestParam(value = "imageName", required = true) String imageName,
                                   @RequestParam(value = "url", required = true) String url){
        ModelMap modelMap = new ModelMap();
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setId(id);
        bannerInfo.setAdType(Constant.HOMEPAGE_TOP_BANNER);
        bannerInfo.setImageName(imageName);
        bannerInfo.setUrl(url);
        boolean flag = bannerBusiness.modifyBanner(bannerInfo);
        modelMap.put("updateFlag",flag);
        List<BannerInfo> topBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
        if(null != topBannerInfos){
            modelMap.put("topBannerInfos", topBannerInfos);
        }
        return new ModelAndView("/topbanner", modelMap);
    }

    @RequestMapping(value = "/deltopbanner", method = {RequestMethod.GET})
    public ModelAndView delTopBanner(@RequestParam(value = "id", required = true) int id,HttpServletResponse response){
        ModelMap modelMap = new ModelMap();
        boolean flag = bannerBusiness.deleteBanner(id);
        modelMap.put("deleteFlag",flag);
        List<BannerInfo> topBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
        if(null != topBannerInfos){
            modelMap.put("topBannerInfos", topBannerInfos);
        }
        return new ModelAndView("/topbanner", modelMap);
    }

    @RequestMapping(value = "/activitybanner", method = {RequestMethod.GET})
    public ModelAndView activityBanner(HttpServletRequest request, HttpSession session) {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            List<BannerInfo> activityBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_ACTIVITY_BANNER);
            if(null != activityBannerInfos){
                model.put("activityBannerInfos",activityBannerInfos);
            }
            return new ModelAndView("/activitybanner", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }

    @RequestMapping(value = "/floatwindow", method = {RequestMethod.GET})
    public ModelAndView floatWindow(HttpServletRequest request, HttpSession session) {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            return new ModelAndView("/floatwindow", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }


    @RequestMapping(value = "/bottombanner", method = {RequestMethod.GET})
    public ModelAndView bottomBanner(HttpServletRequest request, HttpSession session) {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            List<BannerInfo> bottomBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_FOOTER_AD);
            if(null != bottomBannerInfos){
                model.put("bottomBannerInfos",bottomBannerInfos);
            }
            return new ModelAndView("/bottombanner", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }

    @RequestMapping(value = "/activitymodule", method = {RequestMethod.GET})
    public ModelAndView activityModule(HttpServletRequest request, HttpSession session) {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            List<BannerInfo> activityModuleInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_ACTIVITY_MODULE);
            if(null != activityModuleInfos){
                model.put("activityModuleInfos",activityModuleInfos);
            }
            return new ModelAndView("/activitymodule", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }
}
