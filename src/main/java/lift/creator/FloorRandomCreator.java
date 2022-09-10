package lift.creator;

import lift.model.Floor;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.Random;

public class FloorRandomCreator {
    private static final int PASSENGER_MIN_NUMBER = 0;
    private static final int PASSENGER_MAX_NUMBER = 10;
    private final PassengerRandomCreator passengerCreator = new PassengerRandomCreator();

    public Floor constructFloor(int floorQuantity, int floorNumber) {
        Floor floor = new Floor();
        floor.setPassengerNumber(new Random().nextInt(PASSENGER_MIN_NUMBER, PASSENGER_MAX_NUMBER));
        floor.setFloorNumber(floorNumber);
        LinkedList<Passenger> passengerList = new LinkedList<>();
        for (int i = 0; i < floor.getPassengerNumber(); i++) {
            passengerList.add(passengerCreator.constructPassenger(floorQuantity, floorNumber));
        }
        floor.setPassengersIn(passengerList);
        return floor;
    }


}
