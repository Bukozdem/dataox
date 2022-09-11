package lift.service;

import lift.creator.BuildingRandomCreator;

public class StartWorkingImpl implements StartWorking {
    private final BuildingRandomCreator buildingCreator = new BuildingRandomCreator();
    private final LiftTraffic liftTraffic = new LiftTrafficImpl();

    public void liftOff() {
        liftTraffic.run(buildingCreator.constructBuilding());
    }
}
