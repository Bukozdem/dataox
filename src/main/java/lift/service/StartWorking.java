package lift.service;

import lift.model.Building;

public class StartWorking {
    private final BuildingCreator buildingCreator = new BuildingCreator();

    public void liftOff() {
        Building building = buildingCreator.constructBuilding();
        System.out.println(building);
    }
}
