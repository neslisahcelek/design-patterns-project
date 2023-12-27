package state;

import manager.Process;
import shuttlemanager.ShuttleManager;

public class WaitingState implements State{
    ShuttleManager shuttleManager;
    public WaitingState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        shuttleManager.setTimeEnd(Process.scene);

        if (shuttleManager.getTimeEnd() - shuttleManager.getTimeStart() > shuttleManager.getWaitingTime()) {
            shuttleManager.setState(shuttleManager.getLeavingTheStationState());
        }
    }
}
