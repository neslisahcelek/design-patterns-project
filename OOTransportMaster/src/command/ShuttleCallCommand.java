package command;

import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

public class ShuttleCallCommand implements Command {
    Shuttle shuttle;
    Passenger passenger;

    public ShuttleCallCommand(Shuttle shuttle, Passenger passenger) {
        this.shuttle = shuttle;
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        shuttle.registerObserver(passenger);
    }
}
