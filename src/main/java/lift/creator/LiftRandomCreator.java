package lift.creator;

import lift.model.Direction;
import lift.model.Lift;
import java.util.LinkedList;

public class LiftRandomCreator {
    private static final int CAPACITY = 5;
    private static final int INITIAL_FLOOR = 1;
    private static final Direction direction = Direction.UP;

    public Lift constructLift() {
        Lift lift = new Lift();
        lift.setCurrentFloor(INITIAL_FLOOR);
        lift.setCapacity(CAPACITY);
        lift.setDirection(direction);
        lift.setPassengers(new LinkedList<>());
        return lift;
    }
}
