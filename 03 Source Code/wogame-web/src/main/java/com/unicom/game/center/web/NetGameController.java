package com.unicom.game.center.web;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.vo.InfoDetailListVo;
import com.unicom.game.center.vo.InfoDetailVo;
import com.unicom.game.center.vo.NetGameInfoListVo;
import com.unicom.game.center.vo.NetGameInfoVo;
import com.unicom.game.center.vo.NetGameServerItemVo;
import com.unicom.game.center.vo.NetGameServerListVo;
import com.unicom.game.center.vo.NetGameServerVo;

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

        return netGameServerListVo.getNetGameInfoVo();
    }

    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public String netGameServerList(HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("新服预告");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));

        return "netGame/server";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String netGameInfoList(Model model, HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(1, 1);
        List<NetGameServerItemVo> list = netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList();
        model.addAttribute("list", list);

        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("网游资讯");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "netGame/info";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String fetchInfoDetail(@RequestParam("id") int id, Model model,HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);

        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);
        InfoDetailVo i = infoDetailListVo.getInfoDetailVo();
        model.addAttribute("ac", i);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("网游资讯详情");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "netGame/detail";
    }

}
