package aidev.com.citytemperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText citySearch;
    private List<Cities.City> listView;
    private ProgressBar spinner;
    private APIInterface citiesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
        setCityData();
    }

    private void setCityData() {

        Call<Cities> call = citiesClient.doGetListResources();
        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                Cities cities = response.body();
                listView = cities.citilist;
                adapter = new AdapterCities(MainActivity.this,listView);
                recyclerView.setAdapter(adapter);

                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                call.cancel();
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialise() {

        citySearch = (EditText) findViewById(R.id.citysearch);
        recyclerView = (RecyclerView) findViewById(R.id.citylist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        listView = new ArrayList();
        spinner = (ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        citiesClient = APIClientCity.getClient().create(APIInterface.class);


        //add text watcher
        citySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }
    public void filter(String text){
        List<Cities.City> temp = new ArrayList();
        for(Cities.City d: listView){

            if(d.name.toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        adapter = new AdapterCities(MainActivity.this,temp);
        recyclerView.setAdapter(adapter);
    }
}
