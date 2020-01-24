package aidev.com.citytemperature;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/bins/lw9qf")
    Call<Cities> doGetListResources();


    @GET("/data/2.5/weather?")
    Call<CityTemperature> doGetCity(@Query("q") String city,@Query("appid") String appid);

}
