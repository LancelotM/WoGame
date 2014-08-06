package com.unicom.game.center.controller;

import com.unicom.game.center.model.ChannelInfo;
import org.springframework.beans.factory.annotation.Value;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-29
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UploadFileControl {

    @Value("#{properties['upload.path']}")
    private String uploadPath;

    @RequestMapping(value = "/uploadPage", method = {RequestMethod.GET})
    public ModelAndView getAllKeyWowrd(HttpServletRequest request,HttpSession session){
        if(null == session){
            session = request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            return new ModelAndView("/upload");
        }else{
            return new ModelAndView("/index");
        }

    }

    @RequestMapping(value="/uploadFileHandel",method ={RequestMethod.POST})
    public @ResponseBody String fileHandel(String updateType, String appid, String channelId, HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        // 存储fileUrl
        List<String> uploadPaths = new ArrayList<String>();
        String fileUrl = "";
        try {
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
            {
                MultipartFile file = entity.getValue();

                byte[] fileByte = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(uploadPath+File.separator+file.getOriginalFilename()));
                stream.write(fileByte);
                stream.close();
            }
            fileUrl = "true";
        } catch (IOException e) {
            e.printStackTrace();
            fileUrl = "false";
        }
        return fileUrl;
    }

}
