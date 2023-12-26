package shuttlemanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chart { //bozuk
    private static Route[][] chart;
    private static Station[] station;
    public static Route[][] getChartList() {

        if (chart == null) {
            chart = calculateShortestPaths(getDistances());
        }
        return chart;
    }
    public static Route getChart(int i, int j) {
        return getChartList()[i][j];
    }
    private static int[][] getDistances()
    {
        return new int[][]{
                {0,22,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {22,0,63,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,16,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,63,0,37,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,28,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,37,0,52,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,52,0,20,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,30,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,20,0,36,Integer.MAX_VALUE,Integer.MAX_VALUE,32,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,36,0,38,28,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,38,0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,28,Integer.MAX_VALUE,0,42,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,86,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,32,Integer.MAX_VALUE,Integer.MAX_VALUE,42,0,18,Integer.MAX_VALUE,Integer.MAX_VALUE,78,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,18,0,18,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,28,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,18,0,63,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,16,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,63,0,31,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,78,Integer.MAX_VALUE,Integer.MAX_VALUE,31,0,28,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,86,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,28,0,66},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,66,0}
        };
    }
    public static Station[] getStationList()
    {
        Station[] stations = {
                new Station(106, 856),
                new Station(130, 763),
                new Station(65, 503),
                new Station(64, 343),
                new Station(285, 342),
                new Station(373, 341),
                new Station(521, 343),
                new Station(550, 177),
                new Station(505, 464),
                new Station(322, 465),
                new Station(262, 505),
                new Station(187, 505),
                new Station(192, 770),
                new Station(322, 796),
                new Station(435, 818),
                new Station(437, 1100),
        };
        if (station == null) {
            station = stations;
        }
        return station;
    }
    public static Station getStation(int stationName)
    {
        if(stationName >= 1 && stationName <= 16)
        {return getStationList()[stationName-1];}
        else return new Station(0,0);
    }

    public static int getDistance(int i, int j) {
        return getChartList()[i][j].getDistance();
    }
    public static List<Integer> getPath(int i, int j) {
        return getChartList()[i][j].getPath();
    }

    private static Route[][] calculateShortestPaths(int[][] distances) {
        int numNodes = distances.length;
        Route[][] shortestPaths = new Route[numNodes][numNodes];


        // Başlangıç değerleri atama
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (distances[i][j] != Integer.MAX_VALUE) {
                    shortestPaths[i][j] = new Route(distances[i][j], new ArrayList<>(Arrays.asList(i, j)));
                } else {
                    shortestPaths[i][j] = new Route(Integer.MAX_VALUE, new ArrayList<>());
                }
            }
        }

        // Floyd-Warshall algoritması
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (shortestPaths[i][k].getDistance() != Integer.MAX_VALUE &&
                            shortestPaths[k][j].getDistance() != Integer.MAX_VALUE &&
                            shortestPaths[i][k].getDistance() + shortestPaths[k][j].getDistance() < shortestPaths[i][j].getDistance()) {
                        List<Integer> newPath = new ArrayList<>(shortestPaths[i][k].getPath());
                        newPath.addAll(shortestPaths[k][j].getPath().subList(1, shortestPaths[k][j].getPath().size()));
                        shortestPaths[i][j] = new Route(shortestPaths[i][k].getDistance() + shortestPaths[k][j].getDistance(), newPath);
                    }
                }
            }
        }

        return shortestPaths;
    }
}

