package lift.service;

import lift.model.Floor;
import lift.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloorCreator {
    private static final int PASSENGER_MIN_NUMBER = 0;
    private static final int PASSENGER_MAX_NUMBER = 10;
    private final PassengerCreator passengerCreator = new PassengerCreator();

    public Floor constructFloor(int floorQuantity, int floorNumber) {
        Floor floor = new Floor();
        floor.setPassengerNumber(new Random().nextInt(PASSENGER_MIN_NUMBER, PASSENGER_MAX_NUMBER));
        floor.setFloorNumber(floorNumber);
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < floor.getPassengerNumber(); i++) {
            passengerList.add(passengerCreator.constructPassenger(floorQuantity, floorNumber));
        }
        floor.setPassengersIn(passengerList);
        return floor;
    }


}
