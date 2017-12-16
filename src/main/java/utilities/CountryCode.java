package utilities;

import java.util.Arrays;
import java.util.Locale;

public class CountryCode {
    public static boolean isCountryCodeCorrect(String countryCode) {
        return Arrays.asList(Locale.getISOCountries()).contains(countryCode);
    }
}
