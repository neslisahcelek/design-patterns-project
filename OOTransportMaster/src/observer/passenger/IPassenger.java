package observer.passenger;

import display.drawable.DrawableBehavior;
import movement.movable.MovableBehavior;

public abstract class IPassenger {
    DrawableBehavior drawable;
    MovableBehavior movable;
    int station;
    int id;

    public IPassenger() {}

    public DrawableBehavior getDrawable() {return drawable;}
    public MovableBehavior getMovable() {return movable;}

    public int getStation() {
        return station;
    }

    public int getId() { return id; }
}
