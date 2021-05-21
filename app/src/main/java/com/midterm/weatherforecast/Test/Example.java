package com.midterm.weatherforecast.Test;

import com.google.gson.annotations.SerializedName;
import com.midterm.weatherforecast.model.Main;

import java.io.Serializable;

public class Example implements Serializable {
    @SerializedName("main")
    private Main main;

    public Example(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
