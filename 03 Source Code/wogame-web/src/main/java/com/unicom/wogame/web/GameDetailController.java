package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class GameDetailController {

	@Autowired
	private GameService accountService;


}
