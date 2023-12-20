package shuttlemanager;

import manager.Process;
import movement.Movement;
import movement.Position;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

import java.util.ArrayList;

import static movement.Movement.destinationI;
import static movement.Movement.destinationJ;

public class ShuttleManager {
    Shuttle shuttle;

    public ShuttleManager(Shuttle shuttle) {
        this.shuttle = shuttle;
    }

    // en yakından başladığı için yolcular bittikten sonraki dönüşü uzayabilir

    public void findShortestRoute(ArrayList<Passenger> passengers, int startLocation, Route[][] route) {
        if (passengers.isEmpty()) {
            return;
        }

        ClosestPassenger closestPassenger = new ClosestPassenger(passengers.get(0), passengers.get(0).getStation());

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < passengers.size(); i++) {
            int passengerDistance = route[startLocation][passengers.get(i).getStation()].getDistance();
            System.out.println(passengerDistance);
            if (minDistance > passengerDistance) {
                minDistance = passengerDistance;
                closestPassenger = new ClosestPassenger(passengers.get(i), passengers.get(i).getStation());
            }
        }
        System.out.println("min distance: " + minDistance);

        passengers.remove(closestPassenger.passenger);
        passengers.forEach((Passenger p) -> System.out.print("kalanlar: " + p.getStation()));
        System.out.println();

        findShortestRoute(passengers, closestPassenger.location, route);
    }

    public void shuttleE()
    {
        //shuttle.getMovable().setVelocityDirection(Motion.calculateVelocityDirection(shuttle.getMovable(),5,5));
        shuttle.getMovable().setSpeed(5);
        System.out.println( shuttle.getMovable().getPosition().getI() + " " +  shuttle.getMovable().getPosition().getJ());
        //shuttle.updatePosition(Motion.calculateNextPositionWithDestination( shuttle.getMovable(),20,20));

    }

    public static void shuttleGo()
    {
        destinationI = Station.stations[11].getLocationI();
        destinationJ = Station.stations[11].getLocationJ();

        MovableBehavior movable = manager.Process.shuttles.get(0).getMovable();

        movable.setVelocityDirection(movement.Movement.calculateVelocityDirection(movable, destinationI, destinationJ));



    }

    public static void directionShuttle()
    {
        if (Process.shuttles.get(0).getMovable().getVelocityDirection().getJ() > 0) {
            Process.shuttles.get(0).getDrawable().direction = false;
        } else {
            Process.shuttles.get(0).getDrawable().direction = true;
        }
    }
}