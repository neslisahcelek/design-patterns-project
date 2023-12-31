package command;

import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

public class PassengerCancelCommand implements Command {
    Shuttle shuttle;
    Passenger passenger;

    public PassengerCancelCommand(Shuttle shuttle, Passenger passenger) {
        this.shuttle = shuttle;
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        System.out.println("Passenger " + passenger.getId() + " is cancelled (PassengerCancelCommand)");
        shuttle.removeObserver(passenger);
    }
}
