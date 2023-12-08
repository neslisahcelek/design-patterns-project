package shuttlemanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Chart {
    private static Route[][] chart;

    public static Route[][] getChart() {
        int[][] distances =
                {
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
                        {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,18,0,18,30,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                        {Integer.MAX_VALUE,Integer.MAX_VALUE,28,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,18,0,63,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                        {Integer.MAX_VALUE,16,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,63,0,31,Integer.MAX_VALUE,Integer.MAX_VALUE},
                        {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,78,Integer.MAX_VALUE,Integer.MAX_VALUE,31,0,28,Integer.MAX_VALUE},
                        {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,86,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,28,0,66},
                        {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,66,0}
                };

        if (chart == null) {
            chart = calculateShortestPaths(distances);
        }
        return chart;
    }
    private static Route[][] calculateShortestPaths(int[][] distances) {
        int numNodes = distances.length;
        Route[][] shortestPaths = new Route[numNodes][numNodes];


        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (distances[i][j] != Integer.MAX_VALUE) {
                    shortestPaths[i][j] = new Route(distances[i][j], new ArrayList<>(Arrays.asList(i, j)));
                } else {
                    shortestPaths[i][j] = new Route(Integer.MAX_VALUE, new ArrayList<>());
                }
            }
        }

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (shortestPaths[i][k].distance != Integer.MAX_VALUE &&
                            shortestPaths[k][j].distance != Integer.MAX_VALUE &&
                            shortestPaths[i][k].distance + shortestPaths[k][j].distance < shortestPaths[i][j].distance) {
                        List<Integer> newPath = new ArrayList<>(shortestPaths[i][k].path);
                        newPath.addAll(shortestPaths[k][j].path.subList(1, shortestPaths[k][j].path.size()));
                        shortestPaths[i][j] = new Route(shortestPaths[i][k].distance + shortestPaths[k][j].distance, newPath);
                    }
                }
            }
        }

        return shortestPaths;
    }
}
