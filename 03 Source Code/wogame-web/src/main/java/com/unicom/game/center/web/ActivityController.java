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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public ActivityInfoVo activityInfoList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {
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

        ActivityInfoListVo activityInfoListVo = gameService.readActivityInfoList(pageNum, size);

        return activityInfoListVo.getActivityInfoVo();
    }
}
