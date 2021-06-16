package com.midterm.weatherforecast.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.midterm.weatherforecast.R;
import com.midterm.weatherforecast.api.CurrentWeatherAPI;
import com.midterm.weatherforecast.model.CurrentWeather;
import com.midterm.weatherforecast.model.DailyWeather;
import com.midterm.weatherforecast.model.HourWeather;
import com.midterm.weatherforecast.model.List;
import com.midterm.weatherforecast.model.Main;
import com.midterm.weatherforecast.model.Weather;
import com.midterm.weatherforecast.model.Wind;
import com.midterm.weatherforecast.viewmodel.CurrentWeatherService;
import com.midterm.weatherforecast.viewmodel.DailyWeatherService;
import com.midterm.weatherforecast.viewmodel.ThreeHourService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Locale;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_CODE = 1001;

    private ImageView imgIcon, imgHumidity, imgCloudSpeed;
    private TextView txtTemp, txtDescription,txtHumidity, txtWindSpeed, txtLoCation, txtCurrentDay;
    private TextView txtHourTemp1, txtHourTemp2, txtHourTemp3, txtHourTemp4, txtHourTemp5;
    private TextView txtHourTime1, txtHourTime2, txtHourTime3, txtHourTime4, txtHourTime5;
    private TextView txtHourHumi1, txtHourHumi2, txtHourHumi3, txtHourHumi4, txtHourHumi5;
    private ImageView imgHourIcon1, imgHourIcon2, imgHourIcon3, imgHourIcon4, imgHourIcon5;
    private TextView txtDailyTemp1, txtDailyTemp2, txtDailyTemp3, txtDailyTemp4, txtDailyTemp5, txtDailyTemp6, txtDailyTemp7;
    private TextView txtDailyHumi1, txtDailyHumi2, txtDailyHumi3, txtDailyHumi4, txtDailyHumi5, txtDailyHumi6, txtDailyHumi7;
    private TextView txtDailyDay1, txtDailyDay2, txtDailyDay3, txtDailyDay4, txtDailyDay5, txtDailyDay6, txtDailyDay7;
    private ImageView imgDailyIcon1, imgDailyIcon2, imgDailyIcon3, imgDailyIcon4, imgDailyIcon5, imgDailyIcon6, imgDailyIcon7;

    CurrentWeatherService currentWeatherService;
    private CurrentWeatherService apiService;
    private ThreeHourService apiHourService;
    private DailyWeatherService apiDailyService;
    private FusedLocationProviderClient client;
    private LocationManager locationManager;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    public static double lat;
    public static double lon;
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        client = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        lat = location.getLatitude();
                        lon = location.getLongitude();
                        Log.e(TAG, "Lat 1:" +lat);
                        ApiCurrentWeatherCall(lat, lon);
                        APIHourWeather(lat, lon);
                        APIDailyWeather(lat, lon);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "You need turn on your location!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void Init() {
        //Current
        imgIcon = findViewById(R.id.iv_weather_current);
        imgHumidity = findViewById(R.id.iv_humidity_current);
        imgCloudSpeed = findViewById(R.id.iv_cloudspeed_current);
        txtTemp = findViewById(R.id.tv_nhietdo);
        txtHumidity = findViewById(R.id.tv_humidity);
        txtWindSpeed = findViewById(R.id.tv_wind_speed);
        txtLoCation = findViewById(R.id.tv_location);
        txtCurrentDay = findViewById(R.id.tv_current_day);
        txtDescription = findViewById(R.id.tv_current_des);

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

        txtDailyHumi1 = findViewById(R.id.tv_daily_humi1);
        txtDailyHumi2 = findViewById(R.id.tv_daily_humi2);
        txtDailyHumi3 = findViewById(R.id.tv_daily_humi3);
        txtDailyHumi4 = findViewById(R.id.tv_daily_humi4);
        txtDailyHumi5 = findViewById(R.id.tv_daily_humi5);
        txtDailyHumi6 = findViewById(R.id.tv_daily_humi6);
        txtDailyHumi7 = findViewById(R.id.tv_daily_humi7);
        txtDailyTemp1 = findViewById(R.id.tv_daily_temp1);
        txtDailyTemp2 = findViewById(R.id.tv_daily_temp2);
        txtDailyTemp3 = findViewById(R.id.tv_daily_temp3);
        txtDailyTemp4 = findViewById(R.id.tv_daily_temp4);
        txtDailyTemp5 = findViewById(R.id.tv_daily_temp5);
        txtDailyTemp6 = findViewById(R.id.tv_daily_temp6);
        txtDailyTemp7 = findViewById(R.id.tv_daily_temp7);
        txtDailyDay1 = findViewById(R.id.tv_daily_time1);
        txtDailyDay2 = findViewById(R.id.tv_daily_time2);
        txtDailyDay3 = findViewById(R.id.tv_daily_time3);
        txtDailyDay4 = findViewById(R.id.tv_daily_time4);
        txtDailyDay5 = findViewById(R.id.tv_daily_time5);
        txtDailyDay6 = findViewById(R.id.tv_daily_time6);
        txtDailyDay7 = findViewById(R.id.tv_daily_time7);
        imgDailyIcon1 = findViewById(R.id.img_daily_icon1);
        imgDailyIcon2 = findViewById(R.id.img_daily_icon2);
        imgDailyIcon3 = findViewById(R.id.img_daily_icon3);
        imgDailyIcon4 = findViewById(R.id.img_daily_icon4);
        imgDailyIcon5 = findViewById(R.id.img_daily_icon5);
        imgDailyIcon6 = findViewById(R.id.img_daily_icon6);
        imgDailyIcon7 = findViewById(R.id.img_daily_icon7);

        //Menu toolbar
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.draw_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Directional
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void ApiCurrentWeatherCall(double lat, double lon) {
        apiService = new CurrentWeatherService();
        Call<CurrentWeather> call = apiService.getCurrentWeather(lat, lon);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
//                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_LONG).show();

                CurrentWeather mydata = response.body();
                Main mainData = mydata.getMain();
                Wind windata = mydata.getWind();

                mydata.getWeather().get(0).LoadIconImg(imgIcon, mydata.getWeather().get(0).getIcon());
                txtTemp.setText(String.valueOf((int) Math.round(mainData.getTemp())) + "°");
                txtHumidity.setText(String.format("%s", mainData.getHumidity()));
                txtWindSpeed.setText(String.valueOf(String.format("%sm/s", windata.getSpeed())));
                txtDescription.setText(mydata.getWeather().get(0).getDescription());
                txtCurrentDay.setText(String.format("%s", mydata.getCurrentTime()));
                txtLoCation.setText(mydata.getName());

                Log.e("Curent Time", mydata.getCurrentTime());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void APIHourWeather(double lat, double lon) {
        apiHourService = new ThreeHourService();
        Call<HourWeather> call = apiHourService.getHourWeather(lat, lon);
        call.enqueue(new Callback<HourWeather>() {
            @Override
            public void onResponse(Call<HourWeather> call, Response<HourWeather> response) {
//                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_LONG).show();
                HourWeather mydata = response.body();
                txtHourHumi1.setText(String.valueOf(mydata.getList().get(0).getMain().getHumidity()));
                txtHourHumi2.setText(String.valueOf(mydata.getList().get(1).getMain().getHumidity()));
                txtHourHumi3.setText(String.valueOf(mydata.getList().get(2).getMain().getHumidity()));
                txtHourHumi4.setText(String.valueOf(mydata.getList().get(3).getMain().getHumidity()));
                txtHourHumi5.setText(String.valueOf(mydata.getList().get(4).getMain().getHumidity()));
                txtHourTemp1.setText(String.valueOf((int) Math.round(mydata.getList().get(0).getMain().getTemp()) + "°"));
                txtHourTemp2.setText(String.valueOf((int) Math.round(mydata.getList().get(1).getMain().getTemp()) + "°"));
                txtHourTemp3.setText(String.valueOf((int) Math.round(mydata.getList().get(2).getMain().getTemp()) + "°"));
                txtHourTemp4.setText(String.valueOf((int) Math.round(mydata.getList().get(3).getMain().getTemp()) + "°"));
                txtHourTemp5.setText(String.valueOf((int) Math.round(mydata.getList().get(4).getMain().getTemp()) + "°"));
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

    public void APIDailyWeather(double lat, double lon) {
        apiDailyService = new DailyWeatherService();
        Call<DailyWeather> call = apiDailyService.getDailyWeather(lat, lon);
        call.enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, Response<DailyWeather> response) {
//                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_LONG).show();
                DailyWeather mydata = response.body();

                txtDailyHumi1.setText(String.valueOf(mydata.getDaily().get(0).getHumidity()));
                txtDailyHumi2.setText(String.valueOf(mydata.getDaily().get(1).getHumidity()));
                txtDailyHumi3.setText(String.valueOf(mydata.getDaily().get(2).getHumidity()));
                txtDailyHumi4.setText(String.valueOf(mydata.getDaily().get(3).getHumidity()));
                txtDailyHumi5.setText(String.valueOf(mydata.getDaily().get(4).getHumidity()));
                txtDailyHumi6.setText(String.valueOf(mydata.getDaily().get(5).getHumidity()));
                txtDailyHumi7.setText(String.valueOf(mydata.getDaily().get(6).getHumidity()));

                txtDailyTemp1.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(0).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(0).getTemp().getMax())));
                txtDailyTemp2.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(1).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(1).getTemp().getMax())));
                txtDailyTemp3.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(2).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(2).getTemp().getMax())));
                txtDailyTemp4.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(3).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(3).getTemp().getMax())));
                txtDailyTemp5.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(4).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(4).getTemp().getMax())));
                txtDailyTemp6.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(5).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(5).getTemp().getMax())));
                txtDailyTemp7.setText(String.format("%s/%s", (int) Math.round(mydata.getDaily().get(6).getTemp().getMin()), (int) Math.round(mydata.getDaily().get(6).getTemp().getMax())));

                mydata.getDaily().get(0).getWeather().get(0).LoadIconImg(imgDailyIcon1, mydata.getDaily().get(0).getWeather().get(0).getIcon());
                mydata.getDaily().get(1).getWeather().get(0).LoadIconImg(imgDailyIcon2, mydata.getDaily().get(1).getWeather().get(0).getIcon());
                mydata.getDaily().get(2).getWeather().get(0).LoadIconImg(imgDailyIcon3, mydata.getDaily().get(2).getWeather().get(0).getIcon());
                mydata.getDaily().get(3).getWeather().get(0).LoadIconImg(imgDailyIcon4, mydata.getDaily().get(3).getWeather().get(0).getIcon());
                mydata.getDaily().get(4).getWeather().get(0).LoadIconImg(imgDailyIcon5, mydata.getDaily().get(4).getWeather().get(0).getIcon());
                mydata.getDaily().get(5).getWeather().get(0).LoadIconImg(imgDailyIcon6, mydata.getDaily().get(5).getWeather().get(0).getIcon());
                mydata.getDaily().get(6).getWeather().get(0).LoadIconImg(imgDailyIcon7, mydata.getDaily().get(6).getWeather().get(0).getIcon());


                txtDailyDay1.setText(mydata.ConvertToDate(mydata.getDaily().get(0).getDt()));
                txtDailyDay2.setText(mydata.ConvertToDate(mydata.getDaily().get(1).getDt()));
                txtDailyDay3.setText(mydata.ConvertToDate(mydata.getDaily().get(2).getDt()));
                txtDailyDay4.setText(mydata.ConvertToDate(mydata.getDaily().get(3).getDt()));
                txtDailyDay5.setText(mydata.ConvertToDate(mydata.getDaily().get(4).getDt()));
                txtDailyDay6.setText(mydata.ConvertToDate(mydata.getDaily().get(5).getDt()));
                txtDailyDay7.setText(mydata.ConvertToDate(mydata.getDaily().get(6).getDt()));
            }
            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API fail", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_search:
                Intent intent_search = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent_search, REQUEST_CODE);
                break;
            case R.id.nav_reload:
                ApiCurrentWeatherCall(lat, lon);
                APIHourWeather(lat, lon);
                APIDailyWeather(lat, lon);
                break;
            case R.id.nav_rate:
                Toast.makeText(MainActivity.this, "We will update soon ", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_contact:
                Toast.makeText(MainActivity.this, "We will update soon ", Toast.LENGTH_LONG).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                double lat = data.getDoubleExtra("lat", 0);
                double lon = data.getDoubleExtra("lon", 0);
                ApiCurrentWeatherCall(lat, lon);
                APIHourWeather(lat, lon);
                APIDailyWeather(lat, lon);
            }
        }

    }
    private void ShowToast(String mess)
    {
        Toast.makeText(MainActivity.this, mess, Toast.LENGTH_SHORT).show();
    }
}