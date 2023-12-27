package state;

import shuttlemanager.RouteGenerator;
import shuttlemanager.ShuttleManager;

public class CalculatingPathState implements State{
    ShuttleManager shuttleManager;
    public CalculatingPathState(ShuttleManager shuttleManager) {
        this.shuttleManager = shuttleManager;
    }
    @Override
    public void handleState() {
        System.out.println("Shortest path is calculating (CalculatingPathState)");

        shuttleManager.setRoute(RouteGenerator.generateRoute(shuttleManager.getShuttle(), manager.Process.passengers));

        System.out.println("-------------------------");
        System.out.println("Shuttle is coming!");
        System.out.println("-------------------------");

        shuttleManager.getShuttle().notifyObservers();

        shuttleManager.setState(shuttleManager.getOnTheRoadState());
    }
}
