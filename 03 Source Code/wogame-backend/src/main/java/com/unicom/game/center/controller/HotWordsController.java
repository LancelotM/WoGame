package com.unicom.game.center.controller;

import java.util.List;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.model.ChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.KeywordBusiness;
import com.unicom.game.center.model.KeywordInfo;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-6-26
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HotWordsController {

    @Autowired
    private ChannelInfoBusiness channelService;

    @Autowired
    private KeywordBusiness keywordService;


    @RequestMapping(value = "/getAllKeyWowrd", method = {RequestMethod.GET})
    public ModelAndView getAllKeyWowrd(){
    	ModelMap model = new ModelMap();
        List<KeywordInfo> keywords = keywordService.fetchTopSearchKeyword();
        model.put("keywords",keywords);
        return new ModelAndView("/keyword", model); 
    }
}
