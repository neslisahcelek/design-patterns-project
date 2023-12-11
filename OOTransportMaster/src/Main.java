import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.DisplayManager;
import motion.Motion;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

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

        for (Observer p : shuttles.get(0).getPassengers()) {
            passengers.add((Passenger) p);
        }
        sm.findShortestRoute(passengers, 1, route);
         */
        DisplayManager.display(shuttles,passengers);

    }

    static void addShuttle(int positionI, int positionJ)
    {
        Shuttle shuttle = new Shuttle(positionI,positionJ);
        shuttles.add(shuttle);
        
        shuttle.getMovable().setVelocityDirection(Motion.calculateVelocityDirection(shuttle.getMovable(),5,5));
        shuttle.getMovable().setSpeed(5);
        System.out.println( shuttle.getMovable().getPosition().getI() + " " +  shuttle.getMovable().getPosition().getJ());
        shuttle.updatePosition(Motion.calculateNextPositionWithDestination( shuttle.getMovable(),20,20));

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