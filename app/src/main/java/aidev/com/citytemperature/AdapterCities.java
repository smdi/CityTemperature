package aidev.com.citytemperature;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterCities extends RecyclerView.Adapter<AdapterCities.ViewHolder>  {


    private List<Cities.City> listitem;
    Context ctx;


    public AdapterCities(Context ctx, List<Cities.City> listitem) {
        this.listitem = listitem;
        this.ctx = ctx;
    }

    @Override
    public AdapterCities.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Cities.City  cities = listitem.get(position);
        final String name = cities.name;
        holder.cityName.setText(name);
        holder.cityImage.setImageResource(CityStore.getImage(name));
        holder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFragment(name);
            }
        });

        holder.cityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(name);
            }
        });
    }

    private void openFragment(String name) {

        SharedPreferences.Editor editor = ctx.getSharedPreferences("data", ctx.MODE_PRIVATE).edit();
        editor.putString("name", name);
        editor.apply();
        CityDialogFragment dialogFragment = new CityDialogFragment();
        dialogFragment.show(((FragmentActivity)ctx).getSupportFragmentManager(), "city");
    }


    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cityName;
        public ImageView cityImage;

        public ViewHolder(View itemView) {
            super(itemView);

            cityName  = (TextView) itemView.findViewById(R.id.cityname);
            cityImage = (ImageView) itemView.findViewById(R.id.cityimage);

        }
    }

    public void updateList(List<Cities.City> listitem){
        this.listitem = listitem;
        notifyDataSetChanged();
    }
}
