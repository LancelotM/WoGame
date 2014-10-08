package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.ActivityInfoListVo;
import com.unicom.game.center.vo.ActivityInfoVo;
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

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public ActivityInfoVo activityInfoList(@RequestParam("pageNum") int pageNum, Model model) {
        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        return activityInfoListVo.getActivityInfoVo();
    }
}
