package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.ActivityInfoListVo;
import com.unicom.game.center.vo.ActivityInfoVo;
import com.unicom.game.center.vo.InfoDetailListVo;
import com.unicom.game.center.vo.InfoDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @RequestMapping(value = "/ajaxList", method = RequestMethod.GET)
    @ResponseBody
    public ActivityInfoVo activityInfoList(@RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(pageNum, size);

        return activityInfoListVo.getActivityInfoVo();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "activity/list";
    }


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String fetchInfoDetail(@RequestParam("id") int id, Model model) {

        InfoDetailListVo infoDetailListVo = gameService.readInfoDetail(id);
        InfoDetailVo i = infoDetailListVo.getInfoDetailVo();
        model.addAttribute("activityContent", i);
        return "activity/detail";
    }

}
