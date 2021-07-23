package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HourWeather implements Serializable {
    @SerializedName("list")
    private java.util.List<com.midterm.weatherforecast.model.List> list = null;

    public HourWeather(List<com.midterm.weatherforecast.model.List> list) {
        this.list = list;
    }

    public List<com.midterm.weatherforecast.model.List> getList() {
        return list;
    }

    public void setList(List<com.midterm.weatherforecast.model.List> list) {
        this.list = list;
    }

    public String getHourTime(String dt_txt)
    {
        char temp1 = 0;
        String temp2;
        String result = "";
        for(int i = 11; i<=12; i++)
        {
            temp1 = dt_txt.charAt(i);
            temp2 = Character.toString(temp1);
            result = result + temp2;
        }
        return result+"h";
    }
}
