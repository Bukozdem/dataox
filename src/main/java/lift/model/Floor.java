package lift.model;

import lombok.Data;
import java.util.List;

@Data
public class Floor {
    private int floorNumber;
    private List<Passenger> passengersToGetIn;
    private int passengersOut;
}
