package shuttlemanager;

import manager.Process;
import movement.Position;
import movement.VelocityDirection;
import movement.movable.MovableBehavior;
import observer.shuttle.Shuttle;
import state.*;
import java.util.List;
import static movement.Movement.calculateVelocityDirection;

public class ShuttleManager {
    State waitingForPassengersState;
    State calculatingPathState;
    State onTheRoadState;
    State onTheStationState;
    State passengerBoardingState;
    State waitingState;
    State leavingTheStationState;
    State onTheLastStationState;
    public State shuttleState;
    static Shuttle shuttle;
    static List<Integer> route;
    int step = 0;
    int timeStart;
    int timeEnd;
    int waitingTime = 5;

    public ShuttleManager(Shuttle shuttle) {
        ShuttleManager.shuttle = shuttle;
        waitingForPassengersState = new WaitingForPassengersState(this);
        calculatingPathState = new CalculatingPathState(this);
        onTheRoadState = new OnTheRoadState(this);
        onTheStationState = new OnTheStationState(this);
        passengerBoardingState = new PassengerBoardingState(this);
        waitingState = new WaitingState(this);
        leavingTheStationState = new LeavingTheStationState(this);
        onTheLastStationState = new OnTheLastStationState(this);

        shuttleState = waitingForPassengersState;
    }

    public void updateShuttleStates() {
        shuttleState.handleState();
        Process.hasChange = true;
    }

    public static void updateVelocityDirection(int destinationStation) {
        Station station = Chart.getStation(destinationStation);

        shuttle.setTargetPosition(station.position);

        MovableBehavior movable = shuttle.getMovable();
        Position destinationPosition = shuttle.getTargetPosition();
        VelocityDirection velocityDirection = calculateVelocityDirection(movable,destinationPosition);

        movable.setVelocityDirection(velocityDirection);
    }

    public State getCalculatingPathState() {
        return calculatingPathState;
    }

    public void setState(State state) {
        shuttleState = state;
    }

    public State getOnTheRoadState() {
        return onTheRoadState;
    }

    public State getOnTheStationState() {
        return onTheStationState;
    }

    public State getPassengersAreBoardingState() {
        return passengerBoardingState;
    }

    public State getWaitingState() {
        return waitingState;
    }

    public State getLeavingTheStationState() {
        return leavingTheStationState;
    }

    public State getOnTheLastStationState() {
        return onTheLastStationState;
    }

    public Shuttle getShuttle() {
        return shuttle;
    }

    public void setRoute(List<Integer> route) {
        ShuttleManager.route = route;
    }

    public int getStep() {
        return step;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void increaseStep() {
        this.step++;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

}