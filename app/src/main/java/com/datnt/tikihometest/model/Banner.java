package com.datnt.tikihometest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banner {
    @SerializedName("data")
    @Expose
    private List<BannerObject> data = null;

    public List<BannerObject> getData() {
        return data;
    }

    public void setData(List<BannerObject> data) {
        this.data = data;
    }
}

