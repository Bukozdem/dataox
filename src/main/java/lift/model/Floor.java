package lift.model;

import lombok.Data;

import java.util.List;

@Data
public class Floor {
    private int floorNumber;
    private int passengerNumber;
    private List<Passenger> passengersIn;
    private List<Passenger> passengersOut;

}
