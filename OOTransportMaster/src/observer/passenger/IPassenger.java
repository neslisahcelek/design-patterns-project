package observer.passenger;

import display.drawable.DrawableBehavior;
import manager.Process;
import movement.Position;
import movement.movable.MovableBehavior;

public abstract class IPassenger {
    DrawableBehavior drawable;
    MovableBehavior movable;
    int station;
    int id;

    public IPassenger() {}

    public DrawableBehavior getDrawable() {return drawable;}
    public MovableBehavior getMovable() {return movable;}

    public void updatePosition(Position position)
    {
        movable.setPosition(position);
        drawable.setPosition(position);
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public int getId() { return id; }
}
