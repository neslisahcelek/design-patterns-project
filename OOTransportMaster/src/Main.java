import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Drawable;
import display.drawable.DrawablePassenger;
import display.drawable.DrawableShuttleBehavior;
import motion.Motion;
import motion.Movable;
import motion.movable.MovableShuttle;
import observer.Observer;
import observer.Passenger;
import observer.Shuttle;
import shuttlemanager.Chart;
import shuttlemanager.Route;
import shuttlemanager.ShuttleManager;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static ArrayList<Shuttle> shuttles = new ArrayList<>();
    private static ShuttleApp shuttleApp = new ShuttleApp();

    public static void main(String[] args) {
        addShuttle(1,1);
        addPassenger(2,3);
        addPassenger(2,3);
        addPassenger(2,3);
        addPassenger(2,3);

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
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (Observer p : shuttles.get(0).getPassengers()) {
            passengers.add((Passenger) p);
        }
        sm.findShortestRoute(passengers, 1, route);

         */

    }

    static void addShuttle(int positionI, int positionJ)
    {
        Shuttle shuttle = new Shuttle(new MovableShuttle(positionI,positionJ),new DrawableShuttleBehavior(positionI,positionJ));
        shuttles.add(shuttle);
        
        //shuttle.getMovable().setVelocityDirection(Motion.calculateVelocityDirection(shuttle.getMovable(),5,5));
        shuttle.getMovable().setSpeed(5);
        System.out.println( shuttle.getMovable().getPosition().getI() + " " +  shuttle.getMovable().getPosition().getJ());
        //shuttle.updatePosition(Motion.calculateNextPositionWithDestination( shuttle.getMovable(),20,20));

    }

    static void addPassenger(int positionI, int positionJ)
    {
        Passenger passenger = new Passenger(1,positionI,positionJ);
        passengers.add(passenger);
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

}