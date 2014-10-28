package com.unicom.game.center.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.BannerBusiness;
import com.unicom.game.center.model.BannerInfo;
import com.unicom.game.center.utils.Constant;
import com.unicom.game.center.utils.SFTPHelper;

@Controller
public class BannerController {

    @Autowired
    private BannerBusiness bannerBusiness;
    
	@Value("#{properties['banner.image.path']}")
	private String imagePath;
		
	@Autowired
	private SFTPHelper sftpHelper;

    @RequestMapping(value = "/topbanner", method = {RequestMethod.GET})
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

    @RequestMapping(value = "/createtopbanner", method = {RequestMethod.POST})
    public ModelAndView createTopBanner(HttpServletRequest request){
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	InputStream uploadedStream = null;
        ModelMap modelMap = new ModelMap();
        Map<String,String> bannerMap = new HashMap<String,String>();

    	// Parse the request
        uploadedStream = dataStream(request, bannerMap, upload);

        BannerInfo bannerInfo = new BannerInfo();
        Iterator iterator = bannerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            if(key.equals("url")){
                bannerInfo.setUrl(val);
            }else if(key.equals("imageName")){
                bannerInfo.setImageName(val);
            }else if(key.equals("id")){
                bannerInfo.setId(Integer.parseInt(val));
            }
        }
        bannerInfo.setStatus(true);
        bannerInfo.setAdType(Constant.HOMEPAGE_TOP_BANNER);
        bannerInfo.setPosition(0);
        boolean flag = true;

        uploadImage(bannerInfo,modelMap,uploadedStream);

        bannerMap.clear();
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

    @RequestMapping(value = "/createbanner", method = {RequestMethod.POST})
    public ModelAndView createBanner(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        InputStream uploadedStream = null;
        ModelMap modelMap = new ModelMap();
        Map<String,String> bannerMap = new HashMap<String,String>();

        // Parse the request
        uploadedStream = dataStream(request, bannerMap, upload);

        BannerInfo bannerInfo = new BannerInfo();
        Iterator iterator = bannerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            if(key.equals("titleCode")){
                bannerInfo.setTitle(val);
            }else if(key.equals("imageName")){
                bannerInfo.setImageName(val);
            }else if(key.equals("categoryCode")){
                if(val.equals("bigCategory")){
                    bannerInfo.setPosition(1);
                }else if(val.equals("smallCategory")){
                    bannerInfo.setPosition(2);
                }
            }
            else if(key.equals("url")){
                bannerInfo.setUrl(val);
            }else if(key.equals("id")){
                bannerInfo.setId(Integer.parseInt(val));
            }
        }
        bannerInfo.setStatus(true);
        bannerInfo.setAdType(Constant.HOMEPAGE_ACTIVITY_BANNER);

        //To upload pictures
        uploadImage(bannerInfo,modelMap,uploadedStream);

        bannerMap.clear();
        List<BannerInfo> activityBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_ACTIVITY_BANNER);
        if(null != activityBannerInfos){
            modelMap.put("activityBannerInfos", activityBannerInfos);
        }
        return new ModelAndView("/activitybanner", modelMap);
    }

    @RequestMapping(value = "/delbanner", method = {RequestMethod.GET})
    public @ResponseBody List<BannerInfo> delBanner(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "type", required = true) int type,HttpServletResponse response){
        ModelMap modelMap = new ModelMap();
        boolean flag = bannerBusiness.deleteBanner(id);
        modelMap.put("deleteFlag",flag);
        List<BannerInfo> bannerInfos = bannerBusiness.fetchBannerInfos(type);

        return bannerInfos;
    }
    
    @RequestMapping(value = "/showbanner", method = {RequestMethod.GET})
    public @ResponseBody List<BannerInfo> showBanner(@RequestParam(value = "type", required = true) int type,HttpServletResponse response){
        List<BannerInfo> bannerInfos = bannerBusiness.fetchBannerInfos(type);

        return bannerInfos;
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

    @RequestMapping(value = "/addfloatwindow", method = {RequestMethod.POST})
    public ModelAndView addFloatWindow(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        InputStream uploadedStream = null;
        ModelMap modelMap = new ModelMap();
        Map<String,String> floatMap = new HashMap<String,String>();

        // Parse the request
        uploadedStream = dataStream(request, floatMap, upload);

        BannerInfo bannerInfo = new BannerInfo();
        Iterator iterator = floatMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            if(key.equals("urlCode")){
                bannerInfo.setUrl(val);
            }else if(key.equals("imageName")){
                bannerInfo.setImageName(val);
            }else if(key.equals("contentCode")){
                bannerInfo.setDescription(val);
            }else if(key.equals("radio")){
                if(val.equals("show")){
                    bannerInfo.setStatus(true);
                }else{
                    bannerInfo.setStatus(false);
                }
            }else if(key.equals("id")){
                bannerInfo.setId(Integer.parseInt(val));
            }
        }
        bannerInfo.setPosition(0);
        bannerInfo.setAdType(Constant.HOMEPAGE_TOP_AD);

        //To upload pictures
        uploadImage(bannerInfo,modelMap,uploadedStream);

        floatMap.clear();
        List<BannerInfo> floatWindowInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_TOP_AD);
        if(null != floatWindowInfos){
            modelMap.put("floatWindowInfos", floatWindowInfos);
        }
        return new ModelAndView("/floatwindow", modelMap);
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

    @RequestMapping(value = "/addbottombanner", method = {RequestMethod.POST})
    public ModelAndView addBottomBanner(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        InputStream uploadedStream = null;
        ModelMap modelMap = new ModelMap();
        Map<String,String> bannerMap = new HashMap<String,String>();

        // Parse the request
        uploadedStream = dataStream(request, bannerMap, upload);

        BannerInfo bannerInfo = new BannerInfo();
        Iterator iterator = bannerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            if(key.equals("urlCode")){
                bannerInfo.setUrl(val);
            }else if(key.equals("imageName")){
                bannerInfo.setImageName(val);
            }else if(key.equals("contentCode")){
                bannerInfo.setDescription(val);
            }else if(key.equals("id")){
                bannerInfo.setId(Integer.parseInt(val));
            }
        }
        bannerInfo.setStatus(true);
        bannerInfo.setPosition(0);
        bannerInfo.setAdType(Constant.HOMEPAGE_FOOTER_AD);

        //To upload pictures
        uploadImage(bannerInfo,modelMap,uploadedStream);

        bannerMap.clear();
        List<BannerInfo> bottomBannerInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_FOOTER_AD);
        if(null != bottomBannerInfos){
            modelMap.put("bottomBannerInfos", bottomBannerInfos);
        }
        return new ModelAndView("/bottombanner", modelMap);
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

    @RequestMapping(value = "/addactivitymodule", method = {RequestMethod.POST})
    public ModelAndView addActivityModule(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        InputStream uploadedStream = null;
        ModelMap modelMap = new ModelMap();
        Map<String,String> bannerMap = new HashMap<String,String>();

        // Parse the request
        uploadedStream = dataStream(request, bannerMap, upload);

        BannerInfo bannerInfo = new BannerInfo();
        Iterator iterator = bannerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            if(key.equals("titleCode")){
                bannerInfo.setTitle(val);
            }else if(key.equals("imageName")){
                bannerInfo.setImageName(val);
            }else if(key.equals("introduceCode")){
                bannerInfo.setDescription(val);
            }else if(key.equals("urlCode")){
                bannerInfo.setUrl(val);
            }else if(key.equals("id")){
                bannerInfo.setId(Integer.parseInt(val));
            }
        }
        bannerInfo.setStatus(true);
        bannerInfo.setPosition(0);
        bannerInfo.setAdType(Constant.HOMEPAGE_ACTIVITY_MODULE);

        //To upload pictures
        uploadImage(bannerInfo,modelMap,uploadedStream);

        bannerMap.clear();
        List<BannerInfo> activityModuleInfos = bannerBusiness.fetchBannerInfos(Constant.HOMEPAGE_ACTIVITY_MODULE);
        if(null != activityModuleInfos){
            modelMap.put("activityModuleInfos", activityModuleInfos);
        }
        return new ModelAndView("/activitymodule", modelMap);
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

    private InputStream dataStream(HttpServletRequest request,Map<String,String> bannerMap,ServletFileUpload upload){
        InputStream uploadedStream = null;
        List<?> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }

        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();

            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = null;
                try {
                    value = item.getString("utf-8");
                }catch (Exception e){
                    e.printStackTrace();
                }
                bannerMap.put(name,value);
            } else {
                String fileName = item.getName();
                bannerMap.put("imageName",fileName);

                try {
                    uploadedStream = item.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadedStream;
    }

    private void uploadImage( BannerInfo bannerInfo, ModelMap modelMap, InputStream uploadedStream){
        if(bannerInfo.getId() == null){
            long timestamp = System.currentTimeMillis();
            boolean flag = true;
            try {
                flag = sftpHelper.uploadFile(uploadedStream, (imagePath + String.valueOf(timestamp)));
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }

            if(flag){
                flag = bannerBusiness.createBanner(bannerInfo, String.valueOf(timestamp));
            }
            modelMap.put("createFlag",flag);
        }else{
            long timestamp = System.currentTimeMillis();
            boolean flag = false;
            try {
                if(bannerInfo.getImageName().startsWith("http://")){
                    bannerInfo.setImageName(bannerInfo.getImageName());
                }else{
                    flag = sftpHelper.uploadFile(uploadedStream, (imagePath + String.valueOf(timestamp)));
                    if(flag){
                        bannerInfo.setImageName("http://channel.wostore.cn:8080/images/" + String.valueOf(timestamp));
                    }else{
                        flag = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
            if(flag){
                flag = bannerBusiness.modifyBanner(bannerInfo, String.valueOf(timestamp));
            }
            modelMap.put("updateFlag",flag);
        }
    }
}
