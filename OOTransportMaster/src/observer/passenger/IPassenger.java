package observer.passenger;

import display.drawable.DrawableBehavior;
import motion.Position;

public abstract class IPassenger {
    DrawableBehavior drawable;
    int station;

    public IPassenger() {}

    public DrawableBehavior getDrawable() {return drawable;}

    public void updatePosition(Position position)
    {
        drawable.setPosition(position);
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
