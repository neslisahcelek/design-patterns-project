package observer.shuttle;

import display.drawable.DrawableShuttle;
import manager.Process;
import movement.Position;
import movement.movable.MovableShuttle;
import observer.Observer;
import observer.Subject;

import java.util.ArrayList;

public class Shuttle extends IShuttle implements Subject { //Receiver
    private ArrayList<Observer> passengers;

    public Shuttle(double positionI, double positionJ) {
        passengers = new ArrayList<>();
        super.station = 1;
        super.movable = new MovableShuttle(positionI, positionJ);
        super.drawable = new DrawableShuttle(positionI, positionJ);
        super.targetPosition = new Position(5,5);
    }

    @Override
    public void registerObserver(Observer passenger) {
        passengers.add(passenger);
    }

    @Override
    public void removeObserver(Observer passenger) {
        passengers.remove(passenger);
    }

    @Override
    public void notifyObservers() { //Shuttle starts moving
        passengers.forEach(Observer::update);
    }
    public ArrayList<Observer> getPassengers() {
        return passengers;
    }
}
