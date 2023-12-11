package observer;

import display.drawable.DrawableBehavior;
import motion.Position;
import motion.movable.IMovable;

public abstract class IShuttle {
    DrawableBehavior drawableBehavior;
    int station;

    public IShuttle() {}

    public DrawableBehavior getDrawable() {return drawableBehavior;}

    public void updatePosition(Position position)
    {
        movable.setPosition(position);
        drawableBehavior.setPosition(position);
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
