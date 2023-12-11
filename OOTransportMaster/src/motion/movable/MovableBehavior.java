package motion.movable;

import motion.Position;
import motion.VelocityDirection;

public interface MovableBehavior {
        Position getPosition();

        void setPosition(Position position);

        VelocityDirection getVelocityDirection();

        void setVelocityDirection(VelocityDirection velocityDirection);

        double getSpeed();

        void setSpeed(double speed);
}
