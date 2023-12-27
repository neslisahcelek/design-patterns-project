package observer.passenger;


import display.drawable.DrawablePassenger;
import movement.movable.MovablePassenger;
import observer.Observer;

public class Passenger extends IPassenger implements Observer {
    private static int id = 1;

    public Passenger(int station, double positionI, double positionJ) {
        super.station = station;
        super.drawable = new DrawablePassenger(positionI, positionJ);
        super.movable = new MovablePassenger(positionI, positionJ);
        super.id = id++;
    }

    @Override
    public void update() {
        System.out.println("Passenger " + super.getId() + " is notified");
    }
}


