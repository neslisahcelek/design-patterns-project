package state;

import shuttlemanager.ShuttleManager;

public class OnTheLastStationState implements State{
    ShuttleManager shuttleManager;
    boolean write = true;
    public OnTheLastStationState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        if (write) {
            System.out.println("Please leave the shuttle (OnTheLastStationState)");
            write = false;
        }
    }
}
