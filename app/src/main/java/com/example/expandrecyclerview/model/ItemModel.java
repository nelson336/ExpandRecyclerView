package com.example.expandrecyclerview.model;

import java.util.List;

/**
 * Created by nelson336 on 24/06/2017.
 */

public class ItemModel {
    private String desc;
    private List<ItemSubModel> subs;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ItemSubModel> getSubs() {
        return subs;
    }

    public void setSubs(List<ItemSubModel> subs) {
        this.subs = subs;
    }
}
