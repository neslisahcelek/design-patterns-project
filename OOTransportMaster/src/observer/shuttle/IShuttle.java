package observer.shuttle;

import display.drawable.DrawableBehavior;
import motion.Position;
import motion.movable.MovableBehavior;

public abstract class IShuttle {
    DrawableBehavior drawable;
    MovableBehavior movable;
    int station;

    public IShuttle() {}

    public DrawableBehavior getDrawable() {return drawable;}

    public MovableBehavior getMovable() {
        return movable;
    }

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
}
