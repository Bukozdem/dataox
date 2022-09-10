package lift.model;

import lombok.Data;
import java.util.LinkedList;

@Data
public class Lift {
    private int capacity;
    private Direction direction;
    private LinkedList<Passenger> passengers;
    private int currentFloor;
}
