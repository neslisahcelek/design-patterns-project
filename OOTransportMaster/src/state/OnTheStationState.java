package state;

import manager.Process;
import observer.shuttle.Shuttle;
import shuttlemanager.ShuttleManager;

import java.util.List;

public class OnTheStationState implements State{
    ShuttleManager shuttleManager;
    public OnTheStationState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        int step = shuttleManager.getStep();
        List<Integer> route = shuttleManager.getRoute();
        Shuttle shuttle = shuttleManager.getShuttle();

        shuttle.getMovable().immutable = true;
        shuttle.setStation(route.get(step));

        if(anyPassenger(route, step)) {
            shuttleManager.setState(shuttleManager.getPassengersAreBoardingState());
        }
        else {
            shuttleManager.setState(shuttleManager.getLeavingTheStationState());
        }
    }

    static boolean anyPassenger(List<Integer> route, int step) {
        for (int i = 0; i < Process.passengers.size() ; i++) {

            if (route.get(step) == Process.passengers.get(i).getStation())
            {
                return true;
            }
        }
        return false;
    }
}
