package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class RecommendedListVo extends BaseWogameVo {

    @JsonProperty("data")
    private RecommendDataListVo recommendedListData;

    public RecommendDataListVo getRecommendedListData() {
        return recommendedListData;
    }

    public void setRecommendedListData(RecommendDataListVo recommendedListData) {
        this.recommendedListData = recommendedListData;
    }
}
