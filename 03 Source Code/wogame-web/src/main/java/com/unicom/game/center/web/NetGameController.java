package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.NetGameInfoListVo;
import com.unicom.game.center.vo.NetGameInfoVo;
import com.unicom.game.center.vo.NetGameServerListVo;
import com.unicom.game.center.vo.NetGameServerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/netgame")
public class NetGameController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(NetGameController.class);

    @RequestMapping(value = "/serverlist", method = RequestMethod.GET)
    @ResponseBody
    public NetGameServerVo netGameServerList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {

        int size = 0;
        try{
            if(pageSize != 0){
                size = pageSize;
            }else {
                size = Constants.PAGE_SIZE_DEFAULT;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error pageSize!");
        }

        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(pageNum, size);

        return netGameServerListVo.getNetGameServerVo();
    }

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public NetGameInfoVo netGameInfoList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {

        int size = 0;
        try{
            if(pageSize != 0){
                size = pageSize;
            }else {
                size = Constants.PAGE_SIZE_DEFAULT;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error pageSize!");
        }
        NetGameInfoListVo netGameServerListVo = gameService.readNetGameInfoList(pageNum, size);

        return netGameServerListVo.getNetGameInfoVo();
    }
}
