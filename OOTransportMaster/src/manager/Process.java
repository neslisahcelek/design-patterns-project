package manager;

import command.Command;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Display;
import display.Image;
import movement.movable.MovableBehavior;
import inputmanager.Click;
import inputmanager.Input;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import shuttlemanager.Station;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Process {

    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Shuttle> shuttles = new ArrayList<>();
    private static ShuttleApp shuttleApp = new ShuttleApp();
    private static Color[][] newImage = new Color[Image.getMap().length][Image.getMap()[0].length];

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

        display.createDisplay(Image.getMap());

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
        // Color[][] newImage = Image.getMap();
        newImage = Arrays.stream(Image.getMap()).map(Color[]::clone).toArray(Color[][]::new);

        Display.updateDrawableArrayList();
        Display.updateImage(newImage);
        return newImage;
    }

    static void addPassenger(int positionI, int positionJ)
    {
        if(isValid(positionI,positionJ)) {
            int station = findClosestStation(positionI,positionJ);
            Passenger passenger = new Passenger(station,positionI,positionJ);
            passengers.add(passenger);
            System.out.println("passanger eklendi "+ passengers.size());}
    }
    static void removePassenger(int positionI, int positionJ)
    {
        if(passengers.size()>0) {
            Passenger passenger = findClosestPassenger(positionI, positionJ);
            passengers.remove(passenger);
            System.out.println("passanger kaldırıldı " + passengers.size());
        }
    }


    private static Passenger findClosestPassenger(int positionI,int positionJ) {

        double minDistance = Double.MAX_VALUE;
        Passenger closestPassenger= null;

        for (int i = 0; i < passengers.size() ; i++) {


            double currentDistance = findDistance(
                    (int) passengers.get(i).getMovable().getPosition().getI(),
                    (int) passengers.get(i).getMovable().getPosition().getJ(),
                    positionI, positionJ);


            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestPassenger = passengers.get(i); }
        }

        System.out.println("ben en yakın passangerdım ölüyorum" + closestPassenger.getMovable().getPosition().getJ());
        return closestPassenger;
    }



    private static int findClosestStation(int positionI,int positionJ) {
        double minDistance = Double.MAX_VALUE;
        int closestStation = 0;

        for (int i = 0; i < Station.stations.length ; i++) {

            double currentDistance = findDistance(Station.stations[i].getLocationI(),
                    Station.stations[i].getLocationJ(),positionI,positionJ);

            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestStation = i; }
        }

        return closestStation;
    }

    private static double findDistance(int arrayPositionI, int arrayPositionJ, int positionI,int positionJ)
    {
            return Math.sqrt(Math.pow(arrayPositionI - positionI, 2) +
                            Math.pow(arrayPositionJ - positionJ, 2));
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

            if(click.isRightClick())
            {Process.removePassenger(click.getI(), click.getJ());}
            else{Process.addPassenger(click.getI(), click.getJ());
            }
           
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

    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    //bunlar sadece dönse ama asıl array değiştirilebiliyor input classına bak

    public static ArrayList<Shuttle> getShuttles() {
        return shuttles;
    }


}


