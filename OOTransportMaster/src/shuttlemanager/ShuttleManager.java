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
    int step = 0;
    public static int shuttleState = 0;
    int timeStart;
    int timeEnd;
    static Route[][] distances = Chart.getChart();
    static public boolean isRouteStarted = false;
    static boolean isPathCalculated = false;

    public ShuttleManager(Shuttle shuttle) {
        this.shuttle = shuttle;
    }

    public static void startRoute(ArrayList<Passenger> passengers)
    {
        findShortestRoute(shuttle, passengers);
        shuttle.notifyObservers();
        isPathCalculated = true;
        System.out.println("hesaplandı");
    }

    public static void findShortestRoute(Shuttle shuttle, List<Passenger> passengers) {
        int currentStation = shuttle.getStation();
        List<Integer> passengerStations = new ArrayList<>();
        passengers.forEach(p -> passengerStations.add(p.getStation()));

        int lastStation = 16;
        passengerStations.add(lastStation);

        List<Integer> shortestRoute = null;
        int minDistance = Integer.MAX_VALUE;

        List<List<Integer>> allRoutes = generateAllRoutes(currentStation, passengerStations);
        for (List<Integer> route : allRoutes) {
            if (route.get(0) == 1 && route.get(route.size() - 1) == 16) {
                int totalDistance = calculateTotalDistance(route);
                if (totalDistance < minDistance) {
                    minDistance = totalDistance;
                    shortestRoute = route;
                }
            }
        }

        addRemainingStations(shortestRoute);
    }

    public static void addRemainingStations(List<Integer> shortestRoute) {
        System.out.println("Shortest Route: " + shortestRoute);
        List<Integer> newRoute = null;

        for (int i = 0; i < shortestRoute.size() - 1; i++) {
            int fromStation = shortestRoute.get(i) - 1;
            int toStation = shortestRoute.get(i + 1) - 1;

            if (newRoute == null) {
                newRoute = distances[fromStation][toStation].path;
            } else {
                newRoute.remove(newRoute.size()-1);
                newRoute.addAll(distances[fromStation][toStation].path);
            }
        }

        for (int i = 0; i < newRoute.size(); i++) {
            newRoute.set(i, newRoute.get(i) + 1) ;
        }

        System.out.println("Shortest Route With Remaining Stations: " + newRoute);
        route = newRoute;
    }

    private static List<List<Integer>> generateAllRoutes(int currentStation, List<Integer> passengerStations) {
        List<List<Integer>> allRoutes = new ArrayList<>();
        generateRoutes(new ArrayList<>(), currentStation, passengerStations, allRoutes);
        return allRoutes;
    }

    private static void generateRoutes(List<Integer> currentRoute, int currentStation, List<Integer> remainingStations, List<List<Integer>> allRoutes) {
        if (remainingStations.isEmpty()) {
            List<Integer> route = new ArrayList<>(currentRoute);
            route.add(currentStation);
            allRoutes.add(route);
            return;
        }

        for (int i = 0; i < remainingStations.size(); i++) {
            int nextStation = remainingStations.get(i);

            List<Integer> updatedRoute = new ArrayList<>(currentRoute);
            updatedRoute.add(currentStation);

            List<Integer> updatedRemainingStations = new ArrayList<>(remainingStations);
            updatedRemainingStations.remove(i);

            generateRoutes(updatedRoute, nextStation, updatedRemainingStations, allRoutes);
        }
    }

    private static int calculateTotalDistance(List<Integer> route) {
        int totalDistance = 0;
        for (int i = 0; i < route.size() - 2; i++) {
            int fromStation = route.get(i);
            int toStation = route.get(i + 1);
            totalDistance += distances[fromStation][toStation].getDistance();
        }
        return totalDistance;
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
            System.out.println(route.get(step));
            //yolcular biner
            shuttleState = 2;
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