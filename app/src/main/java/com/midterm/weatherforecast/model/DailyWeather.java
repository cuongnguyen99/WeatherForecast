package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DailyWeather implements Serializable {
    @SerializedName("list")
    private java.util.List<com.midterm.weatherforecast.model.List> list = null;

    public DailyWeather(java.util.List<List> list) {
        this.list = list;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }
}
