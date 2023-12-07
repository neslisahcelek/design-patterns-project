package observer;

import java.util.ArrayList;

public class Shuttle implements Subject{ //Receiver
    private ArrayList<Observer> passengers;

    public Shuttle() {
        passengers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer passenger) {
        passengers.add(passenger);
    }

    @Override
    public void removeObserver(Observer passenger) {
        passengers.remove(passenger);
    }

    @Override
    public void notifyObservers() { //Shuttle starts moving
        passengers.forEach(Observer::update);
    }
}
