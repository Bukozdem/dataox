package lift.creator;

import lift.model.Floor;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FloorRandomCreator {
    private static final int PASSENGER_MIN_NUMBER = 0;
    private static final int PASSENGER_MAX_NUMBER = 10;
    private final PassengerRandomCreator passengerCreator = new PassengerRandomCreator();

    public Floor constructFloor(int floorQuantity, int floorNumber) {
        Floor floor = new Floor();
        int passengerNumber = new Random().nextInt(PASSENGER_MIN_NUMBER, PASSENGER_MAX_NUMBER);
        floor.setFloorNumber(floorNumber);
        List<Passenger> passengerList = IntStream.rangeClosed(1, passengerNumber)
                .mapToObj(n -> passengerCreator.constructPassenger(floorQuantity, floorNumber))
                .collect(Collectors.toCollection(LinkedList::new));
        floor.setPassengersToGetIn(passengerList);
        return floor;
    }
}
