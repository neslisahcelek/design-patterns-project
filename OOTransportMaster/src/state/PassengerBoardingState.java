package state;

import manager.Process;
import shuttlemanager.ShuttleManager;

import java.util.List;

public class PassengerBoardingState implements State{
    ShuttleManager shuttleManager;
    public PassengerBoardingState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        int step = shuttleManager.getStep();
        List<Integer> route = shuttleManager.getRoute();
        System.out.println("Passengers are boarding (PassengerBoardingState)");
        System.out.println("Shuttle is on the road (OnTheRoadState)");
        getOnPassengers(route, step);

        shuttleManager.setTimeStart(Process.scene);
        shuttleManager.setState(shuttleManager.getWaitingState());
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
