package display;

import motion.Position;

public class Drawable {
    private Position position;
    private boolean direction;
    final private int type;

    public Drawable(double positionI,double positionJ,int type) {
        this.position = new Position(positionI,positionJ);
        this.type = type;
        this.direction = true;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public boolean isDirection() {
        return direction;
    }
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

}
