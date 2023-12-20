package display.drawable;

import movement.Position;

public class DrawablePassenger extends DrawableBehavior {
    public DrawablePassenger(double positionI, double positionJ) {
        super.position = new Position(positionI,positionJ);
        super.type = 2;
    }
}
