package state;

import manager.Process;
import shuttlemanager.ShuttleManager;

public class PassengerBoardingState implements State{
    ShuttleManager shuttleManager;
    public PassengerBoardingState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        System.out.println("Passengers are boarding (PassengerBoardingState)");
        shuttleManager.setTimeStart(Process.scene);
        shuttleManager.setState(shuttleManager.getWaitingState());
    }
}
