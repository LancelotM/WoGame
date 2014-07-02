package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.RecommendedListVo;
import com.unicom.game.center.vo.RollingAdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
public class IndexController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(@RequestParam("channel") String channel, Model model, HttpSession session) {

        session.setAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);

        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList();

        model.addAttribute("isIndex", true);
        model.addAttribute("adList", rollingAdListVo.getItems());
        model.addAttribute("recommendedList", recommendedListVo.getAppList());
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model, HttpSession session) {

        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList();

        model.addAttribute("adList", rollingAdListVo.getItems());
        model.addAttribute("recommendedList", recommendedListVo.getAppList());
        return "index";
    }

}
