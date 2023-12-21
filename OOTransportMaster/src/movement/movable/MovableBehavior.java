package movement.movable;

import movement.Position;
import movement.VelocityDirection;

public abstract class MovableBehavior {
        public Position position;
        public VelocityDirection velocityDirection;
        public double speed;
        public boolean immutable;

        public boolean isImmutable() {
                return immutable;
        }

        public void setImmutable(boolean immutable) {
                this.immutable = immutable;
        }

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

        public void setPosition(double i, double j) {
               position.setI(i);
               position.setJ(j);
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

