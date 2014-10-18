package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.UrlUtil;
import com.unicom.game.center.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public String info(@RequestParam("productId") String productId, Model model, HttpServletRequest request) {
        model.addAttribute("error", "");
        model.addAttribute("referUrl", UrlUtil.buildUrlWithRandomKey(request.getHeader("Referer")));
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


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String fetchGameInfoDetail(@RequestParam("id") int id, Model model) {

        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);
        InfoDetailVo i = infoDetailListVo.getInfoDetailVo();
        model.addAttribute("gameInfoContent", i);
        return "info/detail";
    }


    @RequestMapping(value = "/ajaxlist", method = RequestMethod.GET)
    @ResponseBody
    public GameInfoDataVo gameInfoList(@RequestParam("pageNum") int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        GameInfoListVo gameInfoListVo = gameService.readGameInfoList(pageNum, size);

        return gameInfoListVo.getGameInfoVo();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String news() {
        return "info/list";
    }


}
