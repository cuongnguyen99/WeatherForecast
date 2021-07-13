package com.midterm.weatherforecast.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// G

public class Wind implements Serializable{
    @SerializedName("speed")
    private Double speed;

    public Wind(Double speed) {
        this.speed = speed;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
