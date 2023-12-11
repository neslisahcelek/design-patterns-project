package observer;

import display.drawable.DrawableBehavior;
import display.drawable.DrawableShuttle;
import motion.Position;
import motion.movable.IMovable;

import java.util.ArrayList;

public class Shuttle extends IShuttle implements Subject{ //Receiver
    private ArrayList<Observer> passengers;
    private IMovable movable;

    public Shuttle(IMovable movable, double positionI, double positionJ) {
        passengers = new ArrayList<>();
        super.station = 0;
        this.movable = movable;
        super.drawableBehavior = new DrawableShuttle(positionI, positionJ);
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

    public IMovable getMovable() {
        return movable;
    }

    public void setMovable(IMovable movable) {
        this.movable = movable;
    }
    
}
