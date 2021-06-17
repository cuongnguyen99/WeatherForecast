package com.midterm.weatherforecast.viewmodel;

import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.CurrentWeather;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CurrentWeatherService {
    private CurrentWeatherAPI api;

    public CurrentWeatherService()
    {
        @GET("weather")
        Call<CurrentWeather> getAPIWeather(@Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("units") String unit,
            @Query("lang") String lang,
            @Query("appid") String key);
    }

    public Call<CurrentWeather> getCurrentWeather(double lat, double lon)
    {
        return api.getAPIDailyWeather(String.valueOf(lat),String.valueOf(lon), "metric", "hourly", "7027a7f8895e3698e6f30a47722eb07a");
    }
}
