package lift.service;

import lift.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowStep {
    private static final String UP = " ↑ ";
    private static final String DOWN = " ↓ ";
    private int step = 1;
    public void toConsole(Building building) {
        StringBuilder sb = new StringBuilder();
        sb.append(" * * * Step ").append(step++).append(" * * * ").append(System.lineSeparator())
                .append(System.lineSeparator());
        Iterator<Floor> iterator = building.getFloors().descendingIterator();
        Lift lift = building.getLift();
        while (iterator.hasNext()) {
            Floor floor = iterator.next();
            sb.append(floor.getPassengersOut()).append(" |");
            if (floor.getFloorNumber() == lift.getCurrentFloor() && !lift.getPassengers().isEmpty()) {
                String direction = lift.getDirection() == Direction.UP ? UP : DOWN;
                sb.append(direction)
                        .append(lift.getPassengers().stream()
                                .map(Passenger::getDesiredFloor)
                                .collect(Collectors.toList()))
                        .append(direction);
            } else {
                sb.append("                     ");
            }
            sb.append("| ").append(floor.getPassengersIn().stream()
                            .map(Passenger::getDesiredFloor)
                            .collect(Collectors.toList()))
                    .append(System.lineSeparator());
        }
        System.out.println(sb);
    }
}
