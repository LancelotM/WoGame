package com.unicom.game.center.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.WeekHotItemVo;
import com.unicom.game.center.vo.WeekHotVo;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/weeklyHot")
public class WeeklyHotGamesController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(HttpSession session) {

        return "weeklyHot";
    }

    @RequestMapping(value = "ajaxList", method = RequestMethod.GET)
    @ResponseBody
    public WeekHotItemVo ajaxList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if(null != pageSize && pageSize > 0){
            size = pageSize;
        }

        WeekHotVo weekHotVo = gameService.readWeekHotList(pageNum, size);

        return weekHotVo.getData();
    }
}
