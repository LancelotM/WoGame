package com.unicom.game.center.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-1-8.
 */
public class UrlUtil {

    public final static String buildUrl(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        return buildUrl(url, params, "UTF-8");
    }

    public final static String buildUrl(String url, Map<String, Object> params, String charset) throws UnsupportedEncodingException {
        return buildUrl(url, params, null, charset);
    }

    public final static String buildUrl(String url, Map<String, Object> params, List<String> replaceKeys, String charset) throws UnsupportedEncodingException {
        String urlPage = urlPage(url);
        List<NameValuePair> requestParams = urlRequest(url);

        removeParamPairKey(requestParams, replaceKeys).addAll(filterParameters(params, null));

        if (CollectionUtils.isEmpty(requestParams)) {
            return urlPage;
        } else {
            return urlPage + "?" + URLEncodedUtils.format(requestParams, charset);
        }
    }

    private static List<NameValuePair> removeParamPairKey(List<NameValuePair> params, List<String> replaceKey) {
        List<NameValuePair> matchedPair = Lists.newArrayList();

        for (NameValuePair pair : params) {
            if (replaceKey.contains(pair.getName())) {
                matchedPair.add(pair);
            }
        }

        params.removeAll(matchedPair);

        return params;
    }

    /**
     * Processes the parameters passed through to the request,
     * removing the parameters used by the proxy itself
     */
    public final static List<NameValuePair> filterParameters(Map<String, Object> umap, List<String> excludeParams) {
        Map<String, Object> map = new HashMap<String, Object>(umap);
        List<NameValuePair> params = Lists.newArrayList();

        if (excludeParams != null) {
            for (String p : excludeParams) {
                map.remove(p);
            }
        }

        for (Object key : map.keySet().toArray()) {
            if (map.get(key) instanceof Object[]) {
                for (Object value : (Object[]) map.get(key)) {
                    String stringValue = getStringValue(value);
                    NameValuePair param = new BasicNameValuePair((String) key, stringValue);
                    params.add(param);
                }
            } else {
                String stringValue = getStringValue(map.get(key));
                NameValuePair param = new BasicNameValuePair((String) key, stringValue);
                params.add(param);
            }
        }

        return params;
    }

    private static String getStringValue(Object value) {
        String stringValue = "";
        if (value instanceof String) {
            stringValue = (String) value;
        } else {
            stringValue = String.valueOf(value);
        }
        return stringValue;
    }

    public final static String replaceSpecialChar(String url) {
        return StringUtils.replace(url, "?", "%3F").replace("&", "%26");
    }

    public final static String encodeUrl(String url, String charset) {
        String urlPage = urlPage(url);
        try {
            List<NameValuePair> requestParams = urlRequest(url);
            return urlPage == null ? url : urlPage + (CollectionUtils.isEmpty(requestParams) ? "" : "?" + URLEncodedUtils.format(urlRequest(url), charset));
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    private static String urlPage(String strURL) {
    	String strPage = strURL;
    	if(null != strURL){
            String[] arrSplit = null;

            strURL = strURL.trim();

            arrSplit = strURL.split("[?]");
            if (strURL.length() > 0) {
                if (arrSplit.length > 1) {
                    if (arrSplit[0] != null) {
                        strPage = arrSplit[0];
                    }
                }
            }    		
    	}

        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对, 并使用charset编码后返回编码后的URL
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static List<NameValuePair> urlRequest(String URL) throws UnsupportedEncodingException {
        List<NameValuePair> requestParams = Lists.newArrayList();

        String strUrlParam = truncateUrlPage(URL);
        if (strUrlParam == null) {
            return requestParams;
        }

        String[] arrSplit = null;

        //每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                requestParams.add(new BasicNameValuePair(arrSplitEqual[0], arrSplitEqual[1]));
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    requestParams.add(new BasicNameValuePair(arrSplitEqual[0], ""));
                }
            }
        }
        return requestParams;
    }

    public static String buildUrlWithRandomKey(String refer) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("rand", new Date().getTime());

        try {
            return buildUrl(refer, params, Lists.newArrayList("rand"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return encodeUrl(refer, "UTF-8") + "&rand=" + new Date().getTime();
        }
    }
}
