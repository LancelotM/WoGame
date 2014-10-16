package com.unicom.game.center.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.CategoryGameVo;
import com.unicom.game.center.vo.CategoryListVo;
import com.unicom.game.center.vo.ShowCategoryVo;

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

        model.addAttribute("list", categoryListVo.getCategoryData());
        return "category/list";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam("categoryId") int categoryId,
                         @RequestParam("categoryName") String categoryName,
                         Model model) {

        model.addAttribute("categoryId", categoryId);
        try {
            model.addAttribute("categoryName", URLDecoder.decode(categoryName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            model.addAttribute("categoryName", categoryName);
        }

        return "category/detail";
    }

    @RequestMapping(value = "ajaxDetail", method = RequestMethod.GET)
    @ResponseBody
    public CategoryGameVo ajaxDetail(@RequestParam("categoryId") int categoryId,
                                           @RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) Integer pageSize) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if(null != pageSize && pageSize > 0){
            size = pageSize;
        }

        ShowCategoryVo categoryVo = gameService.readShowCategory(categoryId, pageNum, size);

        return categoryVo.getCategoryGameData();
    }

}
