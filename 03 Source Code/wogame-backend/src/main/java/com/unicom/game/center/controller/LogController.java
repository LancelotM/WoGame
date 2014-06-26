package com.unicom.game.center.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.GameTrafficBusiness;
import com.unicom.game.center.business.LoginInfoBusiness;
import com.unicom.game.center.business.PageTrafficBusiness;
import com.unicom.game.center.model.GameInfo;

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

    @RequestMapping(value = "/getBannerTraffic", method = {RequestMethod.GET})
    public ModelAndView getBannerTraffic(@RequestParam(value="type",required=true) String type,@RequestParam(value="channelId",required = true) int channelID){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("log");
        int intType = Integer.parseInt(type);
        List<GameInfo> gameInfos = new ArrayList<GameInfo>();
        modelView.addObject("gameInfos",gameInfos);
        return modelView;
    }
}
