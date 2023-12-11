package observer;


import display.Drawable;
import motion.Movable;
import motion.Position;

public class Passenger implements Observer {
    private int location;
    private Movable movable;
    private Drawable drawable;

    public Passenger(Movable movable, Drawable drawable) {
        this.movable = movable;
        this.drawable = drawable;
    }

    @Override
    public void update() {
        //shuttle start, phone light on
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int i) {
        location = i;
    }

    public Movable getMovable() {
        return movable;
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

    public Drawable getDrawable() {return drawable;}

    public void setDrawable(Drawable drawable) {this.drawable = drawable;}

    public void updatePosition(Position position)
    {
        movable.setPosition(position);
        drawable.setPosition(position);
    }
}


