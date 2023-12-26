package display.drawable;

import movement.Position;

public abstract class DrawableBehavior {
    public Position position;
    public boolean direction;
    public int type;
    public int widthShift;
    public int lengthShift;

    DrawableBehavior() {
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
