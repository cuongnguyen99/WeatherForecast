package com.midterm.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.midterm.weatherforecast.R;
import com.midterm.weatherforecast.model.ListCity;
import com.midterm.weatherforecast.viewmodel.ListCityAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListCityAdapter adapter;
    private ArrayList<ListCity> cities;
    private SearchView searchView;
    private ListCityAdapter.RecyclerViewListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.rv_list_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListCityAdapter(getListCity(), new ListCityAdapter.RecyclerViewListener() {
            @Override
            public void onClick(ListCity list) {
//                ShowToast(list.getCityName());
                Intent data = new Intent();
                data.putExtra("lat", list.getLat());
                data.putExtra("lon", list.getLon());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void ShowToast(String mess)
    {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<ListCity> getListCity()
    {
        ArrayList<ListCity> list = new ArrayList<>();
        list.add(new ListCity("Vinh", 18.679585, 105.681335));
        list.add(new ListCity("Soc Trang", 9.602521, 105.973907));
        list.add(new ListCity("Hue", 16.463713, 107.590866));
        list.add(new ListCity("Ha Noi", 21.027763, 105.834160));
        list.add(new ListCity("Ha Long", 20.959902, 107.042542));
        list.add(new ListCity("Da Nang", 16.047079, 108.206230));
        list.add(new ListCity("Can Tho", 10.045162, 105.746857));

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}