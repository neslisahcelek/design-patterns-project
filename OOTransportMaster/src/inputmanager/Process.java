package inputmanager;

import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Display;
import observer.Passenger;
import observer.Shuttle;
import shuttlemanager.Station;

import java.util.ArrayList;

public class Process {

    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Shuttle> shuttles = new ArrayList<>();
    private static ShuttleApp shuttleApp = new ShuttleApp();

    public static void mainly()
    {
        //Process.addShuttle(1,1);
        Process.addPassenger(2,3);
        Process.addPassenger(2,3);
        Process.addPassenger(2,3);
        Process.addPassenger(2,3);

        for (Passenger p : passengers) {
            callCommand(shuttles.get(0), p);
        }

        /*
        int i = 3;
        for (Passenger p : passengers) {
            p.setLocation(++i);
        }

        // start journey
        ShuttleManager sm = new ShuttleManager(shuttles.get(0));
        Route[][] route = Chart.getChart();

        for (Observer p : shuttles.get(0).getPassengers()) {
            passengers.add((Passenger) p);
        }
        sm.findShortestRoute(passengers, 1, route);
         */

        Display.display();
    }

    static void addPassenger(int positionI, int positionJ)
    {
        if(isValid(positionI,positionJ))
        {Passenger passenger = new Passenger(1,positionI,positionJ);
            passengers.add(passenger);}
    }

    static void callCommand(Shuttle shuttle, Passenger passenger) {
        Command shuttleCallCommand = new ShuttleCallCommand(shuttle, passenger);
        setCommand(shuttleCallCommand);
    }

    static void setCommand(Command command) {
        shuttleApp.setCommand(command);
        takeCommandCall(command);
    }

    static void takeCommandCall(Command command) {
        shuttleApp.takeCommandCall(command);
    }

    static boolean isValid(int positionI,int positionJ)
    {
        int range = 5;

        for (int i = 0; i < Station.stations.length ; i++) {
            if(range >= Math.sqrt(
                    Math.pow(Station.stations[i].getLocationI() - positionI, 2) +
                            Math.pow(Station.stations[i].getLocationJ() - positionJ, 2)))
            {return true;}
        }
        return false;
    }

    /*
    static void addShuttle(int positionI, int positionJ)
    {
        Shuttle shuttle = new Shuttle(new MovableShuttle(positionI,positionJ),new DrawableShuttleBehavior(positionI,positionJ));
        shuttles.add(shuttle);

        //shuttle.getMovable().setVelocityDirection(Motion.calculateVelocityDirection(shuttle.getMovable(),5,5));
        shuttle.getMovable().setSpeed(5);
        System.out.println( shuttle.getMovable().getPosition().getI() + " " +  shuttle.getMovable().getPosition().getJ());
        //shuttle.updatePosition(Motion.calculateNextPositionWithDestination( shuttle.getMovable(),20,20));

    }
*/
    public static void applyAddRequests()
    {
        for (Click click : Input.getClicks()) {

            Process.addPassenger(click.i,click.j);

        }
    }
}


