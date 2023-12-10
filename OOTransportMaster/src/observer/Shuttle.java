package observer;

import java.util.ArrayList;

public class Shuttle implements Subject{ //Receiver
    private ArrayList<Passenger> passengers;

    public Shuttle() {
        passengers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public void removeObserver(Passenger passenger) {
        passengers.remove(passenger);
    }

    @Override
    public void notifyObservers() { //Shuttle starts moving
        passengers.forEach(Observer::update);
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
