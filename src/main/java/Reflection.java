import java.lang.reflect.Field;
import java.util.*;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Car prius = new Car(5, "priusC", "Nick", false);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("wheels", 4);
        map.put("budget", 1000);
        map.put("serialnumber", 938361);
        map.put("doors", 100);

        HashSet<String> fieldsToOutMap = new HashSet<String>();
        HashSet<String> fieldsToOutCar = new HashSet<String>();

        HashSet<String> fieldsToCleanUp = new HashSet<String>();

        fieldsToCleanUp.add("wheels");
        fieldsToCleanUp.add("doors");
        fieldsToCleanUp.add("budget");

        Reflection.cleanup(map, fieldsToCleanUp, fieldsToOutMap);
        Reflection.cleanup(prius, fieldsToCleanUp, fieldsToOutCar);
        System.out.println(fieldsToOutCar);
        System.out.println(fieldsToOutMap);
    }

    public static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws NoSuchFieldException, IllegalAccessException {

        Class reflected = object.getClass();

        if (object instanceof Map) {
            Map origHashMap = new HashMap();
            Map hashMap = (HashMap) object;
            origHashMap.putAll((HashMap) object);

            for (String key : fieldsToCleanup) {
                reflected.getFields();
                if (!hashMap.containsKey(key)) {
                    object = origHashMap;
                    throw new IllegalArgumentException();
                }
                hashMap.remove(key);
            }

            Iterator it = hashMap.entrySet().iterator();

            while (it.hasNext()) {
                Object next = it.next();
                if (((Map.Entry) next).getValue() == null) {
                    fieldsToOutput.add(null);
                } else {
                    fieldsToOutput.add(((Map.Entry) next).getValue().toString());
                }
            }

        } else {
            Field[] fields = reflected.getDeclaredFields();

            for (String key : fieldsToCleanup) {

                for (Field field : fields) {

                    if (key.equals(field.getName())) {

                        if (field.getType().isPrimitive()) {

                            if (field.getType().getName().equals("boolean")) {
                                field.setAccessible(true);
                                field.set(object, false);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));
                            } else if (field.getType().getName().equals("char")) {
                                field.setAccessible(true);
                                field.set(object, (char) 0);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));
                            } else {
                                field.setAccessible(true);
                                field.set(object, (byte) 0);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));
                            }
                        } else {
                            field.setAccessible(true);
                            field.set(object, null);
                            fieldsToOutput.add(field.getName() + " = " + field.get(object));
                        }
                    }
                }
            }
        }
    }
}