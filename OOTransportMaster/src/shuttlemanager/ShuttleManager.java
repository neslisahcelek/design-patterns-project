package shuttlemanager;

import manager.Process;
import movement.Position;
import movement.VelocityDirection;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

import java.util.ArrayList;
import java.util.List;

import static movement.Movement.calculateVelocityDirection;


public class ShuttleManager {
    static Shuttle shuttle;
    ArrayList<Integer> route = new ArrayList<>(List.of(1, 2, 13, 12, 11, 10, 9, 15, 16));
    int step = 1;
    public static int done = 0;

    int timeStart;
    int timeEnd;

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


    public void checkShuttleManager()
    {
        boolean arrivedStation = shuttle.getMovable().position.getI() ==
                Station.getStation(route.get(step-1)).getPosition().getI()
                && shuttle.getMovable().position.getJ() ==
                        Station.getStation(route.get(step-1)).getPosition().getJ()
                && done == 0;

        if(arrivedStation)
        {
            shuttle.getMovable().immutable = true;
            shuttle.setStation(route.get(step-1));
            System.out.println(route.get(step-1));
            //yolcular biner
            done = 2;
            Process.hasChange = true;
        }
        else if (done == 2)
        {
            startWaiting();
            Process.hasChange = true;
        }
        else if (done == 3)
        {
            waiting();
            Process.hasChange = true;
        }
        else if (done == 4)
        {
            hitTheRoad();
            Process.hasChange = true;
        }

    }

    public void startWaiting()
    {
        timeStart = Process.scene;
        done = 3;

    }

    public void waiting() {
        //System.out.println(timeStart + " " + timeEnd);
        timeEnd = Process.scene;

        if (timeEnd - timeStart > 10) {
            done = 4;
        }
    }

    public void hitTheRoad() {
        step++;
        updateVelocityDirection(route.get(step-1));
        shuttle.getMovable().immutable = false;
        done = 0;
    }




    public static void updateVelocityDirection(int destinationStation)
    {
        Station station = Station.getStation(destinationStation);

        shuttle.setTargetPosition(station.position);

        MovableBehavior movable = shuttle.getMovable();
        Position destinationPosition = shuttle.getTargetPosition();
        VelocityDirection velocityDirection = calculateVelocityDirection(movable,destinationPosition);

        movable.setVelocityDirection(velocityDirection);
    }


}