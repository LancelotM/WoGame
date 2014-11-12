package com.unicom.game.center.web;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UnicomLogServer unicomLogServer;


    private Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @RequestMapping(value = "/ajaxList", method = RequestMethod.GET)
    @ResponseBody
    public ActivityInfoVo activityInfoList(@RequestParam(value = "pageNum", required = false,defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(pageNum, size);
         ActivityInfoVo ac= activityInfoListVo.getActivityInfoVo();
        ActivityInfoItemVo a=new ActivityInfoItemVo();
        a.setId(231);
        a.setType(1);
        a.setTitle("网游彻夜狂欢，双11的棍棍族庆典");
        a.setIntro("网游彻夜狂欢，双11的棍棍族庆典");
        Banner b=new Banner();
        b.setLinkId("230");
        b.setResType(4);
        b.setBannerUrl("http://wgfiles.wostore.cn/images/2014/11/a5f601bdc210461b81b63f4c803ebe57");
        a.setBanner(b);
        a.setCornerMark(new Integer("0").intValue());
        a.setCreateTime(1415609553000L);
        a.setStartTime(1415548800000l);
        a.setEndTime(1415721600000l);
         ac.getActivityInfoItemVoList().add(0,a);
        return ac;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpSession session) {
        if(null == session){
            session = request.getSession(true);
        }
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("活动列表");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "activity/list";
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String fetchInfoDetail(@RequestParam("id") int id, Model model, HttpServletRequest request, HttpSession session) {
        if(null == session){
            session = request.getSession(true);
        }
        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);
        InfoDetailVo i = infoDetailListVo.getInfoDetailVo();
        model.addAttribute("activityContent", i);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("活动详情");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "activity/detail";
    }

}
