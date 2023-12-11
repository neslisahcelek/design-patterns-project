package motion;

public class Movable {

    private Position position;
    private VelocityDirection velocityDirection;
    private double speed;

    public Movable(double positionI,double positionJ) {

        this.position = new Position(positionI,positionJ);
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
