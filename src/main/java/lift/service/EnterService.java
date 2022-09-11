package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Floor;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.stream.Collectors;

class EnterService {
    Building enter(Building building) {
        int freePlacesInLift = building.getLift().getCapacity();
        if (freePlacesInLift == 0) {
            return building;
        }
        Direction direction = building.getLift().getDirection();
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        Floor floor = building.getFloors().get(currentFloorIndex);
        LinkedList<Passenger> passengersToGetIn = floor.getPassengersToGetIn();
        int floorQuantity = building.getFloorQuantity();
        LinkedList<Passenger> suitablePassengers;
        if (direction == Direction.UP && currentFloorIndex != floorQuantity - 1
                || currentFloorIndex == 0) {
            suitablePassengers = passengersToGetIn.stream()
                    .filter(p -> p.getDesiredFloor() > currentFloorIndex + 1)
                    .collect(Collectors.toCollection(LinkedList::new));
        } else {
            suitablePassengers = passengersToGetIn.stream()
                    .filter(p -> p.getDesiredFloor() < currentFloorIndex + 1)
                    .collect(Collectors.toCollection(LinkedList::new));
        }
        if (suitablePassengers.isEmpty()) {
            return building;
        }
        passengersToGetIn.removeAll(suitablePassengers);
        LinkedList<Passenger> passengersInLift = building.getLift().getPassengers();
        while (!suitablePassengers.isEmpty() && freePlacesInLift != 0) {
            passengersInLift.add(suitablePassengers.poll());
            freePlacesInLift--;
        }
        passengersToGetIn.addAll(suitablePassengers);
        building.getLift().setCapacity(freePlacesInLift);
        building.getLift().setPassengers(passengersInLift);
        building.getFloors().get(currentFloorIndex).setPassengersToGetIn(passengersToGetIn);
        return building;
    }
}
