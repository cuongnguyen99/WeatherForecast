package com.midterm.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.midterm.weatherforecast.R;
import com.midterm.weatherforecast.Test.Api;
import com.midterm.weatherforecast.Test.Example;
import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.CurrentWeather;
import com.midterm.weatherforecast.model.HourWeather;
import com.midterm.weatherforecast.model.Main;
import com.midterm.weatherforecast.model.Weather;
import com.midterm.weatherforecast.model.Wind;
import com.midterm.weatherforecast.viewmodel.CurrentWeatherService;
import com.midterm.weatherforecast.viewmodel.ThreeHourService;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
//    7027a7f8895e3698e6f30a47722eb07a
//    https://cdn.iconscout.com/icon/premium/png-512-thumb/cloud-speed-1061353.png
//    https://image.flaticon.com/icons/png/512/777/777610.png
    private ImageView imgIcon, imgHumidity, imgCloudSpeed;
    private TextView txtTemp, txtDescription, txtHumidity, txtWindSpeed, txtLoCation, txtCurrentDay;
    private TextView txtHourTemp1, txtHourTemp2, txtHourTemp3, txtHourTemp4, txtHourTemp5;
    private TextView txtHourTime1, txtHourTime2, txtHourTime3, txtHourTime4, txtHourTime5;
    private TextView txtHourHumi1, txtHourHumi2, txtHourHumi3, txtHourHumi4, txtHourHumi5;
    private ImageView imgHourIcon1, imgHourIcon2, imgHourIcon3, imgHourIcon4, imgHourIcon5;

    CurrentWeatherService currentWeatherService;
    private CurrentWeatherService apiService;
    private ThreeHourService apiHourService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        ApiCurrentWeatherCall();
        APIHourWeather();
    }

    public void Init() {
        //Current
        imgIcon = findViewById(R.id.iv_weather_current);
        imgHumidity = findViewById(R.id.iv_humidity_current);
        imgCloudSpeed = findViewById(R.id.iv_cloudspeed_current);
        txtTemp = findViewById(R.id.tv_nhietdo);
        txtDescription = findViewById(R.id.tv_tt_description);
        txtHumidity = findViewById(R.id.tv_humidity);
        txtWindSpeed = findViewById(R.id.tv_wind_speed);
        txtLoCation = findViewById(R.id.tv_location);
        txtCurrentDay = findViewById(R.id.tv_current_day);

        txtHourHumi1 = findViewById(R.id.tv_hour_humi_1);
        txtHourHumi2 = findViewById(R.id.tv_hour_humi_2);
        txtHourHumi3 = findViewById(R.id.tv_hour_humi_3);
        txtHourHumi4 = findViewById(R.id.tv_hour_humi_4);
        txtHourHumi5 = findViewById(R.id.tv_hour_humi_5);
        txtHourTemp1 = findViewById(R.id.tv_hour_temp_1);
        txtHourTemp2 = findViewById(R.id.tv_hour_temp_2);
        txtHourTemp3 = findViewById(R.id.tv_hour_temp_3);
        txtHourTemp4 = findViewById(R.id.tv_hour_temp_4);
        txtHourTemp5 = findViewById(R.id.tv_hour_temp_5);
        txtHourTime1 = findViewById(R.id.tv_hour_time_1);
        txtHourTime2 = findViewById(R.id.tv_hour_time_2);
        txtHourTime3 = findViewById(R.id.tv_hour_time_3);
        txtHourTime4 = findViewById(R.id.tv_hour_time_4);
        txtHourTime5 = findViewById(R.id.tv_hour_time_5);
        imgHourIcon1 = findViewById(R.id.img_hour_icon_1);
        imgHourIcon2 = findViewById(R.id.img_hour_icon_2);
        imgHourIcon3 = findViewById(R.id.img_hour_icon_3);
        imgHourIcon4 = findViewById(R.id.img_hour_icon_4);
        imgHourIcon5 = findViewById(R.id.img_hour_icon_5);
    }
    public void ApiCurrentWeatherCall()
    {
        apiService = new CurrentWeatherService();
        Call<CurrentWeather> call = apiService.getCurrentWeather();
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_LONG).show();

                CurrentWeather mydata = response.body();
                Main mainData = mydata.getMain();
                Wind windata = mydata.getWind();

                mydata.getWeather().get(0).LoadIconImg(imgIcon ,mydata.getWeather().get(0).getIcon());
                txtTemp.setText(String.valueOf((int)Math.round(mainData.getTemp()))+"°");
                txtHumidity.setText(String.format("%s",mainData.getHumidity()));
                txtWindSpeed.setText(String.valueOf(String.format("%sm/s",windata.getSpeed())));
                txtDescription.setText(mydata.getWeather().get(0).getDescription());
                txtCurrentDay.setText(String.format("%s",mydata.getCurrentTime()));
                txtLoCation.setText(mydata.getName());

                Log.e("Curent Time", mydata.getCurrentTime());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API fail", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void APIHourWeather()
    {
        apiHourService = new ThreeHourService();
        Call<HourWeather> call = apiHourService.getHourWeather();
        call.enqueue(new Callback<HourWeather>() {
            @Override
            public void onResponse(Call<HourWeather> call, Response<HourWeather> response) {
                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_LONG).show();
                HourWeather mydata = response.body();
                txtHourHumi1.setText(String.valueOf(mydata.getList().get(0).getMain().getHumidity()));
                txtHourHumi2.setText(String.valueOf(mydata.getList().get(1).getMain().getHumidity()));
                txtHourHumi3.setText(String.valueOf(mydata.getList().get(2).getMain().getHumidity()));
                txtHourHumi4.setText(String.valueOf(mydata.getList().get(3).getMain().getHumidity()));
                txtHourHumi5.setText(String.valueOf(mydata.getList().get(4).getMain().getHumidity()));
                txtHourTemp1.setText(String.valueOf((int)Math.round(mydata.getList().get(0).getMain().getTemp())+"°"));
                txtHourTemp2.setText(String.valueOf((int)Math.round(mydata.getList().get(1).getMain().getTemp())+"°"));
                txtHourTemp3.setText(String.valueOf((int)Math.round(mydata.getList().get(2).getMain().getTemp())+"°"));
                txtHourTemp4.setText(String.valueOf((int)Math.round(mydata.getList().get(3).getMain().getTemp())+"°"));
                txtHourTemp5.setText(String.valueOf((int)Math.round(mydata.getList().get(4).getMain().getTemp())+"°"));
                txtHourTime1.setText(mydata.getHourTime(mydata.getList().get(0).getDtTxt()));
                txtHourTime2.setText(mydata.getHourTime(mydata.getList().get(1).getDtTxt()));
                txtHourTime3.setText(mydata.getHourTime(mydata.getList().get(2).getDtTxt()));
                txtHourTime4.setText(mydata.getHourTime(mydata.getList().get(3).getDtTxt()));
                txtHourTime5.setText(mydata.getHourTime(mydata.getList().get(4).getDtTxt()));
                mydata.getList().get(0).getWeather().get(0).LoadIconImg(imgHourIcon1, mydata.getList().get(0).getWeather().get(0).getIcon());
                mydata.getList().get(1).getWeather().get(0).LoadIconImg(imgHourIcon2, mydata.getList().get(1).getWeather().get(0).getIcon());
                mydata.getList().get(2).getWeather().get(0).LoadIconImg(imgHourIcon3, mydata.getList().get(2).getWeather().get(0).getIcon());
                mydata.getList().get(3).getWeather().get(0).LoadIconImg(imgHourIcon4, mydata.getList().get(3).getWeather().get(0).getIcon());
                mydata.getList().get(4).getWeather().get(0).LoadIconImg(imgHourIcon5, mydata.getList().get(4).getWeather().get(0).getIcon());
//                Log.e("My-Check", mydata.getList().get(0).getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<HourWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}