package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import com.unicom.wogame.vo.RecommendedListVo;
import com.unicom.wogame.vo.RollingAdListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList();

		model.addAttribute("adList", rollingAdListVo.getItems());
        model.addAttribute("recommendedList", recommendedListVo.getAppList());
		return "index";
	}


}
