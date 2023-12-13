package inputmanager;

import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Display;
import display.Image;
import motion.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import shuttlemanager.Station;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Process {

    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Shuttle> shuttles = new ArrayList<>();
    private static ShuttleApp shuttleApp = new ShuttleApp();

    public static void mainly()
    {

        //Process.addShuttle(1,1);
        addPassenger(223,775);

        display();
        /*
        for (Passenger p : passengers) {
            callCommand(shuttles.get(0), p);
        }
*/
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

    }

    public static void display()
    {
        //Image.getImages();
        Display display = new Display();

        display.createDisplay( Image.getMap());

        Input.mouseEvent(display.getFrame());



        Timer timer = new Timer(100, e -> {

            display.updateDisplay(getCurrentDisplay());
            Process.checkClickRequests();

        });
        timer.setInitialDelay(0);
        timer.start();
    }

    static Color[][] getCurrentDisplay()
    {
        return Display.updateImage(Process.shuttles,Process.passengers);
    }

    static void addPassenger(int positionI, int positionJ)
    {
        if(isValid(positionI,positionJ)) {
            int station = findClosestStation(positionI,positionJ);
            Passenger passenger = new Passenger(station,positionI,positionJ);
            passengers.add(passenger);}
    }
    static void removePassenger(int positionI, int positionJ)
    {
        if(passengers.size()>0)
        {
        Passenger passenger = findClosestPassanger(positionI,positionJ);
        passengers.remove(passenger);}
    }


    private static Passenger findClosestPassanger(int positionI,int positionJ) {



        double minDistance = Double.MAX_VALUE;
        Passenger closestPassenger= null;

        //movable passanger aç, movable dan çek koordinatı
        //passengers.get(i).getDrawable().getPosition().getI()

        for (int i = 0; i < passengers.size() ; i++) {

            double currentDistance = Math.sqrt(
                    Math.pow(passengers.get(i).getDrawable().getPosition().getI() - positionI, 2) +
                            Math.pow(passengers.get(i).getDrawable().getPosition().getJ() - positionJ, 2));

            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestPassenger = passengers.get(i); }
        }

        System.out.println("ben en yakın passangerdım ölüyorum" + closestPassenger.getDrawable().getPosition().getJ());
        return closestPassenger;
    }






    private static int findClosestStation(int positionI,int positionJ) {
        double minDistance = Double.MAX_VALUE;
        int closestStation = 0;

        for (int i = 0; i < Station.stations.length ; i++) {

            double currentDistance = Math.sqrt(
                    Math.pow(Station.stations[i].getLocationI() - positionI, 2) +
                            Math.pow(Station.stations[i].getLocationJ() - positionJ, 2));

            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestStation = i; }
        }

        return closestStation;
    }

    private static MovableBehavior findClosestMovable(
            ArrayList<MovableBehavior> movables, int positionI, int positionJ)
    {
        double minDistance = Double.MAX_VALUE;
        MovableBehavior closestMovable = null;

        for (int i = 0; i < movables.size() ; i++) {

            double currentDistance = Math.sqrt(
                    Math.pow(movables.get(i).getPosition().getI() - positionI, 2) +
                            Math.pow(movables.get(i).getPosition().getJ() - positionJ, 2));

            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestMovable = movables.get(i) ; }
        }

        return closestMovable;
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
        int range = 100;

        for (int i = 0; i < Station.stations.length ; i++) {
            if(range >= Math.sqrt(
                    Math.pow(Station.stations[i].getLocationI() - positionI, 2) +
                            Math.pow(Station.stations[i].getLocationJ() - positionJ, 2)))
            {return true;}
        }
        return false;
    }


    static void addShuttle(int positionI, int positionJ)
    {
        Shuttle shuttle = new Shuttle(positionI,positionJ);
        shuttles.add(shuttle);
    }

    public static void applyRequests()
    {
        while(Input.getClicks().size()>0)
        {
            Click click = Input.getClicks().get(0);

            if(click.rightClick)
            {Process.removePassenger(click.i,click.j);}
            else{Process.addPassenger(click.i,click.j);}

            Input.getClicks().remove(click);
        }
    }

    public static void checkClickRequests()
    {
        if(0 != Input.getClicks().size())
        {
            applyRequests();
        }
    }
}


