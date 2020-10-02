
public class Parking {

private final Car car;
private final String owner;
private final byte number;
private final boolean isEmpty;

    public Parking(Car car, String owner, byte number, boolean isEmpty) {
        this.car = car;
        this.owner = owner;
        this.number = number;
        this.isEmpty = isEmpty;
    }

    public Car getCar() {
        return car;
    }

    public String getOwner() {
        return owner;
    }

    public byte getNumber() {
        return number;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}


