public interface Subject {
    void registerObserver();
    void removeObserver();
    void notifyObservers();
}
