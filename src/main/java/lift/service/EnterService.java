package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.stream.Collectors;

class EnterService {
    Building enter(Building building) {
        int freePlacesInLift = building.getLift().getCapacity();
        if (freePlacesInLift == 0) {
            return building;
        }
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        LinkedList<Passenger> passengersInLift = building.getLift().getPassengers();
        LinkedList<Passenger> passengersToGetIn = building.getFloors()
                .get(currentFloorIndex).getPassengersToGetIn();
        if (passengersInLift.isEmpty() && !passengersToGetIn.isEmpty()) {
            building = changeDirectionWhenLiftIsEmpty(building, passengersToGetIn,
                    currentFloorIndex + 1);
        }
        Direction direction = building.getLift().getDirection();
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

    private Building changeDirectionWhenLiftIsEmpty(Building building,
                                                    LinkedList<Passenger> passengersToGetIn,
                                                    int currentFloor) {
        int numberOfPassengerToGetDown = (int) passengersToGetIn.stream()
                .map(Passenger::getDesiredFloor)
                .filter(f -> f < currentFloor).count();
        Direction newDirection = passengersToGetIn.size()/2 <= numberOfPassengerToGetDown ? Direction.DOWN : Direction.UP;
        building.getLift().setDirection(newDirection);
        return building;
    }
}
