package observer;


import display.drawable.DrawableBehavior;
import display.drawable.DrawablePassenger;
import motion.Position;

public class Passenger extends IPassenger implements Observer {
    private int location;
    private DrawableBehavior drawable;

    public Passenger(int station, double positionI, double positionJ) {
        super.station = station;
        super.drawableBehavior = new DrawablePassenger(positionI, positionJ);
    }

    @Override
    public void update() {
        //shuttle start, phone light on
    }

    public DrawableBehavior getDrawable() {return drawable;}

    public void setDrawable(DrawableBehavior drawable) {this.drawable = drawable;}

    public void updatePosition(Position position)
    {
        drawable.setPosition(position);
    }
}


