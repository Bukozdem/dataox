package lift.creator;

import lift.model.Passenger;
import java.util.Random;

public class PassengerRandomCreator {
    private static final int FIRST_FLOOR = 1;
    public Passenger constructPassenger(int floorQuantity, int currentFloor) {
        Passenger passenger = new Passenger();
        int desiredFloor = currentFloor;
        while (desiredFloor == currentFloor) {
            desiredFloor = new Random().nextInt(FIRST_FLOOR, floorQuantity);
        }
        passenger.setDesiredFloor(desiredFloor);
        return passenger;
    }
}
