package movement;

import display.Display;
import display.drawable.DrawableBehavior;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import manager.Process;
import shuttlemanager.ShuttleManager;
import shuttlemanager.Station;

import java.util.ArrayList;

import static shuttlemanager.ShuttleManager.directionShuttle;


public class Movement {

    public static int destinationI;
    public static int destinationJ;

    static ArrayList<MovableBehavior> movableArrayList = new ArrayList<>();
    public static VelocityDirection calculateVelocityDirection(MovableBehavior movable, double endI, double endJ)
    {
        double startI = movable.getPosition().getI();
        double startJ = movable.getPosition().getJ();

        double sum =
                Math.sqrt(Math.pow(endI-startI, 2) +
                        Math.pow(endJ-startJ, 2));

        return new VelocityDirection(((endI-startI)/sum),((endJ-startJ)/sum));
    }

    static VelocityDirection createVelocityDirection (double i, double j)
    {
        double sum = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));

        return new VelocityDirection(((i)/sum),((j)/sum));
    }

    static VelocityDirection resetVelocityDirection()
    {
        return new VelocityDirection(0,0);
    }

    //sadece bu iki methoddan biri yaratabilir VelocityDirection ı




    static Position calculateNextPosition(MovableBehavior movable)
    {
        double newPositionI = movable.getPosition().getI() +
                (movable.getVelocityDirection().getI() * movable.getSpeed());

        double newPositionJ = movable.getPosition().getJ() +
                (movable.getVelocityDirection().getJ() * movable.getSpeed());

        return new Position(newPositionI,newPositionJ);
    }

    public static Position calculateNextPositionWithDestination(
            MovableBehavior movable, double destinationI, double destinationJ)
    {
        double firstPositionI = movable.getPosition().getI();

        double firstPositionJ = movable.getPosition().getJ();

        double newPositionI = firstPositionI +
                (movable.getVelocityDirection().getI() * movable.getSpeed());

        double newPositionJ = firstPositionJ +
                (movable.getVelocityDirection().getJ() * movable.getSpeed());

        boolean toRightI = (newPositionI>= destinationI) &&
                (firstPositionI<= destinationI);
        boolean toLeftI = (newPositionI<= destinationI) &&
                (firstPositionI>= destinationI);

        boolean toRightJ = (newPositionJ>= destinationJ) &&
                (firstPositionJ<= destinationJ);
        boolean toLeftJ = (newPositionJ<= destinationJ) &&
                (firstPositionJ>= destinationJ);

        if((toRightI || toLeftI) && (toRightJ || toLeftJ))
        {
            newPositionI = destinationI;
            newPositionJ = destinationJ;
        }

        return new Position(newPositionI,newPositionJ);

    }









    public static void updateMovableArrayList ( ) {

        movableArrayList.clear();

        for (Shuttle s : Process.shuttles) {
            movableArrayList.add(s.getMovable());
        }

        for (Passenger p : Process.passengers) {
            movableArrayList.add(p.getMovable());
        }
    }


    public static void updatePositions () {

        for (MovableBehavior movable : movableArrayList) {


            if(!movable.immutable) {


                Position next = movement.Movement.calculateNextPositionWithDestination(movable, destinationI, destinationJ);
                movable.setPosition(next);
                Process.shuttles.get(0).getDrawable().setPosition(next);
                directionShuttle();

              /*  movable.setVelocityDirection(calculateVelocityDirection(movable, stationI, stationJ));
                Position next = calculateNextPositionWithDestination(movable, stationI, stationJ);
                movable.setPosition(next);*/
               // ShuttleManager.shuttleGo();

                Process.hasChange = true;
            }
        }

    }




}
