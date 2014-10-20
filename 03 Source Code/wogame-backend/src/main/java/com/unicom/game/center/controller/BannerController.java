package com.unicom.game.center.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

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
    public ModelAndView createTopBanner(HttpServletRequest request){
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	InputStream uploadedStream = null;

    	// Parse the request
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
    			String value = item.getString();
   			
    			System.out.println("===========================" + name + ":" + value + "===============================");
    	    } else {
			    String fileName = item.getName();
			    System.out.println("===========================" + fileName  + "===============================");
			    
				try {
					uploadedStream = item.getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}			
    	    }
    	}

    	ModelMap modelMap = new ModelMap();
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setAdType(Constant.HOMEPAGE_TOP_BANNER);
        bannerInfo.setUrl("");
        boolean flag = true;
        long timestamp = System.currentTimeMillis();
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
