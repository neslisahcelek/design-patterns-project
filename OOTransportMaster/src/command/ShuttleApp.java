package command;

import java.util.ArrayList;

public class ShuttleApp { //Invoker, app in passenger's phone
    private ArrayList<Command> commands = new ArrayList<>();

    public void setCommand(Command command) {
        commands.add(command);
    }

    public void takeCommandCall(Command command) {
        command.execute();
    }
}
