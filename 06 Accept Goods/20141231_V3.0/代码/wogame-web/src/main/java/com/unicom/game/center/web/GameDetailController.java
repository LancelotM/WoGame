package com.unicom.game.center.web;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import com.unicom.game.center.vo.GameDetailListVo;
import com.unicom.game.center.vo.GameDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/gamedetail")
public class GameDetailController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @RequestMapping(value = "/detaillist", method = RequestMethod.GET)

    public String gameDetailList(@RequestParam("product_id") String productId, Model model,HttpServletRequest request) {
    	HttpSession session = HttpClientUtil.fetchSession(request);
        GameDetailListVo gameDetailListVo = gameService.readGameDetailList(productId);
        GameDetailVo v = gameDetailListVo.getGameDetailVo();
        model.addAttribute("v", v);

        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("游戏详情");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP));
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "gameDetail";
    }
}
