package com.midterm.weatherforecast.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Temp implements Serializable {
    @SerializedName("min")
    private Double min;
    @SerializedName("max")
    private Double max;

    public Temp(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
