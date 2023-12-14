package shuttlemanager;

import observer.passenger.Passenger;

class ClosestPassenger {
    Passenger passenger;
    int location;

    public ClosestPassenger(Passenger passenger, int location) {
        this.passenger = passenger;
        this.location = location;
    }
}
