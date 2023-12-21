package shuttlemanager;

import movement.Position;

public class Station {

    Position position;
    int name;

    public static Station[] stations = {
            new Station(106, 856, 0),
            new Station(130, 763, 1),
            new Station(65, 503, 2),
            new Station(64, 343, 3),
            new Station(285, 342, 4),
            new Station(373, 341, 5),
            new Station(521, 343, 6),
            new Station(550, 177, 7),
            new Station(505, 464, 8),
            new Station(322, 465, 9),
            new Station(262, 505, 10),
            new Station(187, 505, 11),
            new Station(192, 770, 12),
            new Station(322, 796, 13),
            new Station(435, 818, 14),
            new Station(437, 1100, 15),

    };

    public Station(double i, double j,  int name) {
        this.position = new Position(i,j);
        this.name = name;
    }

    public static Station getStation(int station)
    {
        if(station >= 1 && station <= 16)
        {return stations[station-1];}

        else return new Station(0,0,0);
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
