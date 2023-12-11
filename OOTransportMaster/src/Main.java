import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import observer.Observer;
import observer.Passenger;
import observer.Shuttle;
import shuttlemanager.Chart;
import shuttlemanager.Route;
import shuttlemanager.ShuttleManager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ShuttleApp shuttleApp = new ShuttleApp();

        Shuttle shuttle = new Shuttle();
        Passenger passenger = new Passenger();
        Passenger passenger2 = new Passenger();
        Passenger passenger3 = new Passenger();

        Command shuttleCallCommand = new ShuttleCallCommand(shuttle, passenger);
        Command shuttleCallCommand2 = new ShuttleCallCommand(shuttle, passenger2);
        Command shuttleCallCommand3 = new ShuttleCallCommand(shuttle, passenger3);

        shuttleApp.setCommand(shuttleCallCommand);
        shuttleApp.setCommand(shuttleCallCommand2);
        shuttleApp.setCommand(shuttleCallCommand3);

        shuttleApp.takeCommandCall(shuttleCallCommand);
        shuttleApp.takeCommandCall(shuttleCallCommand2);
        shuttleApp.takeCommandCall(shuttleCallCommand3);

        passenger.setLocation(15);
        passenger2.setLocation(5);
        passenger3.setLocation(8);

        // start journey
        ShuttleManager sm = new ShuttleManager(shuttle);
        Route[][] route = Chart.getChart();
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (Observer p : shuttle.getPassengers()) {
            passengers.add((Passenger) p);
        }
        sm.findShortestRoute(passengers, 1, route);

    }
}