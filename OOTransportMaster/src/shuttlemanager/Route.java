package shuttlemanager;

import java.util.List;

class Route {

    int distance;
    //int[] path;
    List<Integer> path;

    public Route(int distance, List<Integer> path) {
        this.distance = distance;
        this.path = path;
    }
}
