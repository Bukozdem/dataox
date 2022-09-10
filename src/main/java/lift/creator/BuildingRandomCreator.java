package lift.creator;

import lift.model.Building;
import lift.model.Floor;
import java.util.LinkedList;
import java.util.Random;

public class BuildingRandomCreator {
    private static final int FLOOR_MIN_NUMBER = 5;
    private static final int FLOOR_MAX_NUMBER = 6; ///////////////////////////////// change to 20
    private final FloorRandomCreator floorCreator = new FloorRandomCreator();
    private final LiftRandomCreator liftCreator = new LiftRandomCreator();

    public Building constructBuilding() {
        Building building = new Building();
        int floorQuantity = new Random().nextInt(FLOOR_MIN_NUMBER, FLOOR_MAX_NUMBER);
        building.setFloorQuantity(floorQuantity);
        LinkedList<Floor> floorList = new LinkedList<>();
        int passengerTotalNumber = 0;
        for (int i = 0; i < floorQuantity; i++) {
            Floor floor = floorCreator.constructFloor(floorQuantity, i + 1);
            floorList.add(floor);
            passengerTotalNumber += floor.getPassengerNumber();
        }
        building.setFloors(floorList);
        building.setCalls(passengerTotalNumber);
        building.setLift(liftCreator.constructLift());
        return building;
    }


}
