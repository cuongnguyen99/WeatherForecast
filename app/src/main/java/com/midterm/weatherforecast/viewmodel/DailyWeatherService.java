package com.midterm.weatherforecast.viewmodel;

import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.DailyWeather;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyWeatherService {
    private CurrentWeatherAPI api;
    public DailyWeatherService()
    {
        api = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CurrentWeatherAPI.class);
    }
    public Call<DailyWeather> getDailyWeather()
    {
        return api.getAPIDailyWeather("Vinh", "metric", "7", "7027a7f8895e3698e6f30a47722eb07a");
    }
}
