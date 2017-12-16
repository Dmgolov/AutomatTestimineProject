package utilitiesTest;

import org.junit.Test;

public class TemperatureUnitsTest {
    @Test
    public void testGetTempUnitFromStringIfAllLettersAreLowered() {
        assertEquals(Constants.TemperatureUnits.STANDARD, Constants.TemperatureUnits.of("standard"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("metric"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("imperial"));
    }

    @Test
    public void testGetTempUnitFromStringIfAllLettersAreUpper() {
        assertEquals(Constants.TemperatureUnits.STANDARD, Constants.TemperatureUnits.of("STANDARD"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("METRIC"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("Imperial"));
    }

    @Test
    public void testGetTempUnitFromStringIfAllLettersAreInDifferentCases() {
        assertEquals(Constants.TemperatureUnits.STANDARD, Constants.TemperatureUnits.of("Standard"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("MEtric"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("imPERIal"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionIfNoSuchUnit() {
        Constants.TemperatureUnits.of("abc");
    }
}