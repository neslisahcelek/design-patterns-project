package manager;

import command.Command;
import command.PassengerCancelCommand;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Display;
import display.Image;
import inputmanager.Click;
import inputmanager.Input;
import movement.Movement;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import shuttlemanager.ShuttleManager;
import shuttlemanager.Station;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Process {

    public static int scene;
    public static boolean hasChange = false;
    public static ArrayList<Passenger> passengers = new ArrayList<>();
    public static ArrayList<Shuttle> shuttles = new ArrayList<>();
    public static final ShuttleApp shuttleApp = new ShuttleApp();
    private static Color[][] newImage = new Color[Image.getMap().length][Image.getMap()[0].length];
    static ShuttleManager sm;

    public static void display()
    {
        //Image.getImages();
        addShuttle(Station.getStation(1).getPosition().getI(),Station.getStation(1).getPosition().getJ());

        Display display = new Display();
        display.createDisplay(Image.getMap());

        //Display.updateDrawableArrayList();
        //newImage = Arrays.stream(Image.getMap()).map(Color[]::clone).toArray(Color[][]::new);
       // Display.updateImage(newImage);
        //display.updateDisplay(newImage);

        Input.mouseEvent(display.getFrame());
        shuttles.get(0).getMovable().setImmutable(false);
        //ShuttleManager sm = new ShuttleManager(shuttles.get(0));
        sm = new ShuttleManager(shuttles.get(0));

        Timer timer = new Timer(100, e -> {

            if(hasChange) {
                hasChange=false;

                sm.checkShuttleManager(passengers);
                Movement.updatePositions();

                Display.updateDrawableArrayList();
                newImage = Arrays.stream(Image.getMap()).map(Color[]::clone).toArray(Color[][]::new);
                Display.updateImage(newImage);
                display.updateDisplay(newImage);
            }
            Process.checkClickRequests();
            scene++;
            System.out.println("hangi aşamada " + ShuttleManager.shuttleState + " frame " + scene + " duruyor mu " + shuttles.get(0).getMovable().immutable);


        });

        timer.setInitialDelay(0);
        timer.start();
    }



    static void addPassenger(int positionI, int positionJ)
    {
        int maxPassenger = 10;

        if(isValid(positionI,positionJ) && passengers.size() < maxPassenger) {
            int station = findClosestStation(positionI,positionJ);
            Passenger passenger = new Passenger(station,positionI,positionJ);
            passengers.add(passenger);

            applyShuttleCallCommand(passenger, shuttles.get(0));
        }
        else if (passengers.size() == maxPassenger) {
            sm.isRouteStarted = true;
        }
    }

    static void removePassenger(int positionI, int positionJ)
    {
        if(!passengers.isEmpty() && isValid(positionI,positionJ)) {
            Passenger passenger = findClosestPassenger(positionI, positionJ);
            passengers.remove(passenger);

            applyPassengerCancelCommand(passenger, shuttles.get(0));
        }
    }

    public static void removePassenger(Passenger passenger)
    {
        if(!passengers.isEmpty())
        {passengers.remove(passenger);}

    }

    private static void applyShuttleCallCommand(Passenger passenger, Shuttle shuttle) {
        Command shuttleCallCommand = new ShuttleCallCommand(shuttle, passenger);
        shuttleApp.setCommand(shuttleCallCommand);
        shuttleApp.takeCommandCall(shuttleCallCommand);
    }

    private static void applyPassengerCancelCommand(Passenger passenger, Shuttle shuttle) {
        Command passengerCancelCommand = new PassengerCancelCommand(shuttle, passenger);
        shuttleApp.setCommand(passengerCancelCommand);
        shuttleApp.takeCommandCall(passengerCancelCommand);
    }

    static void start() {
        ShuttleManager shuttleManager = new ShuttleManager(shuttles.get(0));
        if (passengers.size() == 10) {
           // shuttleManager.startRoute();
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

        return closestPassenger;
    }

    private static int findClosestStation(int positionI,int positionJ) {
        double minDistance = Double.MAX_VALUE;
        int closestStation = 0;

        for (int i = 0; i < Station.stations.length ; i++) {

            double currentDistance = findDistance(Station.getStation(i).getPosition().getI(),
                    Station.getStation(i).getPosition().getJ(),positionI,positionJ);

            if(currentDistance < minDistance )
            { minDistance = currentDistance; closestStation = i; }
        }

        return closestStation;
    }

    private static double findDistance(double arrayPositionI, double arrayPositionJ, double positionI,double positionJ)
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
        double currentDistance;

        for (int i = 0; i < Station.stations.length ; i++) {

            currentDistance = findDistance(Station.getStation(i).getPosition().getI(),
                    Station.getStation(i).getPosition().getJ(),positionI,positionJ);

            if(range >= currentDistance) {return true;}
        }
        return false;
    }

    static void addShuttle(double positionI, double positionJ)
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
            hasChange = true;
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


