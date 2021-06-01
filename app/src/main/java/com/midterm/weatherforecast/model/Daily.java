package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Daily implements Serializable {
    @SerializedName("dt")
    private Integer dt;
    @SerializedName("temp")
    private Temp temp;
    @SerializedName("humidity")
    private Integer humidity;
    @SerializedName("weather")
    private java.util.List<Weather> weather = null;

    public Daily(Integer dt, Temp temp, Integer humidity, List<Weather> weather) {
        this.dt = dt;
        this.temp = temp;
        this.humidity = humidity;
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
