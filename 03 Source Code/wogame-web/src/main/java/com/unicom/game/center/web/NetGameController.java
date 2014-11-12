package com.unicom.game.center.web;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/netgame")
public class NetGameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @RequestMapping(value = "/ajaxserver", method = RequestMethod.GET)
    @ResponseBody
    public NetGameServerVo netGameServerList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(pageNum, size);

        return netGameServerListVo.getNetGameServerVo();
    }

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public NetGameInfoVo netGameInfoList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;
        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }
        NetGameInfoListVo netGameServerListVo = gameService.readNetGameInfoList(pageNum, size);
        NetGameInfoVo ac= netGameServerListVo.getNetGameInfoVo();
        NetGameInfoItemVo a=new NetGameInfoItemVo();
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
        ac.getNetGameInfoItemVoList().add(0,a);
        return ac;
    }

    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public String netGameServerList(HttpServletRequest request, HttpSession session) {
        if(null == session){
            session = request.getSession(true);
        }
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("新服预告");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));

        return "netGame/server";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String netGameInfoList(Model model, HttpServletRequest request, HttpSession session) {
        if(null == session){
            session = request.getSession(true);
        }

        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(1, 1);
        List<NetGameServerItemVo> list = netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList();
        model.addAttribute("list", list);

        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("网游资讯");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "netGame/info";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String fetchInfoDetail(@RequestParam("id") int id, Model model,HttpServletRequest request, HttpSession session) {
        if(null == session){
            session = request.getSession(true);
        }

        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);
        InfoDetailVo i = infoDetailListVo.getInfoDetailVo();
        model.addAttribute("ac", i);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("网游资讯详情");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "netGame/detail";
    }

}
