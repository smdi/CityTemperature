package aidev.com.citytemperature;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityDialogFragment extends DialogFragment {

    private ImageView cityImage;
    private TextView cityName,windspeed,temperature,pressure,humidity;
    private APIInterface cityClient;
    private String name = "Delhi";
    private ProgressBar spinner;
//    private ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.citydialogfragment, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);


       initialise(customView);
       makeDataCall();
    }

    private void makeDataCall() {


        Call<CityTemperature> call1 = cityClient.doGetCity(name,"6eb81e5d0532149ef3fd369256795aa5");
        call1.enqueue(new Callback<CityTemperature>() {
            @Override
            public void onResponse(Call<CityTemperature> call, Response<CityTemperature> response) {
                CityTemperature cityTemperature = response.body();
                Map<String,Object> wind = cityTemperature.wind;
                Map<String,Object> main = cityTemperature.main;

                temperature.setText(main.get("temp").toString());
                humidity.setText(main.get("humidity").toString());
                pressure.setText(main.get("pressure").toString());
                windspeed.setText("wind speed  "+wind.get("speed").toString());

                spinner.setVisibility(View.GONE);
//                scrollView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<CityTemperature> call, Throwable t) {
                call.cancel();
                Toast.makeText(getActivity(),"No Internet",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialise(View customView) {

        spinner = (ProgressBar)customView.findViewById(R.id.spinnerfr);
        spinner.setVisibility(View.VISIBLE);
//        scrollView = (ScrollView)customView.findViewById(R.id.datascroll);
//        scrollView.setVisibility(View.GONE);

        cityClient = APIClientTemperature.getClient().create(APIInterface.class);
        cityImage = (ImageView)  customView.findViewById(R.id.cityimagefr);
        cityName = (TextView) customView.findViewById(R.id.citynamefr);
        temperature = (TextView) customView.findViewById(R.id.temperature);
        pressure = (TextView) customView.findViewById(R.id.pressure);
        humidity = (TextView) customView.findViewById(R.id.humidity);
        windspeed = (TextView)customView.findViewById(R.id.windspeed);

        SharedPreferences prefs = getActivity().getSharedPreferences("data", getActivity().MODE_PRIVATE);
        name = prefs.getString("name", "Delhi");//"No name defined" is the default value.

        cityName.setText(name);
        cityImage.setImageResource(CityStore.getImage(name));
    }



}