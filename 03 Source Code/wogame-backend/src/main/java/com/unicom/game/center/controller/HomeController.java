package com.unicom.game.center.controller;

import com.unicom.game.center.db.dao.AccountDao;
import com.unicom.game.center.db.domain.AccountDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Yin
 * 
 * @Date Jun 11, 2014
 */

@Controller
public class HomeController
{
    @Resource
    public AccountDao dao;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}
/*
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(String username, String password){
        Map<String,String> loginInfo = new HashMap<String, String>();
        if(dao.fetchUserByName(username) != null){
            AccountDomain account = dao.fetchUserByNameAndPassword(username,password);
            if(account != null){
                return "createManager";
            }
            return null;
        }
        return null;

    }
*/	
}
