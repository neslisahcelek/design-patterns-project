package movement.movable;

import movement.Position;
import movement.VelocityDirection;

public interface MovableBehavior {
        Position getPosition();

        void setPosition(Position position);

        VelocityDirection getVelocityDirection();

        void setVelocityDirection(VelocityDirection velocityDirection);

        double getSpeed();

        void setSpeed(double speed);
}
