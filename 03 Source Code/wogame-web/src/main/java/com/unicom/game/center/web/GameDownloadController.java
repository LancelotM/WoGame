package com.unicom.game.center.web;

import com.google.common.collect.Maps;
import com.unicom.game.center.business.PackageInfoBusiness;
import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.service.ZTEService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.GameDownloadVo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springside.modules.web.MediaTypes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
public class GameDownloadController {

    private Logger logger = LoggerFactory.getLogger(GameDownloadController.class);

    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private ZTEService zteService;

    @Autowired
    private PackageInfoBusiness packageInfoBusiness;

    @RequestMapping(value = "downloadFile", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public void download(@RequestParam("url") String url, @RequestParam("fileName") String fileName,
                         HttpServletResponse response) throws IOException {
        RestTemplate template = new RestTemplate();
        String utf8ProductName = URLDecoder.decode(URLDecoder.decode(fileName, "UTF-8"), "UTF-8");
        byte[] fileBytes = template.getForObject(url, byte[].class);

        InputStream is = new ByteArrayInputStream(fileBytes);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(utf8ProductName.getBytes(), "iso8859-1") + ".apk");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public GameDownloadVo info(@RequestParam("productId") String productId,
                               @RequestParam("productName") String productName,
                               @RequestParam("productIcon") String productIcon,
                               HttpServletRequest request) throws UnsupportedEncodingException {

        String utf8ProductName = URLDecoder.decode(URLDecoder.decode(productName, "UTF-8"), "UTF-8");

        HttpSession session = request.getSession();
        // 记录Log
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);

        //30 channelId（3位） productId（10位） productName（256位） productIcon
        String[] logData = new String[]{
                "30",
                StringUtils.rightPad(channel, 3, " "),
                StringUtils.rightPad(productId, 10, " "),
                StringUtils.rightPad(utf8ProductName, 256, ""),
                productIcon
        };

        statisticsLogger.info(StringUtils.join(logData, ""));

        GameDownloadVo info = zteService.readProductDownloadUrl(productId);

        if (StringUtils.isNotBlank(info.getDownloadUrl())) {
            info.setFileName(utf8ProductName);
            info.setContext(request.getContextPath());
            // 取得channel code
            String channelId = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
            String channelCode = packageInfoBusiness.checkPackageExist(channelId, productId, info.getOnlineTime());

            logDownloadUrl(productId, info, getClientIp(request), channelCode);
            info.setDownloadUrl(wrapDownloadUrl(productId, info.getDownloadUrl(), info.getOnlineTime(), channelCode));
        }

        return info;
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null) {
                ip = ip.split(",")[0].trim();
            }
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 成功获取下载地址URL日志（共计18个字段，用|线分隔）：
     * dlIndex
     * userCode
     * IMSI
     * IMEI
     * version
     * preassemble
     * clientVersion
     * accessType				（沃游戏中心 ：81）
     * productIndex			（游戏的productId）
     * positionClient（客户侧真实排序号）
     * positionindex（服务器侧排序号)
     * spID
     * updated
     * channel					(沃商店：0 沃游戏：00018589)
     * referer
     * status
     * server_time
     * IP
     */
    private void logDownloadUrl(String productId, GameDownloadVo info, String ip, String channel) {

        String[] logData = new String[]{
                parseDlIndexFromUrl(info.getDownloadUrl()),      //dlIndex
                "",  //userCode
                "",  //IMSI
                "",  //IMEI
                "",  //version
                "",  //preassemble
                "",  //clientVersion
                "81", //accessType
                productId, //productIndex
                "",  //spID
                "",  //updated
                channel == null ? "" : channel, //channel "00018589"
                "",  //referer
                "", //status
                "", //server_time
                ip //ip

        };

        statisticsLogger.download(StringUtils.join(logData, "|"));
    }

    private String parseDlIndexFromUrl(String downloadUrl) {
        if (downloadUrl == null) {
            return "";
        }

        String key = "/dl";
        int subDomainPosition = StringUtils.indexOf(downloadUrl, key);
        int domainPosition = StringUtils.indexOf(downloadUrl, ".");
        return StringUtils.substring(downloadUrl, subDomainPosition + key.length(), domainPosition);
    }

    private String wrapDownloadUrl(String productId, String downloadUrl, String onlineTime, String channelCode) {

        if (channelCode == null) {
        	return downloadUrl;
        }

        // add channel code
        int position = StringUtils.ordinalIndexOf(downloadUrl, "/", 4);
		String  packageURL =StringUtils.left(downloadUrl, position) + "_" + channelCode + StringUtils.right(downloadUrl, downloadUrl.length() - position);

        return StringUtils.left(packageURL, packageURL.length() - 4) + "_" + channelCode + StringUtils.right(packageURL, 4);

    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, String> handleException(Exception e) {
        logger.error("取得下载URL出错", e);

        Map<String, String> error = Maps.newHashMap();
        error.put("status", "-99");

        return error;
    }

}
