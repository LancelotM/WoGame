package com.unicom.wogame.web;

import com.google.common.collect.Maps;
import com.unicom.wogame.service.GameService;
import com.unicom.wogame.service.StatisticsLogger;
import com.unicom.wogame.util.Constants;
import com.unicom.wogame.vo.RecommendedListVo;
import com.unicom.wogame.vo.RollingAdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private StatisticsLogger statisticsLogger;

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam("channel") String channel, Model model, HttpSession session) {

        session.setAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);

        Map<String, Object> loggerData = Maps.newHashMap();
        loggerData.put("cookie", true);
        loggerData.put(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);
        statisticsLogger.log("userCount", loggerData);

		RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList();

		model.addAttribute("adList", rollingAdListVo.getItems());
        model.addAttribute("recommendedList", recommendedListVo.getAppList());
		return "index";
	}


}
