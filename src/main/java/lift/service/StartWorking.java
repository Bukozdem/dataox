package lift.service;

import lift.creator.BuildingRandomCreator;

public class StartWorking {
    private final BuildingRandomCreator buildingCreator = new BuildingRandomCreator();
    private final LiftTraffic liftTraffic = new LiftTraffic();

    public void liftOff() {
        liftTraffic.run(buildingCreator.constructBuilding());
    }
}
