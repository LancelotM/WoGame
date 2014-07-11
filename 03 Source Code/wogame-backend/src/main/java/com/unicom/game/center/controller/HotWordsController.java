package com.unicom.game.center.controller;

import java.util.List;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.business.DownLoadInfoBusiness;
import com.unicom.game.center.db.domain.DownloadInfoDomain;
import com.unicom.game.center.log.model.DownloadInfoModel;
import com.unicom.game.center.model.ChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private DownLoadInfoBusiness downLoadService;


    @RequestMapping(value = "/getAllKeyWowrd", method = {RequestMethod.GET})
    public ModelAndView getAllKeyWowrd(){
    	ModelMap model = new ModelMap();

        List<KeywordInfo> keywords = keywordService.fetchTopSearchKeyword();
        DownloadInfoModel downloadModel = downLoadService.getDownloadInfos(null, null, null, 1);
        model.put("totalPage", downloadModel.getTotalPage());
        model.put("keywords",keywords);
        model.put("downloadInfoDomains",downloadModel.getDownloadInfoDomains());
        return new ModelAndView("/keyword", model); 
    }

    @RequestMapping(value="/getDownloadInfo",method={RequestMethod.POST})
    public @ResponseBody DownloadInfoModel getDownloadInfo(@RequestParam(value="channelId",required = false) int channelId,
                                                    @RequestParam(value="dateStr",required = false) String dateStr,
                                                    @RequestParam(value="page",required = true) int page,
                                                    @RequestParam(value="gameName",required = false) String gameName){
        return downLoadService.getDownloadInfos(gameName,channelId,dateStr,page);
    }
}
