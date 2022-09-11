package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Floor;
import lift.model.Lift;
import lift.model.Passenger;
import java.util.Iterator;
import java.util.stream.Collectors;

class ShowStep {
    private static final String UP = " ↑ ";
    private static final String DOWN = " ↓ ";
    private int step = 1;
    public void toConsole(Building building) {
        StringBuilder sb = new StringBuilder();
        sb.append("     * * * Step ").append(step++).append(" * * * ").append(System.lineSeparator())
                .append(System.lineSeparator());
        Iterator<Floor> iterator = building.getFloors().descendingIterator();
        Lift lift = building.getLift();
        while (iterator.hasNext()) {
            Floor floor = iterator.next();
            sb.append(floor.getPassengersOut()).append(" |");
            if (floor.getFloorNumber() == lift.getCurrentFloor()) {
                String direction = lift.getDirection() == Direction.UP ? UP : DOWN;
                sb.append(direction)
                        .append(padRight(lift.getPassengers().stream()
                                .map(Passenger::getDesiredFloor).toList().toString(), 15))
                        .append(direction);
            } else {
                sb.append(padRight(" ", 21));
            }
            sb.append("| ").append(floor.getPassengersToGetIn().stream()
                            .map(Passenger::getDesiredFloor)
                            .collect(Collectors.toList()))
                    .append(System.lineSeparator());
        }
        System.out.println(sb);
    }
    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
