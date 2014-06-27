package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.vo.GameInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/gameInfo")
public class GameInfoController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(GameInfoController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String info(@RequestParam("productId") String productId, Model model) {
        model.addAttribute("error", "");
        try {
            GameInfoVo info = gameService.readGameInfo(productId);
            if (info == null) {
                model.addAttribute("error", "1");
            } else {
                model.addAttribute("info", info);
            }
        } catch (Exception ex) {
            logger.error("Server Internal Error.", ex);
            model.addAttribute("error", "1");
        }

        return "gameInfo";
    }


}
