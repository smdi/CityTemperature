package aidev.com.citytemperature;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;


public class CityTemperature {


    @SerializedName("name")
    public String name = null;

    @SerializedName("main")
    public Map<String,Object> main = null;

    @SerializedName("wind")
    public Map<String,Object> wind = null;


}
