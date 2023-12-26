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
    final static int waiting_for_passengers = 0;
    final static int calculating_path_ = 1;
    final static int on_the_road = 2;
    final static int on_the_station = 3;
    final static int passengers_are_boarding = 4;
    final static int waiting = 5;
    final static int leaving_the_station = 6;
    final static int on_the_last_station = 7;

    public static int shuttleState = waiting_for_passengers;

    static int step = 0;
    int timeStart;
    int timeEnd;
    int waitingTime = 5;

    static Shuttle shuttle;
    static List<Integer> route;

    public ShuttleManager(Shuttle shuttle) {
        ShuttleManager.shuttle = shuttle;
    }

    public void updateShuttleStates() {

        if(shuttleState == calculating_path_)
        {
            route = RouteGenerator.generateRoute(shuttle, manager.Process.passengers);

            System.out.println("-------------------------");
            System.out.println("Shuttle is coming!");
            System.out.println("-------------------------");

            shuttle.notifyObservers();
            shuttleState = on_the_road;
        }
        else if(shuttleState == on_the_road) {

            if (Process.samePositions(shuttle.getMovable().position,
                    Chart.getStation(route.get(step)).getPosition()))
            {
                if(step == route.size()-1)
                {shuttleState = on_the_last_station;}
                else {shuttleState = on_the_station;}
            }
        }
        else if(shuttleState == on_the_station) {

            shuttle.getMovable().immutable = true;
            shuttle.setStation(route.get(step));

            if(anyPassenger()) {
                getOnPassengers();
                shuttleState = passengers_are_boarding;
            }
            else {shuttleState = leaving_the_station;}
        }
        else if (shuttleState == passengers_are_boarding) {

            timeStart = Process.scene;
            shuttleState = waiting;
        }
        else if (shuttleState == waiting) {

            timeEnd = Process.scene;
            if (timeEnd - timeStart > waitingTime) {
                shuttleState = leaving_the_station;
            }
        }
        else if (shuttleState == leaving_the_station) {

            step++;
            updateVelocityDirection(route.get(step));
            shuttle.getMovable().immutable = false;
            shuttleState = on_the_road;
        }
        else if(shuttleState == on_the_last_station) {

            System.out.println("yolcular");
        }
        Process.hasChange = true;
    }

    public static void getOnPassengers() {
        int i = 0;
        while (i < Process.passengers.size())
        {
            if (route.get(step) == Process.passengers.get(i).getStation())
            {
                Process.removePassenger(Process.passengers.get(i));
            }
            else {i++;}
        }
    }

    static boolean anyPassenger() {
        for (int i = 0; i < Process.passengers.size() ; i++) {

            if (route.get(step) == Process.passengers.get(i).getStation())
            {
                return true;
            }
        }
        return false;
    }

    public static void updateVelocityDirection(int destinationStation) {
        Station station = Chart.getStation(destinationStation);

        shuttle.setTargetPosition(station.position);

        MovableBehavior movable = shuttle.getMovable();
        Position destinationPosition = shuttle.getTargetPosition();
        VelocityDirection velocityDirection = calculateVelocityDirection(movable,destinationPosition);

        movable.setVelocityDirection(velocityDirection);
    }


}