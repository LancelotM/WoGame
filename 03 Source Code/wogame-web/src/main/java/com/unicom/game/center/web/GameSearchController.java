package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.util.UrlUtil;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.SearchKeywordItemVo;
import com.unicom.game.center.vo.SearchKeywordsVo;
import com.unicom.game.center.vo.SearchResultItemVo;
import com.unicom.game.center.vo.SearchResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/search")
public class GameSearchController {


    private Logger logger = LoggerFactory.getLogger(GameSearchController.class);
    private static final String SESSION_KEY_REFERER = "Referer";

    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private ZTEService zteService;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request,HttpSession session) {
        if(null == session){
        	session = HttpClientUtil.fetchSession(request);
        }
        SearchKeywordsVo vo = zteService.readSearchAllKeywords();
        String refer = request.getHeader("Referer");
        if (refer != null) {
            if (refer.contains("/gameInfo")) {
                model.addAttribute("referUrl", request.getContextPath() + "/main;jsessionid=" + request.getSession().getId());
            } else {
                request.getSession().setAttribute(SESSION_KEY_REFERER, refer);
                model.addAttribute("referUrl", UrlUtil.buildUrlWithRandomKey(refer));
            }

        }

        model.addAttribute("list", vo == null ? new ArrayList<SearchKeywordItemVo>() : vo.getHotWordData());
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("搜索");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);
        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "search/category";
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchKeywordItemVo> search(@RequestParam("keyword") String keyword) throws Exception {
        SearchKeywordsVo vo = zteService.readSearchKeywords(URLDecoder.decode(URLDecoder.decode(keyword, "UTF-8"), "UTF-8"));

        return vo == null ? new ArrayList<SearchKeywordItemVo>() : vo.getHotWordData();
    }

    @RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchResultItemVo> ajaxSearch(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNum") Integer pageNum,
                                               HttpServletRequest request) throws Exception {

        String utf8Keyword = URLDecoder.decode(URLDecoder.decode(keyword, "UTF-8"), "UTF-8");

        // 记录Log
        HttpSession session = request.getSession();
        String channel = session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID).toString();
        if (null == channel) {
            channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        }
        String[] logData = new String[]{"40", StringUtils.rightPad(channel, 3, " "), utf8Keyword};
        statisticsLogger.info(StringUtils.join(logData, ""));

        //根据搜索字搜索游戏
        SearchResultVo vo = zteService.readSearchResult(utf8Keyword, pageNum);

        return vo.getSearchList();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> handleException(Exception e) {
        logger.error("解码Keyword出错", e);

        Map<String, String> error = Maps.newHashMap();
        error.put("status", "-99");

        return error;
    }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String searchResult(@RequestParam("keyword") String keyword, Model model, HttpServletRequest request, HttpSession session) {
        if(null == session){
        	session = HttpClientUtil.fetchSession(request);
        }

        try {
            model.addAttribute("keyword", URLDecoder.decode(keyword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            model.addAttribute("keyword", keyword);
        }

        String date = DateUtils.formatDateToString(new Date(),"yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("搜索");
        serverLogInfo.setChannelCode(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE).toString());
        serverLogInfo.setIp(session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP).toString());
        serverLogInfo.setDate(date);
        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "search/result";
    }


}
