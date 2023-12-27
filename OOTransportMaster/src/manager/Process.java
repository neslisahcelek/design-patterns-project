package manager;

import command.Command;
import command.PassengerCancelCommand;
import command.ShuttleApp;
import command.ShuttleCallCommand;
import display.Display;
import display.Image;
import display.InitialImage;
import inputmanager.Click;
import inputmanager.Input;
import movement.Movement;
import movement.Position;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import shuttlemanager.Chart;
import shuttlemanager.ShuttleManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Process {

    public static int scene;
    public static boolean hasChange = false;
    public static ArrayList<Passenger> passengers = new ArrayList<>();

    public static Shuttle shuttle = new Shuttle();
    public static final ShuttleApp shuttleApp = new ShuttleApp();


    static ShuttleManager shuttleManager;

    public static void display()
    {
        shuttleManager = new ShuttleManager(shuttle);
        Display display = new Display();

        Display.updateDrawableArrayList(); //check for new drawables

        InitialImage.getInitialImage().setNewImage(cloneArray());
        Display.updateImage(InitialImage.getInitialImage().getNewImage());
        display.createDisplay(Image.getImage().getMap());

        Input.mouseEvent(display.getFrame());

        Timer timer = new Timer(100, e -> {

            if(hasChange) {
                hasChange=false;

                shuttleManager.updateShuttleStates();
                Movement.updatePositions();

                Display.updateDrawableArrayList();

                InitialImage.getInitialImage().setNewImage(cloneArray());
                Display.updateImage(InitialImage.getInitialImage().getNewImage());
                display.updateDisplay(InitialImage.getInitialImage().getNewImage());

            }
            Process.checkClickRequests();
            scene++;
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    static void addPassenger(int positionI, int positionJ) {
        int maxPassenger = 5;

        if(isValid(positionI,positionJ) && passengers.size() < maxPassenger) {

            int station = findClosestStation(positionI,positionJ);
            Passenger passenger = new Passenger(station,positionI,positionJ);
            passengers.add(passenger);

            applyShuttleCallCommand(passenger, shuttle);
        }
        else if (shuttle.getPassengers().size() == maxPassenger) { //shuttle is ready to go
            shuttleManager.shuttleState = shuttleManager.getCalculatingPathState();
        }
    }

    static void removePassenger(int positionI, int positionJ) {
        if(!passengers.isEmpty() && isValid(positionI,positionJ)) {
            Passenger passenger = findClosestPassenger(positionI, positionJ);
            passengers.remove(passenger);

            applyPassengerCancelCommand(passenger, shuttle);
        }
    }

    public static void removePassenger(Passenger passenger) {
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

        for (int i = 0; i < Chart.getChart().getStationList().length ; i++) {

            double currentDistance = findDistance(Chart.getChart().getStation(i).getPosition().getI(),
                    Chart.getChart().getStation(i).getPosition().getJ(),positionI,positionJ);

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

    static boolean isValid(int positionI,int positionJ)
    {
        int range = 100;
        double currentDistance;

        for (int i = 0; i < Chart.getChart().getStationList().length ; i++) {

            currentDistance = findDistance(Chart.getChart().getStation(i).getPosition().getI(),
                    Chart.getChart().getStation(i).getPosition().getJ(),positionI,positionJ);

            if(range >= currentDistance) {return true;}
        }
        return false;
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

    public static Shuttle getShuttle() {
        return shuttle;
    }

    public static boolean isSamePositions(Position firstPosition, Position secondPosition)
    {
        return firstPosition.getI() == secondPosition.getI()
                && firstPosition.getJ() == secondPosition.getJ();
    }

    static Color[][] cloneArray(){
        return Arrays.stream(Image.getImage().getMap()).map(Color[]::clone).toArray(Color[][]::new);
    }

}


