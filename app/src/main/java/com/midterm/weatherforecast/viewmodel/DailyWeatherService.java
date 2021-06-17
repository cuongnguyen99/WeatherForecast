package com.midterm.weatherforecast.viewmodel;

import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.DailyWeather;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DailyWeatherService {
    private CurrentWeatherAPI api;
    public DailyWeatherService()
    {
        @GET("onecall")
        Call<DailyWeather> getAPIDailyWeather(@Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("units") String units,
            @Query("exclude") String exclude,
            @Query("appid") String key
    }
    public Call<DailyWeather> getDailyWeather(double lat, double lon)
    {
        return api.getAPIWeather(String.valueOf(lat), String.valueOf(lon), "metric", "vi", "7027a7f8895e3698e6f30a47722eb07a");
    }
}
