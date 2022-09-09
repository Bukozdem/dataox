package lift.service;

import lift.model.Building;
import lift.model.Floor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildingCreator {
    private static final int FLOOR_MIN_NUMBER = 5;
    private static final int FLOOR_MAX_NUMBER = 20;
    private final FloorCreator floorCreator = new FloorCreator();
    private final LiftCreator liftCreator = new LiftCreator();

    public Building constructBuilding() {
        Building building = new Building();
        int floorQuantity = new Random().nextInt(FLOOR_MIN_NUMBER, FLOOR_MAX_NUMBER);
        building.setFloorQuantity(floorQuantity);
        List<Floor> floorList = new ArrayList<>();
        int passengerTotalNumber = 0;
        for (int i = 0; i < building.getFloorQuantity(); i++) {
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
