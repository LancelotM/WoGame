package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.GameDownloadVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.web.MediaTypes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
@RequestMapping(value = "/download")
public class GameDownloadController {

    private Logger logger = LoggerFactory.getLogger(GameDownloadController.class);

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
                               HttpSession session) throws UnsupportedEncodingException {

        String utf8ProductName = URLDecoder.decode(URLDecoder.decode(productName, "UTF-8"), "UTF-8");

        // 记录Log
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        Map<String, String> logData = Maps.newLinkedHashMap();
        logData.put("productId", productId);
        logData.put("productName", utf8ProductName);
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

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> handleException(Exception e) {
        logger.error("解码Keyword出错", e);

        Map<String, String> error = Maps.newHashMap();
        error.put("status", "-99");

        return error;
    }

}
