package com.unicom.game.center.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-6-27
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class JsonModel {
    private String name;
    private List<Integer> data = new ArrayList<Integer>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public void addData(int data){
         this.data.add(data);
    }
}
