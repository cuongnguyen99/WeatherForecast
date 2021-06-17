package com.midterm.weatherforecast.viewmodel;

import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.CurrentWeather;
import com.midterm.weatherforecast.model.HourWeather;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ThreeHourService {
    private CurrentWeatherAPI api;
    public ThreeHourService()
    {
        @GET("forecast")
        Call<HourWeather> getAPIHourWeather(@Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("units") String unit,
            @Query("appid") String key);
    }
    public Call<HourWeather> getHourWeather(double lat, double lon)
    {
        return api.getAPIHourWeather(String.valueOf(lat), String.valueOf(lon), "metric", "7027a7f8895e3698e6f30a47722eb07a");
    }
}
