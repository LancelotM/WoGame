package com.unicom.game.center.web;

import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.vo.SearchResultItemVo;
import com.unicom.game.center.vo.SearchResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String list(Model model) {
//        SearchResultVo vo = zteService.readSearchResult("", 1);
//        model.addAttribute("list", vo.getSearchList());
        model.addAttribute("list", new ArrayList());
        return "search";
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public String search(HttpSession session) {
        /*SearchResultVo vo = zteService.readSearchResult(keyword, 1);
        model.addAttribute("list", vo.getSearchList());*/

        return "search";
    }

    @RequestMapping(value = "/ajaxSearch", method = RequestMethod.GET)
    @ResponseBody
    public List<SearchResultItemVo> ajaxSearch(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNum") Integer pageNum) {
        SearchResultVo vo = zteService.readSearchResult(keyword, pageNum);

        return vo.getSearchList();
    }
}
