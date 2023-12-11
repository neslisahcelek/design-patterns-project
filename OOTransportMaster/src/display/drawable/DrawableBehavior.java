package display.drawable;

import motion.Position;

public interface DrawableBehavior {
    Position getPosition();

    void setPosition(Position position);

    int getType();

    boolean isDirection();

    void setDirection(boolean direction);

}
