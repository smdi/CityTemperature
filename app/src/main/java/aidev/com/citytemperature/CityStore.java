package aidev.com.citytemperature;

public class CityStore {

    public static int getImage(String name) {
        if (name.equalsIgnoreCase("Delhi")) {
            return R.drawable.delhi;
        }
        if (name.equalsIgnoreCase("Jaipur")) {
            return R.drawable.jaipur;
        }
        if (name.equalsIgnoreCase("London")) {
            return R.drawable.london;
        }
        if (name.equalsIgnoreCase("Mumbai")) {
            return R.drawable.mumbai;
        }
        if (name.equalsIgnoreCase("Paris")) {
            return R.drawable.paris;
        }
        if (name.equalsIgnoreCase("Pune")) {
            return R.drawable.pune;
        }
        if (name.equalsIgnoreCase("Rajkot")) {
            return R.drawable.rajkot;
        }
        if (name.equalsIgnoreCase("Singapore")) {
            return R.drawable.singapore;
        }
        if (name.equalsIgnoreCase("Sydney")) {
            return R.drawable.sydney;
        }
        if (name.equalsIgnoreCase("Tokyo")) {
            return R.drawable.tokyo;
        }
        if (name.equalsIgnoreCase("Zurich")) {
            return R.drawable.zurich;
        } else {
            return R.drawable.unknowncity;
        }


    }
    }
