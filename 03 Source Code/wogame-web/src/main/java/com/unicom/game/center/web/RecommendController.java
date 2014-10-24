package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.RecommendDataListVo;
import com.unicom.game.center.vo.RecommendedListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RecommendDataListVo recommendDataList(@RequestParam(value = "pageNum",required = false) int pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if (null != pageSize && pageSize > 0) {
            size = pageSize;
        }

        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum, size);

        return recommendedListVo.getRecommendedListData();
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        return "recommend";
    }


}
