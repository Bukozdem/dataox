package lift.creator;

import lift.model.Building;
import lift.model.Floor;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingRandomCreator {
    private static final int FLOOR_MIN_NUMBER = 5;
    private static final int FLOOR_MAX_NUMBER = 21;
    private final FloorRandomCreator floorCreator = new FloorRandomCreator();
    private final LiftCreator liftCreator = new LiftCreator();

    public Building constructBuilding() {
        Building building = new Building();
        int floorQuantity = new Random().nextInt(FLOOR_MIN_NUMBER, FLOOR_MAX_NUMBER);
        building.setFloorQuantity(floorQuantity);
        List<Floor> floorList = IntStream.rangeClosed(1, floorQuantity)
                .mapToObj(n -> floorCreator.constructFloor(floorQuantity, n))
                .collect(Collectors.toCollection(LinkedList::new));
        int passengerTotalNumber = floorList.stream()
                .map(Floor::getPassengersToGetIn)
                .mapToInt(List::size)
                .sum();
        building.setFloors(floorList);
        building.setCalls(passengerTotalNumber);
        building.setLift(liftCreator.constructLift());
        return building;
    }
}
