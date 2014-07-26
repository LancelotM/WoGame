package com.unicom.game.center.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.business.DownLoadInfoBusiness;
import com.unicom.game.center.business.KeywordBusiness;
import com.unicom.game.center.log.model.DownloadInfoModel;
import com.unicom.game.center.model.KeywordInfo;
import com.unicom.game.center.utils.DateUtils;

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
    public ModelAndView getAllKeyWowrd(@RequestParam(value="channelId",required = true) Integer channelId){
    	ModelMap model = new ModelMap();

        List<KeywordInfo> keywords = keywordService.fetchTopSearchKeyword(channelId);
        int yesterdayCount = keywordService.getDayCount(channelId,DateUtils.formatDateToString(DateUtils.getDayByInterval(new Date(), -1),"yyyy-MM-dd"));
        int totalCount = keywordService.getThirtyDayCount(channelId);
        model.put("yesterdayCount",yesterdayCount);
        model.put("totalCount",totalCount);
        model.put("keywords",keywords);
        return new ModelAndView("/keyword", model); 
    }

    @RequestMapping(value="/getDownloadInfo",method={RequestMethod.POST})
    public @ResponseBody DownloadInfoModel getDownloadInfo(@RequestParam(value="channelId",required = false) String channelId,
                                                    @RequestParam(value="startDate",required = false) String startDate,
                                                    @RequestParam(value="endDate",required = false) String endDate,
                                                    @RequestParam(value="page",required = true) int page,
                                                    @RequestParam(value="rowsPerPage",required = true) Integer rowsPerPage){
        return downLoadService.getDownloadInfos(channelId,startDate,endDate,page,rowsPerPage);
    }
}
