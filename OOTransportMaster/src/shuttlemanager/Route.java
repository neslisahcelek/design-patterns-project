package shuttlemanager;

import java.util.List;

public class Route {

    private int distance;
    private List<Integer> path;

    public Route(int distance, List<Integer> path) {
        this.distance = distance;
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public List<Integer> getPath() {
        return path;
    }
}
