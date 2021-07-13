package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// H

public class List implements Serializable {
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private java.util.List<Weather> weather = null;
    @SerializedName("dt_txt")
    private String dtTxt;
    @SerializedName("dt")
    private String dt;

    public List(Main main, java.util.List<Weather> weather, String dtTxt, String dt) {
        this.main = main;
        this.weather = weather;
        this.dtTxt = dtTxt;
        this.dt = dt;
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

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
    public String getDay(String daycode)
    {
        long d = Long.valueOf(daycode);
        Date date = new Date(d*1000L);
        SimpleDateFormat spd = new SimpleDateFormat("EEEE");
        String result = spd.format(date);

        return result;
    }
}
