package io;

import com.google.gson.annotations.SerializedName;

public class RequestFile {
    @SerializedName("cities")
    private String[] citiesNames;
    @SerializedName("tempunit")
    private String temperatureUnit;

    public String[] getCitiesNames() {
        return citiesNames;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }
}
