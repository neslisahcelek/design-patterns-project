package state;

import shuttlemanager.ShuttleManager;

public class LeavingTheStationState implements State{
    ShuttleManager shuttleManager;
    public LeavingTheStationState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        shuttleManager.increaseStep();
        shuttleManager.updateVelocityDirection(shuttleManager.getRoute().get(shuttleManager.getStep()));
        shuttleManager.getShuttle().getMovable().setImmutable(false);
        shuttleManager.setState(shuttleManager.getOnTheRoadState());
    }
}
