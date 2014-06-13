package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import com.unicom.wogame.util.Constants;
import com.unicom.wogame.vo.CategoryListVo;
import com.unicom.wogame.vo.RecommendedListVo;
import com.unicom.wogame.vo.RollingAdListVo;
import com.unicom.wogame.vo.ShowCategoryVo;
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
@RequestMapping(value = "/category")
public class GameCategoryController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        CategoryListVo categoryListVo = gameService.readCategoryList();

        model.addAttribute("list", categoryListVo.getCategoryList());
        return "category/list";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam("categoryId") int categoryId,
                         @RequestParam("pageNum") int pageNum,
                         Model model) {
        ShowCategoryVo categoryVo = gameService.readShowCategory(categoryId, pageNum, Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("list", categoryVo.getAppList());
        return "category/detail";
    }

}
