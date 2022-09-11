package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Lift;

class MoveService {
    Building move(Building building) {
        Lift lift = building.getLift();
        Direction direction = lift.getDirection();
        int floor = lift.getCurrentFloor();
        if (floor == 1) {
            direction = Direction.UP;
            building.getLift().setDirection(direction);
        }
        if (floor == building.getFloorQuantity()) {
            direction = Direction.DOWN;
            building.getLift().setDirection(direction);
        }
        if (direction == Direction.DOWN) {
            floor--;
        } else {
            floor++;
        }
        building.getLift().setCurrentFloor(floor);
        return building;
    }
}
