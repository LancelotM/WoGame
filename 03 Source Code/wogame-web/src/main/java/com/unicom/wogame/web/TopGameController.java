package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class TopGameController {

	@Autowired
	private GameService accountService;


}
