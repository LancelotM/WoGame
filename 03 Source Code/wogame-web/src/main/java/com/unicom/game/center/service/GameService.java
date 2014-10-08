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

   @Value("#{properties['wogame.service.subjectlist']}")
    private String wogameSubjectList;

    @Value("#{properties['wogame.service.subject.gamelist']}")
    private String wogameSubjectGameList;

    @Value("#{properties['wogame.service.activity.infolist']}")
    private String wogameActivityInfoList;

    @Value("#{properties['wogame.service.game.infolist']}")
    private String wogameGameInfoList;

    @Value("#{properties['wogame.service.netgame.serverlist']}")
    private String wogameNetGameServerList;

    @Value("#{properties['wogame.service.netgame.infolist']}")
    private String wogameNetGameInfoList;

    @Value("#{properties['wogame.service.game.detail']}")
    private String wogameGameDetail;

    public GameDetailListVo readGameDetailList(String productId){

        Map params = Maps.newHashMap();

        params.put("product_id", productId);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameGameDetail), GameDetailListVo.class, urlVariables);
    }

    public NetGameInfoListVo readNetGameInfoList(int pageNum, int pageSize){

        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameNetGameInfoList), NetGameInfoListVo.class, urlVariables);
    }

    public NetGameServerListVo readNetGameServerList(int pageNum, int pageSize){

        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameNetGameServerList), NetGameServerListVo.class, urlVariables);
    }

    public GameInfoListVo readGameInfoList(int pageNum, int pageSize){

        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameGameInfoList), GameInfoListVo.class, urlVariables);
    }

    public ActivityInfoListVo readActivityInfoList(int pageNum, int pageSize){

        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameActivityInfoList), ActivityInfoListVo.class, urlVariables);
    }

    public SubjectDetailListVo readSubjectDetailList(int id, int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("id", id);
        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameSubjectGameList), SubjectDetailListVo.class, urlVariables);
    }

    public SubjectListVo readSubjectList(int pageNum, int pageSize){

        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameSubjectList), SubjectListVo.class, urlVariables);
    }

    public RollingAdListVo readRollingAdList() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", "{\"nav\":\"INDEX\"}");
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameBanner), RollingAdListVo.class, urlVariables);
    }

    public RecommendedListVo readRecommendedList(int pageNum, int pageSize) {
        Map params = Maps.newHashMap();

        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameRecommended), RecommendedListVo.class, urlVariables);
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

        params.put("id", categoryId);
        params.put("page_num", pageNum);
        params.put("page_size", pageSize);

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("jsondata", JsonMapper.nonDefaultMapper().toJson(params));

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        return restTemplate.getForObject((wogameHost + wogameCategoryShow), ShowCategoryVo.class, urlVariables);
    }

    public GameInfoVo readGameInfo(String productId) {
        return zteService.readProductDetail(productId);
    }
}
