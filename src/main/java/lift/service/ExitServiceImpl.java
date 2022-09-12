package lift.service;

import lift.model.Building;
import lift.model.Passenger;
import java.util.LinkedList;
import java.util.List;

class ExitServiceImpl implements ExitService {
    public Building exit(Building building) {
        int calls = building.getCalls();
        List<Passenger> passengersInLift = building.getLift().getPassengers();
        int currentFloorIndex = building.getLift().getCurrentFloor() - 1;
        int passengerOut = building.getFloors().get(currentFloorIndex).getPassengersOut();
        Passenger passengerToGetOut = new Passenger(currentFloorIndex + 1);
        int leavesCounter = 0;
        while (passengersInLift.contains(passengerToGetOut)) {
            passengersInLift.remove(passengerToGetOut);
            passengerOut++;
            calls--;
            leavesCounter++;
        }
        building.getLift().setCapacity(building.getLift().getCapacity() + leavesCounter);
        building.getLift().setPassengers(passengersInLift);
        building.getFloors().get(currentFloorIndex).setPassengersOut(passengerOut);
        building.setCalls(calls);
        return building;
    }
}
