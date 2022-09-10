package lift.model;

import lombok.Data;
import java.util.LinkedList;

@Data
public class Floor {
    private int floorNumber;
    private int passengerNumber;
    private LinkedList<Passenger> passengersIn;
    private int passengersOut;

}
