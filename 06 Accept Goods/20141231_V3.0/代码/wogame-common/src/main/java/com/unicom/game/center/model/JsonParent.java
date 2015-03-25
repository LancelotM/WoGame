package com.unicom.game.center.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-6-27
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class JsonParent {

    private String status;
    private List<String> unit = new ArrayList<String>();
    private List<JsonModel> result = new ArrayList<JsonModel>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getUnit() {
        return unit;
    }

    public void setUnit(List<String> unit) {
        this.unit = unit;
    }

    public void addUnit(String data){
        this.unit.add(data);
    }

    public List<JsonModel> getResult() {
        return result;
    }

    public void setResult(List<JsonModel> result) {
        this.result = result;
    }

    public void addResult(JsonModel model){
        this.result.add(model);
    }
}

