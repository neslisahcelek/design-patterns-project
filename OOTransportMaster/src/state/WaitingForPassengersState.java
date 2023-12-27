package state;

import shuttlemanager.ShuttleManager;

public class WaitingForPassengersState implements State{
    ShuttleManager shuttleManager;
    boolean write = true;
    public WaitingForPassengersState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        if (write) {
            System.out.println("Shuttle is waiting for passengers (WaitingForPassengersState)");
            write = false;
        }
    }
}
