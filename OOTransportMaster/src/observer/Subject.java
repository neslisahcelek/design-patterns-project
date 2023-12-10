package observer;

import observer.Observer;

public interface Subject {
    void registerObserver(Passenger observer);
    void removeObserver(Passenger observer);
    void notifyObservers();
}
