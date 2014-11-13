package com.unicom.game.center.web;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.SubjectDetailListVo;
import com.unicom.game.center.vo.SubjectDetailVo;
import com.unicom.game.center.vo.SubjectListVo;
import com.unicom.game.center.vo.SubjectVo;
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

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @RequestMapping(value = "/ajaxlist", method = RequestMethod.GET)
    @ResponseBody
    public SubjectVo subjectList(@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        SubjectListVo subjectListVo = gameService.readSubjectList(pageNum, size);

        return subjectListVo.getSubjectList();
    }

    @RequestMapping(value = "/gamelist", method = RequestMethod.GET)
    @ResponseBody
    public SubjectDetailVo subjectGameList(@RequestParam("id") int id, @RequestParam(value = "pageNum", required = false) int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        SubjectDetailListVo subjectDetailListVo = gameService.readSubjectDetailList(id, pageNum, size);

        return subjectDetailListVo.getSubjectDetailList();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("专题");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "subject/list";
    }


    @RequestMapping(value = "/detailList", method = RequestMethod.GET)
    public String detailList(@RequestParam("id") int id, Model model, HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        model.addAttribute("id", id);

        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("专题详情");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "subject/detailList";
    }


}
