package com.midterm.weatherforecast.Test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
//    api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
//    http://api.openweathermap.org/data/2.5/weather?q={Vinh}&units={metric}&lang={vi}&appid={7027a7f8895e3698e6f30a47722eb07a}
    @GET("weather")
    Call<Example> getWeather(@Query("q") String cityname,
                             @Query("units") String unit,
                             @Query("lang") String lang,
                             @Query("appid") String key);
//    http://api.openweathermap.org/data/2.5/forecast?q=Vinh&appid=7027a7f8895e3698e6f30a47722eb07a
//    @GET("forecast")
//    Call<>
}
