package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoDetailVo {
    @JsonProperty("id")
    private long id;

    @JsonProperty("type")
    private int type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("editor")
    private String editor;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("corner_mark")
    private int cornerMark;

    @JsonProperty("win_list")
    private String winList;

    @JsonProperty("game")
    private Game game;

    @JsonProperty("create_time")
    private long createTime;

    @JsonProperty("start_time")
    private long startTime;

    @JsonProperty("end_time")
    private long endTime;
}
