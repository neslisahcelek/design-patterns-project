package shuttlemanager;

public class Station {
    int locationI;
    int locationJ;
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

    public Station(int locationI, int locationJ,  int name) {
        this.locationI = locationI;
        this.locationJ = locationJ;
        this.name = name;
    }

    public int getLocationI() {
        return locationI;
    }

    public void setLocationI(int locationI) {
        this.locationI = locationI;
    }

    public int getLocationJ() {
        return locationJ;
    }

    public void setLocationJ(int locationJ) {
        this.locationJ = locationJ;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
