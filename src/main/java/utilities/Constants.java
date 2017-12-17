package utilities;

public class Constants {
    public enum TemperatureUnits {
        STANDARD("°K"), METRIC("°C"), IMPERIAL("°F");

        private final String symbol;

        TemperatureUnits(String symbol) {
            this.symbol = symbol;
        }

        public static TemperatureUnits getUnitByDefault() {
            return STANDARD;
        }

        public static TemperatureUnits of(String temperatureMeasure) {
            switch (temperatureMeasure.toLowerCase()) {
                case "standard":
                    return STANDARD;

                case "metric":
                    return METRIC;

                case "imperial":
                    return IMPERIAL;

                default:
                    throw new IllegalArgumentException(String.format("Wrong temperature measure: %s", temperatureMeasure));
            }
        }
        public String getSymbol() {
            return symbol;
        }
    }
}
