package state;
import manager.Process;
import shuttlemanager.Chart;
import shuttlemanager.ShuttleManager;

import java.util.List;

public class OnTheRoadState implements State{
    ShuttleManager shuttleManager;
    boolean write = true;

    public OnTheRoadState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }

    @Override
    public void handleState() {
        int step = shuttleManager.getStep();
        List<Integer> route = shuttleManager.getRoute();

        if (write) {
            System.out.println("Shuttle is on the road (OnTheRoadState)");
            write = false;
        }

        if (Process.isSamePositions(shuttleManager.getShuttle().getMovable().position,
                Chart.getChart().getStation(route.get(step)).getPosition()))
        {
            if(step == route.size()-1) {
                shuttleManager.setState(shuttleManager.getOnTheLastStationState());
            }
            else {
                write = true;
                shuttleManager.setState(shuttleManager.getOnTheStationState());
            }
        }
    }
}
