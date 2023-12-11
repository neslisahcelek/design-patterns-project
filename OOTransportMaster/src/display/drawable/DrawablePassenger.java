package display.drawable;

import motion.Position;

public class DrawablePassenger implements DrawableBehavior {
    private Position position;
    private boolean direction;
    final private int type;

    public DrawablePassenger(double positionI, double positionJ) {
        this.position = new Position(positionI,positionJ);
        this.type = 2;
        this.direction = true;
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
    public int getType() {
        return type;
    }

    @Override
    public boolean isDirection() {
        return direction;
    }

    @Override
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

}
