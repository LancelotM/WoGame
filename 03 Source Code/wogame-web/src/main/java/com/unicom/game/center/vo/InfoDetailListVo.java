package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoDetailListVo extends BaseWogameVo{
    @JsonProperty("data")
    private InfoDetailVo infoDetailVo;

    public InfoDetailVo getInfoDetailVo() {
        return infoDetailVo;
    }

    public void setInfoDetailVo(InfoDetailVo infoDetailVo) {
        this.infoDetailVo = infoDetailVo;
    }
}
