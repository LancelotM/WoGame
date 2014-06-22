package com.unicom.wogame.web;

import com.unicom.wogame.service.ZTEService;
import com.unicom.wogame.vo.SearchResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/search")
public class GameSearchController {

    @Autowired
    private ZTEService zteService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        SearchResultVo vo = zteService.readSearchResult("", 1);
        model.addAttribute("list", vo.getSearchList());

        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@RequestParam("keyword") String keyword, Model model) {
        SearchResultVo vo = zteService.readSearchResult(keyword, 1);
        model.addAttribute("list", vo.getSearchList());

        return "search";
    }
}
