package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// H

public class DailyWeather implements Serializable {
    @SerializedName("daily")
    private java.util.List<Daily> daily = null;

    public DailyWeather(List<Daily> daily) {
        this.daily = daily;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
    public String ConvertToDate(int dt)
    {
        String value = String.valueOf(dt);
        long L = Long.valueOf(value);
        Date date = new Date(L*1000L);
        SimpleDateFormat format = new SimpleDateFormat("EEE");
        String input = format.format(date);

        return input;
    }
}
