package lift.service;

import lift.model.Building;
import lift.model.Direction;
import lift.model.Floor;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class EnterServiceImpl implements EnterService {
    public Building enter(Building building) {
        int freePlacesInLift = building.getLift().getCapacity();
        if (freePlacesInLift == 0) {
            return building;
        }
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        List<Passenger> passengersInLift = building.getLift().getPassengers();
        List<Passenger> passengersToGetIn = building.getFloors()
                .get(currentFloorIndex).getPassengersToGetIn();
        if (passengersInLift.isEmpty()) {
            if (passengersToGetIn.isEmpty()) {
                findNearestFloor(building, currentFloorIndex + 1);
            } else {
                changeDirectionWhenLifIsEmpty(building, passengersToGetIn,
                        currentFloorIndex + 1);
            }
        }
        List<Passenger> suitablePassengers = findSuitablePassengers(building,
                currentFloorIndex, passengersToGetIn);
        if (suitablePassengers.isEmpty()) {
            return building;
        }
        return comeIn(passengersInLift, passengersToGetIn, suitablePassengers, freePlacesInLift, currentFloorIndex, building);
    }

    private Building comeIn(List<Passenger> passengersInLift,
                            List<Passenger> passengersToGetIn,
                            List<Passenger> suitablePassengers,
                            int freePlacesInLift,
                            int currentFloorIndex,
                            Building building) {
        passengersToGetIn.removeAll(suitablePassengers);
        while (!suitablePassengers.isEmpty() && freePlacesInLift != 0) {
            passengersInLift.add(((LinkedList<Passenger>) suitablePassengers).poll());
            freePlacesInLift--;
        }
        passengersToGetIn.addAll(suitablePassengers);
        building.getLift().setCapacity(freePlacesInLift);
        building.getLift().setPassengers(passengersInLift);
        building.getFloors().get(currentFloorIndex).setPassengersToGetIn(passengersToGetIn);
        return building;
    }

    private List<Passenger> findSuitablePassengers(Building building,
                                                   int currentFloorIndex,
                                                   List<Passenger> passengersToGetIn) {
        List<Passenger> suitablePassengers;
        Direction direction = building.getLift().getDirection();
        int floorQuantity = building.getFloorQuantity();
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
        return suitablePassengers;
    }

    private void findNearestFloor(Building building, int currentFloor) {
        List<Floor> floors = building.getFloors();
        int nearestUpFloor = IntStream.rangeClosed(currentFloor, building.getFloorQuantity() - 1)
                .mapToObj(floors::get)
                .filter(f -> !f.getPassengersToGetIn().isEmpty())
                .map(Floor::getFloorNumber)
                .findFirst().orElse(-1);
        int nearestDownFloor = IntStream.range(0, currentFloor)
                .mapToObj(floors::get)
                .filter(f -> !f.getPassengersToGetIn().isEmpty())
                .map(Floor::getFloorNumber)
                .findFirst().orElse(-1);
        if (nearestUpFloor == -1) {
            building.getLift().setDirection(Direction.DOWN);
            return;
        }
        if (nearestDownFloor == -1) {
            building.getLift().setDirection(Direction.UP);
            return;
        }
        Direction newDirection = (currentFloor - nearestDownFloor) < (nearestUpFloor - currentFloor) ?
                Direction.DOWN : Direction.UP;
        building.getLift().setDirection(newDirection);
    }

    private void changeDirectionWhenLifIsEmpty(Building building,
                                               List<Passenger> passengersToGetIn,
                                               int currentFloor) {
        double numberOfPassengerToGetDown = passengersToGetIn.stream()
                .map(Passenger::getDesiredFloor)
                .filter(f -> f < currentFloor).count();
        Direction newDirection = ((double) passengersToGetIn.size()/2) <=
                numberOfPassengerToGetDown ? Direction.DOWN : Direction.UP;
        building.getLift().setDirection(newDirection);
    }
}
