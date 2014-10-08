package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by jession on 14-7-1.
 */
/*
{
            "id": 45,
            "word": "快乐酷宝"
}
 */
public class SearchKeywordItemVo {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("word")
    private String word;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
