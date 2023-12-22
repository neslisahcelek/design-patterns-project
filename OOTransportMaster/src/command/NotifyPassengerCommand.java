package command;

import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

public class NotifyPassengerCommand implements Command{
    Shuttle shuttle;
    Passenger passenger;

    public NotifyPassengerCommand(Shuttle shuttle, Passenger passenger) {
        this.shuttle = shuttle;
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        shuttle.notifyObservers();
    }

}
