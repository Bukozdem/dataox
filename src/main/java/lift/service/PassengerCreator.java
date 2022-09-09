package lift.service;

import lift.model.Floor;
import lift.model.Passenger;

import java.util.Random;

public class PassengerCreator {
    public Passenger constructPassenger(int floorQuantity, int currentFloor) {
        Passenger passenger = new Passenger();
        passenger.setCurrentFloor(currentFloor);
        int desiredFloor = currentFloor;
        while (desiredFloor == currentFloor) {
            desiredFloor = new Random().nextInt(1, floorQuantity);
        }
        passenger.setDesiredFloor(desiredFloor);
        return passenger;
    }
}
