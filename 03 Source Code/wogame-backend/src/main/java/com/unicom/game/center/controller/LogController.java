package com.unicom.game.center.controller;

import java.util.List;

import com.unicom.game.center.model.GameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.GameTrafficBusiness;
import com.unicom.game.center.business.LoginInfoBusiness;
import com.unicom.game.center.business.PageTrafficBusiness;
import com.unicom.game.center.model.GameDisplayModel;
import com.unicom.game.center.model.JsonParent;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-6-26
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogController {

    @Autowired
    private LoginInfoBusiness logInfoService;

    @Autowired
    private PageTrafficBusiness pageTrafficService;

    @Autowired
    private GameTrafficBusiness gameTrafficService;

    @RequestMapping(value = "/getlog", method = {RequestMethod.POST})
    public ModelAndView getLog(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        ModelAndView modelView = new ModelAndView();
        if("1".equals(type)){
            modelView.setViewName("log");
        }else if("2".equals(type)){
            modelView.setViewName("monthLog");
        }
        modelView.addObject("channelId",channelID);
        return modelView;
    }

    @RequestMapping(value = "/userLoginLog", method = {RequestMethod.POST})
    public @ResponseBody JsonParent userLoginCount(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        int dateType = Integer.parseInt(type);
        JsonParent displayModel = null;
        if(dateType == 1){
            displayModel = logInfoService.fetchLoginInfoByDate(channelID);
        }else if(dateType == 2){
            displayModel = logInfoService.fetchLoginInfoByMonth(channelID);
        }
        return displayModel;
    }

    @RequestMapping(value = "/pageTrafficLog", method = {RequestMethod.POST})
    public @ResponseBody JsonParent pageTrafficCount(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        int dateType = Integer.parseInt(type);
        JsonParent displayModel = null;
        if(dateType == 1){
            displayModel = pageTrafficService.fetchTrafficInfoByDate(channelID);
        }else if(dateType == 2){
            displayModel = pageTrafficService.fetchTrafficInfoByMonth(channelID);
        }
        return displayModel;
    }

    @RequestMapping(value = "/firstPageBannerLog", method = {RequestMethod.POST})
    public @ResponseBody List<List<GameInfo>>  firstPageBannerLog(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        int dateType = Integer.parseInt(type);
        List<List<GameInfo>>  gameInfos = null;
        if(dateType == 1){
            gameInfos = gameTrafficService.getBannerDateModel(channelID);
        }else if(dateType == 2){
            gameInfos = gameTrafficService.getBannerMothModel(channelID);
        }
        return gameInfos;
    }

    @RequestMapping(value = "/topGameLog", method = {RequestMethod.POST})
    public @ResponseBody List<GameDisplayModel>  topGameLog(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        int dateType = Integer.parseInt(type);
        List<GameDisplayModel>  gameInfos = null;
        if(dateType == 1){
            gameInfos = gameTrafficService.getGameDayModel(channelID);
        }else if(dateType == 2){
            gameInfos = gameTrafficService.getGameMonthModel(channelID);
        }
        return gameInfos;
    }
}
