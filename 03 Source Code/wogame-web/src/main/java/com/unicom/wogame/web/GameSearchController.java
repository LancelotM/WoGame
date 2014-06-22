package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import com.unicom.wogame.vo.CategoryListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/search")
public class GameSearchController {

    @Autowired
    private GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        CategoryListVo categoryListVo = gameService.readCategoryList();

        model.addAttribute("list", categoryListVo.getCategoryList());
        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(Model model) {
        CategoryListVo categoryListVo = gameService.readCategoryList();

        model.addAttribute("list", categoryListVo.getCategoryList());
        return "search";
    }
}
