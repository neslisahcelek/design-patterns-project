package movement.movable;

import movement.Position;
import movement.VelocityDirection;

public class MovableShuttle extends MovableBehavior {
    public MovableShuttle(double positionI, double positionJ) {
        super.position = new Position(positionI,positionJ);
        super.velocityDirection = new VelocityDirection(0,0);
    }
}
