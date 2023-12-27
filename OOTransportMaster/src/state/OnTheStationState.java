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
            getOnPassengers(route, step);
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

    public static void getOnPassengers(List<Integer> route, int step) {
        int i = 0;
        while (i < Process.passengers.size())
        {
            if (route.get(step) == Process.passengers.get(i).getStation())
            {
                Process.removePassenger(Process.passengers.get(i));
            }
            else {i++;}
        }
    }
}
