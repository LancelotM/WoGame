package com.unicom.game.center.web;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.NewListVo;
import com.unicom.game.center.vo.NewVo;
import com.unicom.game.center.vo.WeekHotItemVo;
import com.unicom.game.center.vo.WeekHotVo;
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

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/newGame")
public class NewGameController {

    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession session) {

        /*Map<String, Object> loggerData = Maps.newHashMap();
        loggerData.put("pageId", "newGame");
        loggerData.put(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID));
        statisticsLogger.log("pageTraffic", loggerData);

        NewListVo newListVo = gameService.readNewList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("newList", newListVo.getAppList());*/
        return "newGame";
    }

    @RequestMapping(value = "/ajaxList", method = RequestMethod.GET)
    @ResponseBody
    public NewVo ajaxList(@RequestParam(value = "pageNum",required = false, defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        NewListVo newListVo = gameService.readNewList(pageNum, size);

        return newListVo.getDataList();
    }


    @RequestMapping(value = "ajaxHotList", method = RequestMethod.GET)
    @ResponseBody
    public WeekHotItemVo ajaxList(@RequestParam(value = "pageNum",required = false,defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        WeekHotVo weekHotVo = gameService.readWeekHotList(pageNum, size);

        return weekHotVo.getData();
    }


    @RequestMapping(value = "/topNewest", method = RequestMethod.GET)
    public String topNewest(HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("最新榜");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "newGame/topNewest";
    }

    @RequestMapping(value = "/topHotest", method = RequestMethod.GET)
    public String topHotest(HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("飙升榜");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "newGame/topHotest";
    }


}
