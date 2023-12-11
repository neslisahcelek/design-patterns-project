package shuttlemanager;

import observer.Observer;
import observer.Passenger;
import observer.Shuttle;

import java.util.ArrayList;

public class ShuttleManager {
    Shuttle shuttle;

    public ShuttleManager(Shuttle shuttle) {
        this.shuttle = shuttle;
    }
    public void findShortestRoute(ArrayList<Passenger> passengers, int startLocation, Route[][] route) {
        if (passengers.isEmpty()) {
            return;
        }

        ClosestPassenger closestPassenger = new ClosestPassenger(passengers.get(0), passengers.get(0).getLocation());

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < passengers.size(); i++) {
            int passengerDistance = route[startLocation][passengers.get(i).getLocation()].getDistance();
            System.out.println(passengerDistance);
            if (minDistance > passengerDistance) {
                minDistance = passengerDistance;
                closestPassenger = new ClosestPassenger(passengers.get(i), passengers.get(i).getLocation());
            }
        }
        System.out.println("min distance: " + minDistance);

        passengers.remove(closestPassenger.passenger);
        passengers.forEach((Passenger p) -> System.out.print("kalanlar: " + p.getLocation()));
        System.out.println();

        findShortestRoute(passengers, closestPassenger.location, route);
    }
}

class ClosestPassenger {
    Passenger passenger;
    int location;

    public ClosestPassenger(Passenger passenger, int location) {
        this.passenger = passenger;
        this.location = location;
    }
}