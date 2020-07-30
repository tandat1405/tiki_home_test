package com.datnt.tikihometest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuickLink {
    @SerializedName("data")
    @Expose
    private List<List<QuickLinkObject>> data = null;

    public List<List<QuickLinkObject>> getData() {
        return data;
    }

    public void setData(List<List<QuickLinkObject>> data) {
        this.data = data;
    }
}
