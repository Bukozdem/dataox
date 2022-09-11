package lift.service;

import lift.model.Building;

class LiftTrafficImpl implements LiftTraffic {
    private final ShowStep showStep = new ShowStepImpl();
    private final EnterService enterService = new EnterServiceImpl();
    private final MoveService moveService = new MoveServiceImpl();
    private final ExitService exitService = new ExitServiceImpl();
    public void run(Building building) {
        showStep.toConsole(building);
        while (building.getCalls() != 0) {
            showStep.toConsole(moveService.move(enterService.enter(exitService.exit(building))));
        }
    }
}
