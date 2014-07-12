package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.vo.SearchKeywordItemVo;
import com.unicom.game.center.vo.SearchKeywordsVo;
import com.unicom.game.center.vo.SearchResultItemVo;
import com.unicom.game.center.vo.SearchResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
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
    private Logger logger = LoggerFactory.getLogger(GameSearchController.class);
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
    public List<SearchKeywordItemVo> search(@RequestParam("keyword") String keyword) throws Exception {
        SearchKeywordsVo vo = zteService.readSearchKeywords(URLDecoder.decode(URLDecoder.decode(keyword, "UTF-8"), "UTF-8"));

        return vo == null ? new ArrayList<SearchKeywordItemVo>() : vo.getHotwordList();
    }

    @RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchResultItemVo> ajaxSearch(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNum") Integer pageNum) throws Exception {

        String utf8Keyword = URLDecoder.decode(URLDecoder.decode(keyword, "UTF-8"), "UTF-8");

        // 记录Log
        String[] logData = new String[]{"40", utf8Keyword};
        statisticsLogger.info(StringUtils.join(logData, ""));

        //根据搜索字搜索游戏
        SearchResultVo vo = zteService.readSearchResult(utf8Keyword, pageNum);

        return vo.getSearchList();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> handleException(Exception e) {
        logger.error("解码Keyword出错", e);

        Map<String, String> error = Maps.newHashMap();
        error.put("status", "-99");

        return error;
    }
}
