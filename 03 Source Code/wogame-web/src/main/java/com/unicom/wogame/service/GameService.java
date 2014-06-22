package com.unicom.wogame.service;

import com.google.common.collect.Maps;
import com.unicom.wogame.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springside.modules.mapper.JsonMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jession on 14-6-11.
 */
@Component
public class GameService {

    @Autowired
    private ZTEService zteService;

    private static final String URL_ROLLING_AD_LIST = "http://wogame.wostore.cn:8080/gameservice/rollingAdList.do?jsondata={jsondata}";
    private static final String URL_RECOMMENDED_LIST = "http://wogame.wostore.cn:8080/gameservice/recommendedList.do?jsondata={jsondata}";
    private static final String URL_WEEK_HOT_DOWNLOAD_LIST = "http://wogame.wostore.cn:8080/gameservice/weekHotDownloadList.do?jsondata={jsondata}";
    private static final String URL_NEW_LIST = "http://wogame.wostore.cn:8080/gameservice/newList.do?jsondata={jsondata}";
    private static final String URL_CATEGORY_LIST = "http://wogame.wostore.cn:8080/gameservice/categoryList.do";
    private static final String URL_SHOW_CATEGORY = "http://wogame.wostore.cn:8080/gameservice/showCategory.do?jsondata={jsondata}";

    public RollingAdListVo readRollingAdList() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", "{\"navigation\":\"001001\"}");
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject(URL_ROLLING_AD_LIST, RollingAdListVo.class, urlVariables);
    }

    public RecommendedListVo readRecommendedList() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", "{\"page_num\":\"1\"}");
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        String responseDataString = (restTemplate.getForObject(URL_RECOMMENDED_LIST, String.class, urlVariables));
        return JsonMapper.nonDefaultMapper().fromJson(responseDataString, RecommendedListVo.class);
    }

    public WeekHotVo readWeekHotDownloadList(int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject(URL_WEEK_HOT_DOWNLOAD_LIST, WeekHotVo.class, urlVariables);
    }

    public NewListVo readNewList(int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject(URL_NEW_LIST, NewListVo.class, urlVariables);
    }

    public CategoryListVo readCategoryList() {
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject(URL_CATEGORY_LIST, CategoryListVo.class, "");
    }

    public ShowCategoryVo readShowCategory(int categoryId, int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("category_id", categoryId);
        params.put("page_num", pageNum);
        params.put("page_size", pageSize);
        params.put("source", "UUC");

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject(URL_SHOW_CATEGORY, ShowCategoryVo.class, urlVariables);
    }

    public GameInfoVo readGameInfo(String productId) {
        return zteService.readProductDetail(productId);
    }
}
