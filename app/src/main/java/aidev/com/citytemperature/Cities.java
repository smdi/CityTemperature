package aidev.com.citytemperature;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cities {

    @SerializedName("cities")
    public List<City> citilist = null;

    public class City {

        @SerializedName("name")
        public String name;

    }
}
