package com.unicom.game.center.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.BannerBusiness;
import com.unicom.game.center.db.domain.HomepageConfigDomain;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.Logging;
import com.unicom.game.center.utils.Operator;
import com.unicom.game.center.utils.SFTPHelper;

@Controller
public class BannerController {

    @Autowired
    private BannerBusiness bannerBusiness;

    @Value("#{properties['banner.image.path']}")
    private String imagePath;

    @Autowired
    private SFTPHelper sftpHelper;

    private String uploadImage(MultipartFile file) throws IOException {
        if(file == null || file.getBytes().length == 0){
        	Logging.logError("Image file is null, can't upload image.");
            return null;
        }
        String result = String.valueOf(System.currentTimeMillis());
        boolean flag = sftpHelper.uploadFile(file.getInputStream(), (imagePath + result));
        if(!flag){
        	Logging.logError("Upload image failure.");
            return null;
        }
        return Constant.IMAGE_BASE_URL + result;
    }

    @RequestMapping(value = "/fetchtobanner", method = {RequestMethod.GET})
    public @ResponseBody BannerInfo fetchTopBannerInfoById(@RequestParam(value = "id", required = true) int id,HttpServletResponse response){
        return bannerBusiness.fetchBannerInfoById(id);
    }

    @RequestMapping(value = "/saveorupdatebanner", method = {RequestMethod.POST})
    public ModelAndView createTopBanner(HttpServletRequest request, BannerInfo bannerInfo, HttpSession session) throws IOException {
        if(session == null){
            session = request.getSession(true);
        }

        String random = (String) session.getAttribute("random");
        if(null == random){
            session.setAttribute("random", bannerInfo.getRandom());
        }else{
            if(random.equals(bannerInfo.getRandom())){
                ModelAndView modelAndView = jumpPage(bannerInfo.getAdType().intValue());
                return modelAndView;
            }else{
                session.setAttribute("random", bannerInfo.getRandom());
            }
        }

        String imageName = null;
        HomepageConfigDomain homepageConfigDomain = null;
        Integer id =  bannerInfo.getId();
        //create
        if(id == null || id == 0) {
            imageName = uploadImage(bannerInfo.getFile());
            if(imageName != null){
                bannerInfo.setImageName(imageName);
            }
        }else{
            if(!bannerInfo.getImageName().startsWith("http://")){
                imageName = uploadImage(bannerInfo.getFile());
            }else{
                imageName = bannerInfo.getImageName();
            }
        }

        if(imageName != null){
            bannerInfo.setImageName(imageName);
            bannerInfo.setAdType(bannerInfo.getAdType());
            if(bannerInfo.getAdType() == 4){
                if(bannerInfo.getCategory().equals("bigCategory")){
                    bannerInfo.setPosition(1);
                }else if(bannerInfo.getCategory().equals("smallCategory")){
                    bannerInfo.setPosition(2);
                }
                bannerInfo.setStatus(true);
            }else if(bannerInfo.getAdType() == 1){
                if(bannerInfo.getRadio().equals("show")){
                    bannerInfo.setStatus(true);
                }else{
                    bannerInfo.setStatus(false);
                }
            }else{
                bannerInfo.setStatus(true);
            }

            if(id == null || id == 0) {
                homepageConfigDomain = bannerBusiness.convertToBannerDomain(bannerInfo, Operator.create);
                bannerBusiness.save(homepageConfigDomain);
            }else{
                homepageConfigDomain = bannerBusiness.convertToBannerDomain(bannerInfo, Operator.update);
                bannerBusiness.update(homepageConfigDomain);
            }
        }else{
            Logging.logError("upload image error");
        }

        ModelAndView modelAndView = jumpPage(bannerInfo.getAdType().intValue());
        return modelAndView;
    }

    private ModelAndView jumpPage(int type){
        String viewValue = null;
        String bannerInfos = null;
        ModelMap modelMap = new ModelMap();
        switch (type){
            case 1 :
                viewValue = "floatwindow";
                bannerInfos = "floatWindowInfos";
                break;
            case 2 :
                viewValue = "topbanner";
                bannerInfos = "topBannerInfos";
                break;
            case 3 :
                viewValue = "activitymodule";
                bannerInfos = "activityModuleInfos";
                break;
            case 4 :
                viewValue = "activitybanner";
                bannerInfos = "activityBannerInfos";
                break;
            case 5 :
                viewValue = "bottombanner";
                bannerInfos = "bottomBannerInfos";
                break;
            default:
                viewValue = "topbanner";
                bannerInfos = "topBannerInfos";
                break;
        }
        List<BannerInfo> bannerList = bannerBusiness.fetchBannerInfos(type);
        if(null != bannerList){
            modelMap.put(bannerInfos, bannerList);
        }
        return new ModelAndView(viewValue, modelMap);

    }

    @RequestMapping(value = "/topbanner", method = RequestMethod.GET)
    public ModelAndView topBanner(HttpServletRequest request, HttpSession session) throws IOException {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean showFlag = (Boolean)session.getAttribute("showBanner");
        if(null != session && null != showFlag && showFlag.booleanValue()){
            List<BannerInfo> topBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
            if(null != topBannerInfos){
                model.put("topBannerInfos", topBannerInfos);
            }
            return new ModelAndView("/topbanner", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }

    @RequestMapping(value = "/activitybanner", method = {RequestMethod.GET})
    public ModelAndView activityBanner(HttpServletRequest request, HttpSession session) {
        ModelMap model = new ModelMap();
        if(session == null){
            request.getSession(true);
        }
        Boolean showFlag = (Boolean)session.getAttribute("showBanner");
        if(null != session && null != showFlag && showFlag.booleanValue()){
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
        Boolean showFlag = (Boolean)session.getAttribute("showBanner");
        if(null != session && null != showFlag && showFlag.booleanValue()){
            List<BannerInfo> floatWindowInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_AD);
            if(null != floatWindowInfos){
                model.put("floatWindowInfos",floatWindowInfos);
            }
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
        Boolean showFlag = (Boolean)session.getAttribute("showBanner");
        if(null != session && null != showFlag && showFlag.booleanValue()){
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
        Boolean showFlag = (Boolean)session.getAttribute("showBanner");
        if(null != session && null != showFlag && showFlag.booleanValue()){
            List<BannerInfo> activityModuleInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_ACTIVITY_MODULE);
            if(null != activityModuleInfos){
                model.put("activityModuleInfos",activityModuleInfos);
            }
            return new ModelAndView("/activitymodule", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }

    @RequestMapping(value = "/delbanner", method = {RequestMethod.GET})
    public @ResponseBody List<BannerInfo> delBanner(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "type", required = true) int type,HttpServletResponse response){
        ModelMap modelMap = new ModelMap();
        boolean flag = bannerBusiness.deleteBanner(id);
        modelMap.put("deleteFlag",flag);
        List<BannerInfo> bannerInfos = bannerBusiness.fetchBannerInfos(type);
        if(null == bannerInfos){
            bannerInfos = new ArrayList<BannerInfo>();
        }
        return bannerInfos;
    }



    @RequestMapping(value = "/sortbanner", method = {RequestMethod.GET})
    public ModelAndView sortBanner(@RequestParam(value = "numString", required = true) String numString, @RequestParam(value = "sortString", required = true) String sortString,HttpServletResponse response){
        ModelMap modelMap = new ModelMap();
        boolean flag = true;
        long timestamp = System.currentTimeMillis();
        String[] idArr = numString.split(",");
        String[] sortArr = sortString.split(",");
        HashMap<Integer,Integer> sortMap = new HashMap<Integer, Integer>();
        for(int i= 0;i<idArr.length;i++){
            sortMap.put(Integer.parseInt(idArr[i]),Integer.parseInt(sortArr[i]));
        }
        //sort
        Iterator iterator = sortMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            int id = Integer.parseInt(entry.getKey().toString());
            int position = Integer.parseInt(entry.getValue().toString());
            BannerInfo bannerInfo = bannerBusiness.fetchBannerInfoById(id);
            bannerInfo.setPosition(position);
            bannerBusiness.modifyBanner(bannerInfo, String.valueOf(timestamp));
        }
        List<BannerInfo> BannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_BANNER);
        if(null != BannerInfos){
            modelMap.put("topBannerInfos", BannerInfos);
        }
        return new ModelAndView("/topbanner", modelMap);
    }

}
