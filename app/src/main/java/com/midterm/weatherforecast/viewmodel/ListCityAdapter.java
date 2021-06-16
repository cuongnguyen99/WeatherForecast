package com.midterm.weatherforecast.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.weatherforecast.R;
import com.midterm.weatherforecast.model.ListCity;

import java.util.ArrayList;

public class ListCityAdapter extends RecyclerView.Adapter<ListCityAdapter.ViewHolder> implements Filterable {
    private ArrayList<ListCity> cities;
    private ArrayList<ListCity> citiesSearch;
    private RecyclerViewListener listener;

    public ListCityAdapter(ArrayList<ListCity> cities, RecyclerViewListener listener) {
        this.cities = cities;
        this.citiesSearch = cities;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvCities;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tvCities = (TextView) view.findViewById(R.id.tv_cityname);
        }
    }

    @NonNull
    @Override
    public ListCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cities, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCityAdapter.ViewHolder holder, int position) {
        ListCity list = cities.get(position);
        holder.tvCities.setText(list.getCityName());
        holder.itemView.setOnClickListener(view ->{
            listener.onClick(cities.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public interface RecyclerViewListener
    {
        void onClick(ListCity list);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if(search.isEmpty())
                {
                    cities = citiesSearch;
                }
                else
                {
                    ArrayList<ListCity> list = new ArrayList<>();
                    for (ListCity listCity : citiesSearch)
                    {
                        if(listCity.getCityName().toLowerCase().contains(search.toLowerCase()))
                        {
                            list.add(listCity);
                        }
                        cities = list;

                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cities;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cities = (ArrayList<ListCity>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
