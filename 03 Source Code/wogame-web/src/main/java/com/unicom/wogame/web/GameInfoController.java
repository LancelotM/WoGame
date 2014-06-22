package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import com.unicom.wogame.vo.GameInfoVo;
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

    @RequestMapping(method = RequestMethod.GET)
    public String info(@RequestParam("productId") String productId, Model model) {
        GameInfoVo info = gameService.readGameInfo(productId);

        model.addAttribute("info", info);
        return "gameInfo";
    }


}
