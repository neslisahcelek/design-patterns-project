package shuttlemanager;

import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

import java.util.ArrayList;
import java.util.List;

public class RouteGenerator {
    static List<Integer> route;

    public static List<Integer> generateRoute(Shuttle shuttle, List<Passenger> passengers) {
        int currentStation = shuttle.getStation();
        List<Integer> passengerStations = addStationsToList(passengers);

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

        return route;
    }

    private static List<Integer> addStationsToList(List<Passenger> passengers) {
        List<Integer> passengerStations = new ArrayList<>();

        for (Passenger p : passengers) {
            int station = p.getStation();
            if (!passengerStations.contains(station)) {
                passengerStations.add(station);
            }
        }

        int lastStation = 16;
        passengerStations.add(lastStation);

        return passengerStations;
    }

    public static void addRemainingStations(List<Integer> shortestRoute) {
        System.out.println("Shortest Route: " + shortestRoute);
        List<Integer> newRoute = null;

        for (int i = 0; i < shortestRoute.size() - 1; i++) {
            int fromStation = shortestRoute.get(i) - 1;
            int toStation = shortestRoute.get(i + 1) - 1;

            if (newRoute == null) {
                newRoute = Chart.getPath(fromStation,toStation);
            } else {
                newRoute.remove(newRoute.size()-1);
                newRoute.addAll(Chart.getPath(fromStation,toStation));
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
            totalDistance += Chart.getDistance(fromStation,toStation);
        }
        return totalDistance;
    }
}
