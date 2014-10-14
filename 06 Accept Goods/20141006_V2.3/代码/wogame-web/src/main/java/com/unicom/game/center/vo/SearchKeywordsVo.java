package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-7-1.
 */
/*
{
    "result": 0,
    "hotword_list": [
        {
            "sort": 1,
            "hot_word_id": 18,
            "hot_word": "捕鱼达人2"
        },
        {
            "sort": 2,
            "hot_word_id": 2,
            "hot_word": "保卫萝卜2"
        }
    ]
}
 */
public class SearchKeywordsVo {
    private Integer result;

    @JsonProperty("hotword_list")
    private List<SearchKeywordItemVo> hotwordList;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<SearchKeywordItemVo> getHotwordList() {
        return hotwordList;
    }

    public void setHotwordList(List<SearchKeywordItemVo> hotwordList) {
        this.hotwordList = hotwordList;
    }
}
