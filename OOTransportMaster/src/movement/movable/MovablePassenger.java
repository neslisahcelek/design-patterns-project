package movement.movable;

import movement.Position;
import movement.VelocityDirection;

public class MovablePassenger extends MovableBehavior {
    public MovablePassenger(double positionI, double positionJ) {
        super.position = new Position(positionI,positionJ);
        super.velocityDirection = new VelocityDirection(0,0);
    }
}
