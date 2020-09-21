import java.util.HashMap;
import java.util.Map;

public class Car {

    private int doors;
    public String name;
    public String owner;
    float wheels = 7;

    //char

    private boolean accident;
    Map<String,Integer> map = new HashMap();

    public Car(int doors, String name, String owner, boolean accident) {
        map.put("Dasha", 30);
        map.put("Masha", 40);
        map.put("Sasha", 50);
        this.doors = doors;
        this.name = name;
        this.owner = owner;
        this.accident = accident;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder("");
        res.append("doors = ").append(doors).append("\n").append("name = ").append(name).append("\n").append("owner = ").append(owner).append("\n").append("accident = ").append(accident).append("\n").append("map = ").append(map);
        return res.toString();
    }
}
