package observer.passenger;


import display.drawable.DrawablePassenger;
import observer.Observer;

public class Passenger extends IPassenger implements Observer {
    private int location;

    public Passenger(int station, double positionI, double positionJ) {
        super.station = station;
        super.drawable = new DrawablePassenger(positionI, positionJ);
    }

    @Override
    public void update() {
        //shuttle start, phone light on
    }
}


