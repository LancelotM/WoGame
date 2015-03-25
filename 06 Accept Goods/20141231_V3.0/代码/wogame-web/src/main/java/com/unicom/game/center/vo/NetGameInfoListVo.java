package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetGameInfoListVo extends BaseWogameVo{

    @JsonProperty("data")
    private NetGameInfoVo netGameInfoVo;

    public NetGameInfoVo getNetGameInfoVo() {
        return netGameInfoVo;
    }

    public void setNetGameInfoVo(NetGameInfoVo netGameInfoVo) {
        this.netGameInfoVo = netGameInfoVo;
    }
}
