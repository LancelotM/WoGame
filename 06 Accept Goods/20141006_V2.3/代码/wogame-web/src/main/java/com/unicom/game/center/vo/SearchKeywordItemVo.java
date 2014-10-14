package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jession on 14-7-1.
 */
/*
{
            "sort": 1,
            "hot_word_id": 18,
            "hot_word": "捕鱼达人2"
        }
 */
public class SearchKeywordItemVo {

    private Integer sort;
    @JsonProperty("hot_word_id")
    private Integer hotWordId;
    @JsonProperty("hot_word")
    private String hotWord;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getHotWordId() {
        return hotWordId;
    }

    public void setHotWordId(Integer hotWordId) {
        this.hotWordId = hotWordId;
    }

    public String getHotWord() {
        return hotWord;
    }

    public void setHotWord(String hotWord) {
        this.hotWord = hotWord;
    }
}
