package com.unicom.game.center.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {


    @RequestMapping(method = RequestMethod.GET)
    public String info() {
        return "error/error";
    }


}
