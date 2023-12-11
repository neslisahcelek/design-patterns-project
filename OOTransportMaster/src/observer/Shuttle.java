package observer;

import display.Drawable;
import motion.Movable;
import motion.Position;

import java.util.ArrayList;

public class Shuttle implements Subject{ //Receiver
    private ArrayList<Observer> passengers;
    private Movable movable;
    private Drawable drawable;

    public Shuttle(Movable movable,Drawable drawable) {
        passengers = new ArrayList<>();
        this.movable = movable;
        this.drawable = drawable;
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

    public Movable getMovable() {
        return movable;
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

    public Drawable getDrawable() {return drawable;}

    public void setDrawable(Drawable drawable) {this.drawable = drawable;}

    public void updatePosition(Position position)
    {
        movable.setPosition(position);
        drawable.setPosition(position);
    }
}
