package lift.model;

import lombok.Data;
import java.util.List;

@Data
public class Lift {
    private int capacity;
    private Direction direction;
    private List<Passenger> passengers;
    private int currentFloor;
}
