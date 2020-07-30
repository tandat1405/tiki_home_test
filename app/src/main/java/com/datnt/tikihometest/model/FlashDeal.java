package com.datnt.tikihometest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlashDeal {
    @SerializedName("data")
    @Expose
    private List<FlashDealObject> data = null;
    @SerializedName("tabs")
    @Expose
    private List<Tab> tabs = null;
    @SerializedName("version")
    @Expose
    private String version;

    public List<FlashDealObject> getData() {
        return data;
    }

    public void setData(List<FlashDealObject> data) {
        this.data = data;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
