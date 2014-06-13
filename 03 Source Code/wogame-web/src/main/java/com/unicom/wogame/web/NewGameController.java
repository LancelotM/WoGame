package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import com.unicom.wogame.util.Constants;
import com.unicom.wogame.vo.NewListVo;
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
@RequestMapping(value = "/newGame")
public class NewGameController {

	@Autowired
	private GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam("pageNum") int pageNum, Model model) {
        NewListVo newListVo = gameService.readNewList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("list", newListVo.getAppList());
        return "newGame";
    }

}
