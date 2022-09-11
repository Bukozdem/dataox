package lift.service;

import lift.model.Building;

class LiftTraffic {
    private final ShowStep showStep = new ShowStep();
    private final EnterService enterService = new EnterService();
    private final MoveService moveService = new MoveService();
    private final ExitService exitService = new ExitService();
    public void run(Building building) {
        showStep.toConsole(building);
        while (building.getCalls() != 0) {
            showStep.toConsole(moveService.move(enterService.enter(exitService.exit(building))));
        }
    }
}
