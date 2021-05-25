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
    Call<CurrentWeather> getAPIWeather(@Query("q") String cityname,
                             @Query("units") String unit,
                             @Query("lang") String lang,
                             @Query("appid") String key);
    //    http://api.openweathermap.org/data/2.5/forecast?q=Vinh&appid=7027a7f8895e3698e6f30a47722eb07a
    @GET("forecast")
    Call<HourWeather> getAPIHourWeather(@Query("q") String cityname,
                                                 @Query("units") String unit,
                                                 @Query("appid") String key);
    //    http://api.openweathermap.org/data/2.5/forecast?q=vinh&units=metric&cnt=7&appid=7027a7f8895e3698e6f30a47722eb07a
    @GET("forecast")
    Call<DailyWeather> getAPIDailyWeather(@Query("q") String vityname,
                                          @Query("units") String units,
                                          @Query("cnt") String day,
                                          @Query("appid") String key
                                          );
}
