package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.WeekHotItemVo;
import com.unicom.game.center.vo.WeekHotVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/weeklyHot")
public class WeeklyHotGamesController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(WeeklyHotGamesController.class);

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(HttpSession session) {
        /*WeekHotVo weekHotVo = gameService.readWeekHotDownloadList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("hotList", weekHotVo.getItems());*/
        return "weeklyHot";
    }

    @RequestMapping(value = "ajaxList", method = RequestMethod.GET)
    @ResponseBody
    public WeekHotItemVo ajaxList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {
        int size = 0;
        try{
            if(pageSize != 0){
                size = pageSize;
            }else {
                size = Constants.PAGE_SIZE_DEFAULT;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error pageSize!");
        }

        WeekHotVo weekHotVo = gameService.readWeekHotDownloadList(pageNum, size);

        return weekHotVo.getData();
    }
}
