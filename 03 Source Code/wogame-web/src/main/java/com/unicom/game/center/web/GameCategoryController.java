package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.CategoryGameVo;
import com.unicom.game.center.vo.CategoryListVo;
import com.unicom.game.center.vo.ShowCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryName", categoryVo.getCategoryTitle());
        model.addAttribute("list", categoryVo.getAppList());

        return "category/detail";
    }

    @RequestMapping(value = "ajaxDetail", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryGameVo> ajaxDetail(@RequestParam("categoryId") int categoryId,
                                           @RequestParam("pageNum") int pageNum) {
        ShowCategoryVo categoryVo = gameService.readShowCategory(categoryId, pageNum, Constants.PAGE_SIZE_DEFAULT);

        return categoryVo.getAppList();
    }

}
