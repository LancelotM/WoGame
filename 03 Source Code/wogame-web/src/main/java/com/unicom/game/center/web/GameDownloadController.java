package com.unicom.game.center.web;

import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.GameDownloadVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.MediaTypes;

import javax.servlet.http.HttpSession;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/download")
public class GameDownloadController {

    @Autowired
    private ZTEService zteService;

//    @Autowired
//    private PackageInfoBusiness packageInfoBusiness;

    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public GameDownloadVo info(@RequestParam("productId") String productId, HttpSession session) {
        GameDownloadVo info = zteService.readProductDownloadUrl(productId);

        if (StringUtils.isNotBlank(info.getDownloadUrl())) {
            info.setDownloadUrl(wrapDownloadUrl(productId, info.getDownloadUrl(), session));
        }

        return info;
    }

    private String wrapDownloadUrl(String productId, String downloadUrl, HttpSession session) {
        String channelId = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
//        if (packageInfoBusiness.checkPackageExist(channelId, productId)) {
//            return StringUtils.left(downloadUrl, downloadUrl.length()-4) + "_" + channelId + StringUtils.right(downloadUrl, 4);
//        }
        return downloadUrl;
    }

}
