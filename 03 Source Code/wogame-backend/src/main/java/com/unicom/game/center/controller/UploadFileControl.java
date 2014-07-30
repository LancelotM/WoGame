package com.unicom.game.center.controller;

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

    @RequestMapping(value = "/uploadPage", method = {RequestMethod.GET})
    public ModelAndView getAllKeyWowrd(){
        return new ModelAndView("/upload");
    }

    @RequestMapping(value="/uploadFileHandel",method ={RequestMethod.POST})
    public @ResponseBody String fileHandel(String updateType, String appid, String channelId, HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        System.out.println(updateType+"=="+appid+"=="+channelId);
        // 存储fileUrl
        List<String> uploadPaths = new ArrayList<String>();
        String fileUrl = null;
        try {
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
            {
                MultipartFile file = entity.getValue();
                System.out.println(file.getName());
                System.out.println(file.getContentType());
                System.out.println(file.getOriginalFilename());

                byte[] fileByte = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream("E:\\xin\\soft\\testImg\\"+file.getOriginalFilename()));
                stream.write(fileByte);
                stream.close();

            }
            fileUrl =  "上传成功！";
        } catch (IOException e) {
            e.printStackTrace();
            fileUrl =  "上传失败！";
        }
        return fileUrl;
    }

}
