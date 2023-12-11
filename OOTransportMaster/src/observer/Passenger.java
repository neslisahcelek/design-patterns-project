package observer;


public class Passenger implements Observer {
    int location;
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
}


