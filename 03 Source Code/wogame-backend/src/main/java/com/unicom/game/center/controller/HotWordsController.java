package com.unicom.game.center.controller;

import com.unicom.game.center.business.AccountBusiness;
import com.unicom.game.center.business.KeywordBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.KeywordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    private KeywordBusiness keywordService;


    @RequestMapping(value = "/getAllKeyWowrd", method = {RequestMethod.GET})
    public ModelAndView getAllKeyWowrd(){
        ModelAndView modelView = new ModelAndView();
        List<KeywordInfo> keywords = keywordService.fetchTopSearchKeyword();
        modelView.setViewName("hotWord");
        modelView.addObject("keywords",keywords);
        return modelView;
    }
}
