package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityInfoItemVo {

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

    @JsonProperty("banner")
    private Banner banner;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {

        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getCreateTime() {

        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Game getGame() {

        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getWinList() {

        return winList;
    }

    public void setWinList(String winList) {
        this.winList = winList;
    }

    public int getCornerMark() {

        return cornerMark;
    }

    public void setCornerMark(int cornerMark) {
        this.cornerMark = cornerMark;
    }

    public String getIconUrl() {

        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getEditor() {

        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getIntro() {

        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
