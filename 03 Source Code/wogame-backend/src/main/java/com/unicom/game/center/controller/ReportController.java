package com.unicom.game.center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportController {

    @RequestMapping(value = "/getReport", method = {RequestMethod.GET})
    public ModelAndView getReport(){
        return new ModelAndView("/report");
    }
}
