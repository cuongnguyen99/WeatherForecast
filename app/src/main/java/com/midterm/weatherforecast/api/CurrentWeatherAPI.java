package com.midterm.weatherforecast.api;

import com.midterm.weatherforecast.model.CurrentWeather;
import com.midterm.weatherforecast.model.DailyWeather;
import com.midterm.weatherforecast.model.HourWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrentWeatherAPI {
    //    api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

    //    http://api.openweathermap.org/data/2.5/weather?q={Vinh}&units={metric}&lang={vi}&appid={7027a7f8895e3698e6f30a47722eb07a}
    @GET("weather")
    Call<CurrentWeather> getAPIWeather(@Query("lat") String latitude,
                             @Query("lon") String longitude,
                             @Query("units") String unit,
                             @Query("lang") String lang,
                             @Query("appid") String key);
    //    http://api.openweathermap.org/data/2.5/forecast?q=Vinh&appid=7027a7f8895e3698e6f30a47722eb07a
    @GET("forecast")
    Call<HourWeather> getAPIHourWeather(@Query("lat") String latitude,
                                        @Query("lon") String longitude,
                                        @Query("units") String unit,
                                        @Query("appid") String key);
    //    https://api.openweathermap.org/data/2.5/onecall?lat=18.66&lon=105.69&units=metric&exclude=hourly&appid=d36636bb515a9f786a7d300557c7b328
    @GET("onecall")
    Call<DailyWeather> getAPIDailyWeather(@Query("lat") String latitude,
                                          @Query("lon") String longitude,
                                          @Query("units") String units,
                                          @Query("exclude") String exclude,
                                          @Query("appid") String key
                                          );
//    https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&units=metric&exclude=hourly&appid=d36636bb515a9f786a7d300557c7b328
}
