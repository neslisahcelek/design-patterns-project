package observer;

import display.drawable.DrawableBehavior;

public abstract class IPassenger {
    DrawableBehavior drawableBehavior;
    int station;

    public IPassenger() {}

    public DrawableBehavior getDrawable() {return drawableBehavior;}

    public int getStation() {
        return station;
    }

    void setStation(int station) {
        this.station = station;
    }
}
