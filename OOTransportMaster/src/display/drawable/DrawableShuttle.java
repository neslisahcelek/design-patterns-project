package display.drawable;

import movement.Position;

public class DrawableShuttle extends DrawableBehavior {
    public DrawableShuttle(double positionI, double positionJ) {
        super.position = new Position(positionI,positionJ);
        super.type = 3;
    }
}

