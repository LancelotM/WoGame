package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.utils.AESEncryptionHelper;
import com.unicom.game.center.vo.RecommendDataListVo;
import com.unicom.game.center.vo.RecommendedListVo;
import com.unicom.game.center.vo.RollingAdListVo;

import com.unicom.game.center.vo.RollingAdVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/indexlist")
public class IndexController {

    @Autowired
    private GameService gameService;

	@Value("#{properties['site.secret.key']}")
	private String siteKey;    
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/banner", method = RequestMethod.GET)
     @ResponseBody
     public List<RollingAdVo> rollingAdList(Model model) {
        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();

        return rollingAdListVo.getData();
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public RecommendDataListVo recommendDataList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {
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

        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum, size);

        return recommendedListVo.getRecommendedListData();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(@RequestParam("pageNum") int pageNum, @RequestParam(value="token", required=false) String token, Model model, HttpSession session) {

    	String channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
    	try {
    		if(null != token && !"".equals(token)){
    			channel = AESEncryptionHelper.decrypt(token, siteKey);
    		}    		
		} catch (Exception e) {
			channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
			e.printStackTrace();
			logger.error("Error channel token!");
		}
    	
        session.setAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);

        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum,Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("isIndex", true);
        model.addAttribute("adList", rollingAdListVo.getData());
        model.addAttribute("recommendedList", recommendedListVo.getRecommendedListData());
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(@RequestParam("pageNum") int pageNum, Model model, HttpSession session) {

        RollingAdListVo rollingAdListVo = gameService.readRollingAdList();
        RecommendedListVo recommendedListVo = gameService.readRecommendedList(pageNum,Constants.PAGE_SIZE_DEFAULT);

        model.addAttribute("adList", rollingAdListVo.getData());
        model.addAttribute("recommendedList", recommendedListVo.getRecommendedListData());
        return "index";
    }

}
