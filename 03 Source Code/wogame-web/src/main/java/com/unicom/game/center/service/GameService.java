package com.unicom.game.center.service;

import com.google.common.collect.Maps;
import com.unicom.game.center.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
	
	@Value("#{properties['wogame.service.host']}")
	private String wogameHost;
	
	@Value("#{properties['wogame.service.banner']}")
	private String wogameBanner;
	
	@Value("#{properties['wogame.service.recommended']}")
	private String wogameRecommended;
	
	@Value("#{properties['wogame.service.week.hotlist']}")
	private String wogameHotList;
	
	@Value("#{properties['wogame.service.new']}")
	private String wogameNew;
	
	@Value("#{properties['wogame.service.category']}")
	private String wogameCategory;
	
	@Value("#{properties['wogame.service.category.show']}")
	private String wogameCategoryShow;

    public RollingAdListVo readRollingAdList() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", "{\"navigation\":\"001001\"}");
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameBanner), RollingAdListVo.class, urlVariables);
    }

    public RecommendedListVo readRecommendedList() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", "{\"page_num\":\"1\"}");
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        String responseDataString = (restTemplate.getForObject((wogameHost + wogameRecommended), String.class, urlVariables));
        return JsonMapper.nonDefaultMapper().fromJson(responseDataString, RecommendedListVo.class);
    }

    public WeekHotVo readWeekHotDownloadList(int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameHotList), WeekHotVo.class, urlVariables);
    }

    public NewListVo readNewList(int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameNew), NewListVo.class, urlVariables);
    }

    public CategoryListVo readCategoryList() {
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameCategory), CategoryListVo.class, "");
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
        return restTemplate.getForObject((wogameHost + wogameCategoryShow), ShowCategoryVo.class, urlVariables);
    }

    public GameInfoVo readGameInfo(String productId) {
        return zteService.readProductDetail(productId);
    }
}
