package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.vo.SearchKeywordItemVo;
import com.unicom.game.center.vo.SearchKeywordsVo;
import com.unicom.game.center.vo.SearchResultItemVo;
import com.unicom.game.center.vo.SearchResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/search")
public class GameSearchController {

    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private ZTEService zteService;

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String list(Model model) {
        SearchKeywordsVo vo = zteService.readSearchAllKeywords();
        model.addAttribute("list", vo == null ? new ArrayList<SearchKeywordItemVo>() : vo.getHotwordList());
        return "search";
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchKeywordItemVo> search(@RequestParam("keyword") String keyword) {
        SearchKeywordsVo vo = zteService.readSearchKeywords(keyword);

        return vo == null ? new ArrayList<SearchKeywordItemVo>() : vo.getHotwordList();
    }

    @RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchResultItemVo> ajaxSearch(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNum") Integer pageNum) {

        // 记录Log
        Map<String, String> logData = Maps.newHashMap();
        logData.put("keyword", keyword);
        statisticsLogger.log("keyword", logData);

        //根据搜索字搜索游戏
        SearchResultVo vo = zteService.readSearchResult(keyword, pageNum);

        return vo.getSearchList();
    }
}
