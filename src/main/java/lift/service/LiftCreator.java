package lift.service;

import lift.model.Direction;
import lift.model.Lift;

public class LiftCreator {
    private static final int CAPACITY = 5;
    private static final Direction direction = Direction.UP;

    public Lift constructLift() {
        Lift lift = new Lift();
        lift.setCapacity(CAPACITY);
        lift.setDirection(direction);
        return lift;
    }
}
