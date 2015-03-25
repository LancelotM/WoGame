package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-7-1.
 */
/*
{
    "result": 0,
    "data": [
        {
            "id": 45,
            "word": "快乐酷宝"
        },
        {
            "id": 62,
            "word": "时空猎人"
        }
    ]
}
 */
public class SearchKeywordsVo {

    private Integer result;

    @JsonProperty("data")
    private List<SearchKeywordItemVo> hotWordData;


    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<SearchKeywordItemVo> getHotWordData() {
        return hotWordData;
    }

    public void setHotWordData(List<SearchKeywordItemVo> hotWordData) {
        this.hotWordData = hotWordData;
    }
}
