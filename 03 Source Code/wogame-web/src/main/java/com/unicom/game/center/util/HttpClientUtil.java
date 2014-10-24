package com.unicom.game.center.util;

import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:HttpClientUtil.java
 * @classDescription:HttpClient工具类//待完善模拟登录，cookie,证书登录
 * @author:xiayingjie
 * @createTime:2011-8-31
 */

public class HttpClientUtil {

    public static String CHARSET_ENCODING = "UTF-8";
    // private static String
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 6.0; Win32)";//ie6
    public static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Win32)";// ie7

    // private static String
    // USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Win32)";//ie8

    /**
     * 获取DefaultHttpClient对象
     *
     * @param charset 字符编码
     * @return DefaultHttpClient对象
     */
    public static DefaultHttpClient getDefaultHttpsClient(final String charset) {
        DefaultHttpClient client = getDefaultHttpClient(charset);
        enableSSL(client);
        return client;
    }

    /**
     * 获取DefaultHttpClient对象
     *
     * @param charset 字符编码
     * @return DefaultHttpClient对象
     */
    public static DefaultHttpClient getDefaultHttpClient(final String charset) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
        httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                USER_AGENT);
        httpclient.getParams().setParameter(
                CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
        httpclient.getParams().setParameter(
                CoreProtocolPNames.HTTP_CONTENT_CHARSET,
                charset == null ? CHARSET_ENCODING : charset);

        // 浏览器兼容性
        httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                CookiePolicy.BROWSER_COMPATIBILITY);
        // 定义重试策略
        httpclient.setHttpRequestRetryHandler(requestRetryHandler);

        return httpclient;
    }

    /**
     * 访问https的网站
     *
     * @param httpclient
     */
    public static void enableSSL(DefaultHttpClient httpclient) {
        //调用ssl
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{truseAllManager}, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", sf, 443);
            httpclient.getConnectionManager().getSchemeRegistry()
                    .register(https);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

    };

    /**
     * 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
     */
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
        // 自定义的恢复策略
        public boolean retryRequest(IOException exception, int executionCount,
                                    HttpContext context) {
            // 设置恢复策略，在发生异常时候将自动重试3次
            if (executionCount >= 3) {
                // 如果连接次数超过了最大值则停止重试
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器连接失败重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试ssl连接异常
                return false;
            }
            HttpRequest request = (HttpRequest) context
                    .getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
            if (!idempotent) {
                // 重试，如果请求是考虑幂等
                return true;
            }
            return false;
        }
    };

    /**
     * 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
     */
    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        // 自定义响应处理
        public String handleResponse(HttpResponse response)
                throws ClientProtocolException, IOException {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_ENCODING
                        : EntityUtils.getContentCharSet(entity);
                return new String(EntityUtils.toByteArray(entity), charset);
            } else {
                return null;
            }
        }
    };

    /**
     * 使用post方法获取相关的数据
     *
     * @param url
     * @param paramsList
     * @return
     */
    public static String post(String url, List<NameValuePair> paramsList) {
        return httpRequest(url, paramsList, "POST", null);
    }

    /**
     * 使用post方法获取相关的数据
     *
     * @param url
     * @param bodyContent
     * @return
     */
    public static String post(String url, List<NameValuePair> paramsList, byte[] bodyContent) throws MethodNotSupportedException {
        return httpRequest(url, paramsList, bodyContent, null);
    }

    /**
     * 使用post方法并且通过代理获取相关的数据
     *
     * @param url
     * @param paramsList
     * @param proxy
     * @return
     */
    public static String post(String url, List<NameValuePair> paramsList,
                              HttpHost proxy) {
        return httpRequest(url, paramsList, "POST", proxy);
    }

    /**
     * 使用get方法获取相关的数据
     *
     * @param url
     * @param paramsList
     * @return
     */
    public static String get(String url, List<NameValuePair> paramsList) {
        return httpRequest(url, paramsList, "GET", null);
    }

    /**
     * 使用get方法获取相关的数据
     *
     * @param url
     * @param paramsList
     * @return
     */
    public static String get(String url, List<NameValuePair> paramsList, Map<String, String> headers) {
        return httpRequest(url, paramsList, "GET", headers, null);
    }

    /**
     * 使用get方法并且通过代理获取相关的数据
     *
     * @param url
     * @param paramsList
     * @param proxy
     * @return
     */
    public static String get(String url, List<NameValuePair> paramsList,
                             HttpHost proxy) {
        return httpRequest(url, paramsList, "GET", proxy);
    }


    /**
     * 提交数据到服务器
     *
     * @param url
     * @param paramsList
     * @param method
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public static String httpRequest(String url,
                                     List<NameValuePair> paramsList, String method,
                                     Map<String, String> headers, HttpHost proxy) {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }

        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        //判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }
        String formatParams = null;
        // 将参数进行utf-8编码
        if (null != paramsList && paramsList.size() > 0) {
            formatParams = URLEncodedUtils.format(paramsList, CHARSET_ENCODING);
        }
        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    proxy);
        }
        try {

            HttpRequestBase rb = null;

            // 如果方法为Get
            if ("GET".equalsIgnoreCase(method)) {
                if (formatParams != null) {
                    url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)
                            : (url.substring(0, url.indexOf("?") + 1) + formatParams);
                }
                rb = new HttpGet(url);
//                responseStr = httpclient.execute(hg, responseHandler);

                // 如果方法为Post
            } else if ("POST".equalsIgnoreCase(method)) {
                rb = new HttpPost(url);
                if (formatParams != null) {
                    StringEntity entity = new StringEntity(formatParams);
                    entity.setContentType("application/x-www-form-urlencoded");
                    ((HttpPost) rb).setEntity(entity);
                }
//                responseStr = httpclient.execute(hp, responseHandler);

            }

            if (headers != null) {
                rb.setHeaders(convertMapToHeaders(headers));
            }

            responseStr = httpclient.execute(rb, responseHandler);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }

    private static Header[] convertMapToHeaders(Map<String, String> headers) {
        Header[] headerArray = new Header[headers.size()];

        int loop = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            headerArray[loop++] = new BasicHeader(entry.getKey(), entry.getValue());
        }

        return headerArray;
    }


    /**
     * 提交数据到服务器
     *
     * @param url
     * @param paramsList
     * @param method
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public static String httpRequest(String url,
                                     List<NameValuePair> paramsList, String method, HttpHost proxy) {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }

        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        //判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }
        String formatParams = null;
        // 将参数进行utf-8编码
        if (null != paramsList && paramsList.size() > 0) {
            formatParams = URLEncodedUtils.format(paramsList, CHARSET_ENCODING);
        }
        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    proxy);
        }
        try {
            // 如果方法为Get
            if ("GET".equalsIgnoreCase(method)) {
                if (formatParams != null) {
                    url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)
                            : (url.substring(0, url.indexOf("?") + 1) + formatParams);
                }
                HttpGet hg = new HttpGet(url);
                responseStr = httpclient.execute(hg, responseHandler);

                // 如果方法为Post
            } else if ("POST".equalsIgnoreCase(method)) {
                HttpPost hp = new HttpPost(url);
                if (formatParams != null) {
                    StringEntity entity = new StringEntity(formatParams);
                    entity.setContentType("application/x-www-form-urlencoded");
                    hp.setEntity(entity);
                }
                responseStr = httpclient.execute(hp, responseHandler);

            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }

    /**
     * 提交数据到服务器
     *
     * @param url
     * @param bodyContent
     * @param paramsList
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public static String httpRequest(String url, List<NameValuePair> paramsList,
                                     byte[] bodyContent, HttpHost proxy) throws MethodNotSupportedException {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }

        String formatParams = null;
        // 将参数进行utf-8编码
        if (null != paramsList && paramsList.size() > 0) {
            formatParams = URLEncodedUtils.format(paramsList, CHARSET_ENCODING);
        }

        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        //判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }

        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    proxy);
        }
        try {

            HttpPost hp = new HttpPost(url);
            if (formatParams != null) {
                StringEntity entity = new StringEntity(formatParams);
                entity.setContentType("application/x-www-form-urlencoded");
                hp.setEntity(entity);
            }
            if (bodyContent != null) {
                ByteArrayEntity entity = new ByteArrayEntity(bodyContent);

                hp.setEntity(entity);
            }
            responseStr = httpclient.execute(hp, responseHandler);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }


    /**
     * 提交数据到服务器
     *
     * @param url
     * @param fileMap
     * @param stringMap
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public static String httpFileRequest(String url,
                                         Map<String, String> fileMap, Map<String, String> stringMap, int type, HttpHost proxy) {
        String responseStr = null;
        // 判断输入的值是是否为空
        if (null == url || "".equals(url)) {
            return null;
        }
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);

        //判断是否是https请求
        if (url.startsWith("https")) {
            enableSSL(httpclient);
        }

        // 如果代理对象不为空则设置代理
        if (null != proxy) {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                    proxy);
        }
        //发送文件
        HttpPost hp = new HttpPost(url);
        MultipartEntity multiEntity = new MultipartEntity();
        try {
            //type=0是本地路径，否则是网络路径
            if (type == 0) {
                for (String key : fileMap.keySet()) {
                    multiEntity.addPart(key, new FileBody(new File(fileMap.get(key))));
                }
            } else {
                for (String key : fileMap.keySet()) {
                    multiEntity.addPart(key, new ByteArrayBody(getUrlFileBytes(fileMap.get(key)), key));
                }
            }
            // 加入相关参数 默认编码为utf-8
            for (String key : stringMap.keySet()) {
                multiEntity.addPart(key, new StringBody(stringMap.get(key), Charset.forName(CHARSET_ENCODING)));
            }
            hp.setEntity(multiEntity);
            responseStr = httpclient.execute(hp, responseHandler);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseStr;
    }

    /**
     * 将相关文件和参数提交到相关服务器
     *
     * @param url
     * @param fileMap
     * @param stringMap
     * @return
     */
    public static String postFile(String url, Map<String, String> fileMap,
                                  Map<String, String> stringMap) {
        return httpFileRequest(url, fileMap, stringMap, 0, null);
    }

    /**
     * 将相关文件和参数提交到相关服务器
     *
     * @param url
     * @param urlMap
     * @param stringMap
     * @return
     */
    public static String postUrlFile(String url, Map<String, String> urlMap,
                                     Map<String, String> stringMap) {
        return httpFileRequest(url, urlMap, stringMap, 1, null);
    }

    /**
     * 获取网络文件的字节数组
     *
     * @param url
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public static byte[] getUrlFileBytes(String url) throws ClientProtocolException,
            IOException {

        byte[] bytes = null;
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);
        // 获取url里面的信息
        HttpGet hg = new HttpGet(url);
        HttpResponse hr = httpclient.execute(hg);
        bytes = EntityUtils.toByteArray(hr.getEntity());
        // 转换内容为字节
        return bytes;
    }

    /**
     * 获取图片的字节数组
     *
     * @param url
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     * @createTime 2011-11-24
     */
    public static byte[] getImg(String url) throws ClientProtocolException,
            IOException {
        byte[] bytes = null;
        // 创建HttpClient实例
        DefaultHttpClient httpclient = getDefaultHttpClient(CHARSET_ENCODING);
        // 获取url里面的信息
        HttpGet hg = new HttpGet(url);
        HttpResponse hr = httpclient.execute(hg);
        bytes = EntityUtils.toByteArray(hr.getEntity());
        // 转换内容为字节
        return bytes;
    }

    /**
     * Processes the parameters passed through to the request,
     * removing the parameters used by the proxy itself
     */
    public static List<NameValuePair> getMapParameters(Map<Object, Object> umap) {
        Map<Object, Object> map = new HashMap<Object, Object>(umap);

        List<NameValuePair> parameters = Lists.newArrayList();

        for (Object key : map.keySet().toArray()) {
            if (map.get(key) instanceof Object[]) {
                for (String value : (String[]) map.get(key)) {
                    NameValuePair param = new BasicNameValuePair((String) key, value);
                    parameters.add(param);
                }
            } else {
                NameValuePair param = new BasicNameValuePair((String) key, (String) map.get(key));
                parameters.add(param);
            }
        }

        return parameters;
    }


    public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {

        String url = "http://www.baidu.com/";
        String str = HttpClientUtil.get(url, null);
        System.out.println(str);

    }
    
    public static String getClientIp(HttpServletRequest request) {
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
}