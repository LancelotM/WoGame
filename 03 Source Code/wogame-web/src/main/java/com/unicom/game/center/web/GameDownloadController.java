package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.service.StatisticsLogger;
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
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/download")
public class GameDownloadController {

    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private ZTEService zteService;

    @Autowired
    private PackageInfoBusiness packageInfoBusiness;

    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public GameDownloadVo info(@RequestParam("productId") String productId,
                               @RequestParam("productName") String productName,
                               @RequestParam("productIcon") String productIcon,
                               HttpSession session) {
        // 记录Log
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        Map<String, String> logData = Maps.newLinkedHashMap();
        logData.put("productId", productId);
        logData.put("productName", productName);
        logData.put("productIcon", productIcon);
        logData.put("channelId", channel);
        statisticsLogger.log("downloadInfo", logData);

        GameDownloadVo info = zteService.readProductDownloadUrl(productId);

        if (StringUtils.isNotBlank(info.getDownloadUrl())) {
            info.setDownloadUrl(wrapDownloadUrl(productId, info.getDownloadUrl(), info.getOnlineTime(), session));
        }

        return info;
    }

    private String wrapDownloadUrl(String productId, String downloadUrl, String onlineTime, HttpSession session) {
        // 处理下载
        String channelId = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        String channelCode = packageInfoBusiness.checkPackageExist(channelId, productId, onlineTime);

        if (channelCode == null) {
            return downloadUrl;
        }
        return StringUtils.left(downloadUrl, downloadUrl.length() - 4) + "_" + channelCode + StringUtils.right(downloadUrl, 4);

    }

}
