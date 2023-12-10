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
    public void findShortestRoute() {
        Route[][] route = Chart.getChart();

        ArrayList<Passenger> passengers = shuttle.getPassengers();
        passengers.forEach((Passenger passenger) -> {
            int passengerLocation = passenger.getLocation();

            int minDistance = 0;
            int minDistanceIndex = 0;
            for (int i = 0; i < route[passengerLocation].length; i++) {
                int passengerDistance = route[passengerLocation][i].getDistance();
                if (minDistance > passengerDistance) {
                    minDistance = passengerDistance;
                    minDistanceIndex = i;
                }
            }
            route[passengerLocation][minDistanceIndex].getPath().forEach((Integer i) -> System.out.print(i + " "));
            System.out.println(route[passengerLocation][minDistanceIndex].getDistance());
        });
        /*
        int minDistance = 0;
        shuttle.getPassengers().forEach((Passenger passenger) -> {
            int passengerDistance = route[1][passenger.getLocation()].getDistance();
            if (minDistance > passengerDistance) {
                minDistance = passengerDistance;
            }
        });

         */
    }
}
