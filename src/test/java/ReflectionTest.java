import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    @Test
    void cleanupWithMap() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("wheels", 4);
        map.put("budget", 1000);
        map.put("serialnumber", 938361);
        map.put("doors", 100);

        HashSet<String> fieldsToOutMap = new HashSet<String>();
        HashSet<String> fieldsToCleanupMap = new HashSet<String>();
        HashSet<String> fieldsToOutMapCheck = new HashSet<String>() {{
            add("1000");
            add("938361");
            add("100");
        }};
        fieldsToCleanupMap.add("wheels");
        Reflection.cleanup(map, fieldsToCleanupMap, fieldsToOutMap);
        assertEquals(fieldsToOutMap, fieldsToOutMapCheck);
    }

    @Test
    void cleanUpException() throws IllegalArgumentException {
        final HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("wheels", 4);
        map.put("budget", 1000);
        map.put("serialnumber", 938361);
        map.put("doors", 100);

        final HashSet<String> fieldsToOutMap = new HashSet<String>();
        final HashSet<String> fieldsToCleanupMap = new HashSet<String>();

        fieldsToCleanupMap.add("myWheels");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            Reflection.cleanup(map, fieldsToCleanupMap, fieldsToOutMap);
        });
    }

    @Test
    void cleanUpWithClass() throws NoSuchFieldException, IllegalAccessException {
        Car prius = new Car(5, "priusC", "Nick", false);

        final HashSet<String> fieldsToCleanUpCar = new HashSet<String>() {{
            add("wheels");
            add("doors");
            add("owner");
        }};

        final HashSet<String> fieldsToOutCarCheck = new HashSet<String>() {{
            add("wheels = 0.0");
            add("doors = 0");
            add("owner = null");
        }};

        HashSet<String> fieldsToOutCar = new HashSet<String>();

        Reflection.cleanup(prius, fieldsToCleanUpCar, fieldsToOutCar);
        assertEquals(fieldsToOutCar, fieldsToOutCarCheck);

        Parking parking = new Parking(prius, prius.getOwner(), (byte) 12, false);

        final HashSet<String> fieldsToCleanUpParking = new HashSet<String>() {{
            add("owner");
            add("number");
            add("isEmpty");
            add("car");
            add("wheels");
        }};

        final HashSet<String> fieldsToOutParkingCheck = new HashSet<String>() {{
            add("number = 0");
            add("isEmpty = false");
            add("car = null");
            add("owner = null");
        }};

        HashSet<String> fieldsToOutParking = new HashSet<String>();

        Reflection.cleanup(parking, fieldsToCleanUpParking, fieldsToOutParking);
        assertEquals(fieldsToOutParking, fieldsToOutParkingCheck);

    }
}