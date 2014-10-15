package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.vo.InfoDetailListVo;
import com.unicom.game.center.vo.InfoDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/info")
public class InfoDetailController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public InfoDetailVo fetchInfoDetail(@RequestParam("id") int id, Model model) {

        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);

        return infoDetailListVo.getInfoDetailVo();
    }
}
