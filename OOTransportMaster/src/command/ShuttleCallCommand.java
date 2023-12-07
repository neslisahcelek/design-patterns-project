package command;

import observer.Passenger;
import observer.Shuttle;

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

    @Override
    public void undo() {
        shuttle.removeObserver(passenger);
    }
}
