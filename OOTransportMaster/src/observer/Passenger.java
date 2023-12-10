package observer;


public class Passenger implements Observer, IPassenger {
    int location;
    @Override
    public void update() {
        //shuttle start, phone light on
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void setLocation(int i) {
        location = i;
    }
}


