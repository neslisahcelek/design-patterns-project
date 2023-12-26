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
    static List<Integer> route;
    static int step = 0;
    public static int shuttleState = 0;
    int timeStart;
    int timeEnd;
    public boolean isRouteStarted = false;
    static boolean isPathCalculated = false;

    public ShuttleManager(Shuttle shuttle) {
        this.shuttle = shuttle;
    }

    public static void startRoute(ArrayList<Passenger> passengers)
    {
        route = RouteGenerator.generateRoute(shuttle, passengers);

        System.out.println("-------------------------");
        System.out.println("Shuttle is coming!");
        System.out.println("-------------------------");

        shuttle.notifyObservers();
        isPathCalculated = true;
    }


    public void checkShuttleManager(ArrayList<Passenger> passengers){

        if (isRouteStarted && !isPathCalculated) {
            startRoute(passengers);
        }
        if (isPathCalculated){
            updateShuttleStates();
        }
    }

    public void updateShuttleStates()
    {
        boolean arrivedStation = shuttle.getMovable().position.getI() ==
                Station.getStation(route.get(step)).getPosition().getI()
                && shuttle.getMovable().position.getJ() ==
                        Station.getStation(route.get(step)).getPosition().getJ()
                && shuttleState == 0;

        if(arrivedStation)
        {
            shuttle.getMovable().immutable = true;
            shuttle.setStation(route.get(step));
            //System.out.println(route.get(step));
            if(anyPassenger()) {
                getOnPassengers();
                shuttleState = 2;
            }
            else {shuttleState = 4;}

            Process.hasChange = true;
        }
        else if (shuttleState == 2)
        {
            startWaiting();
            Process.hasChange = true;
        }
        else if (shuttleState == 3)
        {
            waiting();
            Process.hasChange = true;
        }
        else if (shuttleState == 4)
        {
            hitTheRoad();
            Process.hasChange = true;
        }

    }

    public void startWaiting()
    {
        timeStart = Process.scene;
        shuttleState = 3;

    }

    public void waiting() {
        //System.out.println(timeStart + " " + timeEnd);
        timeEnd = Process.scene;
        if (timeEnd - timeStart > 10) {
            shuttleState = 4;
        }
    }

    public void hitTheRoad() {
        step++;
        updateVelocityDirection(route.get(step));
        shuttle.getMovable().immutable = false;
        shuttleState = 0;
    }

    public static void getOnPassengers()
    {
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

    static boolean anyPassenger()
    {
        for (int i = 0; i < Process.passengers.size() ; i++) {

            if (route.get(step) == Process.passengers.get(i).getStation())
            {
                return true;
            }
        }
        return false;
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