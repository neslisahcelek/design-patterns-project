package shuttlemanager;

import movement.Position;

public class Station {

    Position position;

    public Station(double i, double j) {
        this.position = new Position(i,j);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
