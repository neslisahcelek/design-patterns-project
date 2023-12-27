package state;
import manager.Process;
import shuttlemanager.Chart;
import shuttlemanager.ShuttleManager;

import java.util.List;

public class OnTheRoadState implements State{
    ShuttleManager shuttleManager;
    public OnTheRoadState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }

    @Override
    public void handleState() {
        int step = shuttleManager.getStep();
        List<Integer> route = shuttleManager.getRoute();

        if (Process.samePositions(shuttleManager.getShuttle().getMovable().position,
                Chart.getStation(route.get(step)).getPosition()))
        {
            if(step == route.size()-1) {
                shuttleManager.setState(shuttleManager.getOnTheLastStationState());
            }
            else {
                shuttleManager.setState(shuttleManager.getOnTheStationState());
            }
        }
    }
}
