package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class List implements Serializable {
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private java.util.List<Weather> weather = null;
    @SerializedName("dt_txt")
    private String dtTxt;

    public List(Main main, java.util.List<Weather> weather, String dtTxt) {
        this.main = main;
        this.weather = weather;
        this.dtTxt = dtTxt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
