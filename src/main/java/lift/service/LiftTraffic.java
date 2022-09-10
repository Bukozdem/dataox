package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Floor;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class LiftTraffic {
    private final ShowStep showStep = new ShowStep();
    public void run(Building building) {
        showStep.toConsole(building);
        while (building.getCalls() != 0) {
            showStep.toConsole(move(enter(exit(building))));
        }
    }
    private Building enter(Building building) {
        int freePlacesInLift = building.getLift().getCapacity();
        if (freePlacesInLift == 0) {
            return building;
        }
        Direction direction = building.getLift().getDirection();
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        Floor floor = building.getFloors().get(currentFloorIndex);
        LinkedList<Passenger> passengersToGetIn = floor.getPassengersIn();
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
        building.getFloors().get(currentFloorIndex).setPassengersIn(passengersToGetIn);
        return building;
    }

    private Building exit(Building building) {
        int calls = building.getCalls();
        LinkedList<Passenger> passengersInLift = building.getLift().getPassengers();
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        int passengerOut = building.getFloors().get(currentFloorIndex).getPassengersOut();
        Passenger passengerToGetOut = new Passenger(currentFloorIndex + 1);
        int counter = 0;
        while (passengersInLift.contains(passengerToGetOut)) {
            passengersInLift.remove(passengerToGetOut);
            passengerOut++;
            calls--;
            counter++;
        }
        building.getLift().setCapacity(building.getLift().getCapacity() + counter);
        building.getLift().setPassengers(passengersInLift);
        building.getFloors().get(currentFloorIndex).setPassengersOut(passengerOut);
        building.setCalls(calls);
        return building;
    }

    private Building move(Building building) {
        Direction direction = building.getLift().getDirection();
        int floor = building.getLift().getCurrentFloor();
        if (floor == 1) {
            direction = Direction.UP;
            building.getLift().setDirection(direction);
        }
        if (floor == building.getFloorQuantity()) {
            direction = Direction.DOWN;
            building.getLift().setDirection(direction);
        }

        if (direction == Direction.UP) {
            floor++;
        } else {
            floor--;
        }
        building.getLift().setCurrentFloor(floor);

        return building;
    }
}
