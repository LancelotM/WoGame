package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.NewListVo;
import com.unicom.game.center.vo.NewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public NewVo ajaxList(@RequestParam("pageNum") int pageNum, HttpSession session) {
        NewListVo newListVo = gameService.readNewList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        return newListVo.getDataList();
    }

}
