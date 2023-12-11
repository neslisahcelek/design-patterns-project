package motion.movable;

import motion.Position;
import motion.VelocityDirection;

public class MovableShuttle implements MovableBehavior {

    private Position position;
    private VelocityDirection velocityDirection;
    private double speed;

    public MovableShuttle(double positionI, double positionJ) {

        this.position = new Position(positionI,positionJ);
        this.velocityDirection = new VelocityDirection(0,0);
        this.speed = 0;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public VelocityDirection getVelocityDirection() {
        return velocityDirection;
    }

    @Override
    public void setVelocityDirection(VelocityDirection velocityDirection) {
        this.velocityDirection = velocityDirection;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
