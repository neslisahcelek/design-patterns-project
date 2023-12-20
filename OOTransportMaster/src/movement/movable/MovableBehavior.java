package movement.movable;

import movement.Position;
import movement.VelocityDirection;

public abstract class MovableBehavior {
        public Position position;
        public VelocityDirection velocityDirection;
        public double speed;

        public MovableBehavior() {
                this.position = new Position(0,0);
                this.velocityDirection = new VelocityDirection(0,0);
                this.speed = 0;
        }

        public Position getPosition() {
                return position;
        }

        public void setPosition(Position position) {
                this.position = position;
        }

        public VelocityDirection getVelocityDirection() {
                return velocityDirection;
        }

        public void setVelocityDirection(VelocityDirection velocityDirection) {
                this.velocityDirection = velocityDirection;
        }

        public double getSpeed() {
                return speed;
        }

        public void setSpeed(double speed) {
                this.speed = speed;
        }
}

