package com.unicom.game.center.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.vo.NetGameInfoListVo;
import com.unicom.game.center.vo.NetGameInfoVo;
import com.unicom.game.center.vo.NetGameServerItemVo;
import com.unicom.game.center.vo.NetGameServerListVo;
import com.unicom.game.center.vo.NetGameServerVo;

@Controller
@RequestMapping(value = "/netgame")
public class NetGameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/serverlist", method = RequestMethod.GET)
    @ResponseBody
    public NetGameServerVo netGameServerList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) Integer pageSize, Model model) {
        int size = Constants.PAGE_SIZE_DEFAULT;

        if(null != pageSize && pageSize > 0){
            size = pageSize;
        }

        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(pageNum, size);

        return netGameServerListVo.getNetGameServerVo();
    }
    
    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    @ResponseBody
    public NetGameServerVo netGameServerList(Model model) {
    	Date today = DateUtils.beginOfDate(new Date());
    	Date latest = DateUtils.getDayByInterval(today, -7);

        NetGameServerListVo netGameServerListVo = gameService.readNetGameServerList(1, 100);
        
        if(null != netGameServerListVo && null != netGameServerListVo.getNetGameServerVo() && netGameServerListVo.getNetGameServerVo().getTotalNum() > 0){
        	int index = 0;
        	for(NetGameServerItemVo  item : netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList()){
        		if(item.getOpenTime() < latest.getTime()){
        			break;
        		}
        		
        		index++;
        	}
        	
        	List<NetGameServerItemVo> items = netGameServerListVo.getNetGameServerVo().getNetGameServerItemVoList().subList(0, index);
        	netGameServerListVo.getNetGameServerVo().setNetGameServerItemVoList(items);
        	netGameServerListVo.getNetGameServerVo().setTotalNum(items.size());
        }

        return netGameServerListVo.getNetGameServerVo();
    }    

    @RequestMapping(value = "/infolist", method = RequestMethod.GET)
    @ResponseBody
    public NetGameInfoVo netGameInfoList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) Integer pageSize, Model model) {

        int size = Constants.PAGE_SIZE_DEFAULT;

        if(null != pageSize && pageSize > 0){
            size = pageSize;
        }
        
        NetGameInfoListVo netGameServerListVo = gameService.readNetGameInfoList(pageNum, size);

        return netGameServerListVo.getNetGameInfoVo();
    }
}
