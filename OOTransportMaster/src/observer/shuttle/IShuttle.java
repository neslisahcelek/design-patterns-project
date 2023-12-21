package observer.shuttle;

import display.drawable.DrawableBehavior;
import movement.Position;
import movement.movable.MovableBehavior;

public abstract class IShuttle {
    DrawableBehavior drawable;
    MovableBehavior movable;
    Position targetPosition;
    int station;

    public IShuttle() {}

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

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }
    public void setTargetPosition(double i, double j) {
        this.targetPosition.setI(i);
        this.targetPosition.setJ(j);
    }



}
