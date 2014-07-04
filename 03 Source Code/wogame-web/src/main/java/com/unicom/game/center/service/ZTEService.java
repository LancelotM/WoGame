package com.unicom.game.center.service;

import com.google.common.collect.Lists;
import com.unicom.game.center.util.UniData;
import com.unicom.game.center.util.UniDataUtil;
import com.unicom.game.center.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jession on 14-6-20.
 */
@Component
public class ZTEService {

    private Logger logger = LoggerFactory.getLogger(ZTEService.class);

    private static final String GAME_DETAIL_TABLE_NAME = "productdetail";
    private static final String SEARCH_RESULT_TABLE_NAME = "search";
    private static final String SEARCH_RESULT_LIST_TABLE_NAME = "searchlist";
    private static final String DOWNLOAD_RESULT_TABLE_NAME = "order";
    private static final String SEARCH_KEYWORD_TABLE_NAME = "getkeywords";
    private static final String SEARCH_KEYWORD_RESULT_SPLIT_CHAR = "|";

    private static final String URL_PRODUCT_DETAIL = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid={serviceid}&productid={productid}&state={state}";
    private static final String URL_SEARCH_GAME = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid=searchallgameapp&keyword={keyword}&pagenum={pageNum}&tablename=search&count=20";
    private static final String URL_PRODUCT_DOWNLOAD = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid=order&productid={productid}&ordertype=4" +
            "&accountingtype=0&recuserid=&update=0&packageid=&downchannel=8";
    private static final String URL_SEARCH_ALL_KEYWORDS = "http://wogame.wostore.cn:8080/gameservice/hotwordsList.do";
    private static final String URL_SEARCH_KEYWORDS = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid=getkeywords&keyword={keyword}";

    public GameInfoVo readProductDetail(String productId) {

        Map<String, Object> urlVariables = new HashMap<String, Object>();

        urlVariables.put("serviceid", GAME_DETAIL_TABLE_NAME);
        urlVariables.put("productid", productId);
        urlVariables.put("state", "101");

        HttpHeaders headers = getHttpHeaders();


        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

        ResponseEntity<byte[]> response = restTemplate.exchange(URL_PRODUCT_DETAIL, HttpMethod.GET, entity, byte[].class, urlVariables);

        return parseGameInfo(response.getBody());
    }

    private GameInfoVo parseGameInfo(byte[] gameInfoData) {
        try {
            GameInfoVo gameBaseInfo = new GameInfoVo();
            UniData uniData = UniDataUtil.fromBytes(gameInfoData);
            UniData.TableData tableData = uniData.getTable(GAME_DETAIL_TABLE_NAME);
            if (tableData == null) {
                return null;
            }
            UniData.RowData rowData = tableData.getRowData(0);
            gameBaseInfo.setProductId(Long.valueOf(rowData.getColumnData("id")
                    .getValue()));
            gameBaseInfo.setName(rowData.getColumnData("name").getValue());
            gameBaseInfo.setIconUrl(rowData.getColumnData("icon").getValue());
            gameBaseInfo.setRate(Integer.valueOf(
                    rowData.getColumnData("rate").getValue()).intValue());
            gameBaseInfo.setPrice(Integer.valueOf(
                    rowData.getColumnData("price").getValue()).intValue());
            gameBaseInfo.setSupplier(rowData.getColumnData("supplier")
                    .getValue());
            gameBaseInfo.setDesc(rowData.getColumnData("desc").getValue());
            gameBaseInfo.setSize(Long.valueOf(
                    rowData.getColumnData("size").getValue()).longValue() * 1024);
            gameBaseInfo.setCatagory(rowData.getColumnData("catagory")
                    .getValue());
            List<String> screenshots = new ArrayList<String>();
            if (rowData.getColumnData("screenshots1").getValue() != null
                    && rowData.getColumnData("screenshots1").getValue()
                    .startsWith("http://")) {
                screenshots.add(rowData.getColumnData("screenshots1")
                        .getValue());
            }
            if (rowData.getColumnData("screenshots2").getValue() != null
                    && rowData.getColumnData("screenshots2").getValue()
                    .startsWith("http://")) {
                screenshots.add(rowData.getColumnData("screenshots2")
                        .getValue());
            }
            if (rowData.getColumnData("screenshots3").getValue() != null
                    && rowData.getColumnData("screenshots3").getValue()
                    .startsWith("http://")) {
                screenshots.add(rowData.getColumnData("screenshots3")
                        .getValue());
            }
            if (rowData.getColumnData("screenshots4").getValue() != null
                    && rowData.getColumnData("screenshots4").getValue()
                    .startsWith("http://")) {
                screenshots.add(rowData.getColumnData("screenshots4")
                        .getValue());
            }
            gameBaseInfo.setScreenshots(screenshots);
            gameBaseInfo.setPackageName(rowData.getColumnData("packageName")
                    .getValue());
            gameBaseInfo.setVersionName(rowData.getColumnData("versionName")
                    .getValue());
            gameBaseInfo.setAppDate(rowData.getColumnData("appdate").getValue());
            gameBaseInfo.setUpdateTime(rowData.getColumnData("updatetime")
                    .getValue());

            return gameBaseInfo;
        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

    public SearchResultVo readSearchResult(String keyword, int pageNum) {
//        http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid=searchallgameapp&keyword=萝卜&pagenum=1&tablename=search&count=20
        Map<String, Object> urlVariables = new HashMap<String, Object>();

//        urlVariables.put("serviceid", "searchallgameapp");
        urlVariables.put("keyword", keyword);
        urlVariables.put("pageNum", pageNum);
//        urlVariables.put("tableName", SEARCH_RESULT_TABLE_NAME);
//        urlVariables.put("count", 20);

        HttpHeaders headers = getHttpHeaders();


        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

        ResponseEntity<byte[]> response = restTemplate.exchange(URL_SEARCH_GAME, HttpMethod.GET, entity, byte[].class, urlVariables);

        return parseSearchResult(response.getBody());
    }

    private HttpHeaders getHttpHeaders() {
        return getHttpHeaders(true);
    }

    private HttpHeaders getHttpHeaders(boolean isContainChannel) {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setContentLength(57);
        headers.set("user-agent", "Anroid/Lenovo798T");
        headers.set("storeua", "Anroid/Lenovo798T");
        headers.set("x-up-calling-line-id", "00000000000");
        headers.set("handphone", "00000000000");
        headers.set("handua", "9000000000");
        headers.set("settertype", "3");
        headers.set("version", "3");
        headers.set("imei", "000000000000000");
        headers.set("imsi", "000000000000000");
        headers.set("preassemble", "Android-v16>Common>V2.0.0>20120328>NA>NA>NA>beiyong>NA>NA");
        headers.set("companylogo", "18150");
        headers.set("sessionid", "202dc1f08ee64f8f896fafc3c5c62c03");
        headers.set("appfrom", "openfeint");
        headers.set("newclient", "1");
        headers.set("phoneAccessMode", "3");
        headers.set("usertype", "3");
        if (isContainChannel) {
            headers.set("clientchannelflag", "8");
        }
        return headers;
    }

    private SearchResultVo parseSearchResult(byte[] searchResultData) {
        try {
            SearchResultVo searchResultVo = new SearchResultVo();
            UniData uniData = UniDataUtil.fromBytes(searchResultData);
            UniData.TableData tableData = uniData.getTable(SEARCH_RESULT_TABLE_NAME);
            if (tableData == null) {
                return null;
            }
            UniData.RowData rowData = tableData.getRowData(0);
            searchResultVo.setPageNum(Integer.valueOf(rowData.getColumnData("pagenum")
                    .getValue()));
            searchResultVo.setReturnRows(Integer.valueOf(rowData.getColumnData("returnrows").getValue()));
            searchResultVo.setTotalRows(Integer.valueOf(rowData.getColumnData("totalrows").getValue()));

            UniData.TableData listData = uniData.getTable(SEARCH_RESULT_LIST_TABLE_NAME);

            if (listData != null) {
                List<SearchResultItemVo> items = new ArrayList<SearchResultItemVo>(searchResultVo.getReturnRows());

                int loopIndex = 0;

                while (loopIndex < searchResultVo.getReturnRows()) {

                    UniData.RowData itemRow = listData.getRowData(loopIndex);

                    SearchResultItemVo item = new SearchResultItemVo();

                    item.setId(itemRow.getColumnData("id").getValue());
                    item.setName(itemRow.getColumnData("name").getValue());
                    item.setIcon(itemRow.getColumnData("icon").getValue());
                    item.setRate(Integer.valueOf(itemRow.getColumnData("rate").getValue()));
                    item.setPrice(Integer.valueOf(itemRow.getColumnData("price").getValue()));
                    item.setSupplier(itemRow.getColumnData("supplier").getValue());
                    item.setCategory(itemRow.getColumnData("catagory").getValue());
                    item.setVersionCode(itemRow.getColumnData("versioncode").getValue());
                    item.setAppSource(itemRow.getColumnData("appsource").getValue());
                    item.setSize(Integer.parseInt(itemRow.getColumnData("size").getValue()));
                    item.setSizeInfo(itemRow.getColumnData("sizeinfo").getValue());
                    item.setNoSizeInfo(itemRow.getColumnData("nosiziinfo").getValue());
                    item.setPackageName(itemRow.getColumnData("packageName").getValue());

                    items.add(item);

                    loopIndex++;
                }

                searchResultVo.setSearchList(items);
            }


            return searchResultVo;

        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

    public GameDownloadVo readProductDownloadUrl(String productId) {

        Map<String, Object> urlVariables = new HashMap<String, Object>();

        urlVariables.put("productid", productId);

        HttpHeaders headers = getHttpHeaders(false);


        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

        ResponseEntity<byte[]> response = restTemplate.exchange(URL_PRODUCT_DOWNLOAD, HttpMethod.GET, entity, byte[].class, urlVariables);

        return parseGameDownloadInfo(response.getBody());
    }

    private GameDownloadVo parseGameDownloadInfo(byte[] body) {
        try {
            GameDownloadVo downloadVo = new GameDownloadVo();
            UniData uniData = UniDataUtil.fromBytes(body);
            UniData.TableData tableData = uniData.getTable(DOWNLOAD_RESULT_TABLE_NAME);
            if (tableData == null) {
                return null;
            }
            UniData.RowData rowData = tableData.getRowData(0);
            downloadVo.setId(rowData.getColumnData("id")
                    .getValue());
            downloadVo.setResult(Integer.valueOf(rowData.getColumnData("result").getValue()));
            downloadVo.setDescription(rowData.getColumnData("desc").getValue());
            downloadVo.setDownloadUrl(rowData.getColumnData("downurl").getValue());
            downloadVo.setIsFitApp(rowData.getColumnData("isFitApp").getValue());

            return downloadVo;

        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

    public SearchKeywordsVo readSearchAllKeywords() {
        try {
            RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
            SearchKeywordsVo response = restTemplate.getForObject(URL_SEARCH_ALL_KEYWORDS, SearchKeywordsVo.class);

            return response;
        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

    public SearchKeywordsVo readSearchKeywords(String keyword) {
        try {
            Map<String, Object> urlVariables = new HashMap<String, Object>();
            urlVariables.put("keyword", keyword);

            HttpHeaders headers = getHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

            ResponseEntity<byte[]> response = restTemplate.exchange(URL_SEARCH_KEYWORDS, HttpMethod.GET, entity, byte[].class, urlVariables);

            return parseSearchKeywords(response.getBody());
        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

    private SearchKeywordsVo parseSearchKeywords(byte[] searchResultData) {
        try {
            UniData uniData = UniDataUtil.fromBytes(searchResultData);
            UniData.TableData tableData = uniData.getTable(SEARCH_KEYWORD_TABLE_NAME);
            if (tableData == null) {
                return null;
            }
            UniData.RowData rowData = tableData.getRowData(0);
            String keywords = rowData.getColumnData("keywords").getValue();

            String[] keywordList = StringUtils.split(keywords, SEARCH_KEYWORD_RESULT_SPLIT_CHAR);

            if (keywordList == null) {
                return null;
            }

            SearchKeywordsVo result = new SearchKeywordsVo();
            List<SearchKeywordItemVo> items = Lists.newArrayList();

            for (String key : keywordList) {
                SearchKeywordItemVo item = new SearchKeywordItemVo();

                item.setHotWord(key);

                items.add(item);
            }

            result.setResult(0);
            result.setHotwordList(items);

            return result;

        } catch (Exception e) {
            logger.error("解析数据错误。", e);
            return null;
        }
    }

}
