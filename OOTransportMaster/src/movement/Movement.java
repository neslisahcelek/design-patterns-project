package movement;

import display.Display;
import display.drawable.DrawableBehavior;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import manager.Process;
import shuttlemanager.ShuttleManager;
import shuttlemanager.Station;

import java.lang.annotation.Target;
import java.util.ArrayList;

import static display.Display.updateDirection;


public class Movement {

    public static VelocityDirection calculateVelocityDirection(
            MovableBehavior movable, Position destinationPosition )
    {
        double startI = movable.getPosition().getI();
        double startJ = movable.getPosition().getJ();

        double endI = destinationPosition.getI();
        double endJ = destinationPosition.getJ();

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




    static void calculateNextPosition(MovableBehavior movable)
    {
        double newPositionI = movable.getPosition().getI() +
                (movable.getVelocityDirection().getI() * movable.getSpeed());

        double newPositionJ = movable.getPosition().getJ() +
                (movable.getVelocityDirection().getJ() * movable.getSpeed());

        movable.setPosition(newPositionI,newPositionJ);
    }

    public static void calculateNextPositionWithDestination(
            MovableBehavior movable, Position destinationPosition)
    {
        double destinationI = destinationPosition.getI();
        double destinationJ = destinationPosition.getJ();

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

        movable.setPosition(newPositionI,newPositionJ);
    }


    public static void updatePositions () {

        for (Shuttle s : Process.shuttles) {
            updatePositions(s.getMovable(), s.getDrawable(), s.getTargetPosition());
        }

    }

    private static void updatePositions(MovableBehavior movable, DrawableBehavior drawable, Position destinationPosition)
    {
        if(!movable.immutable) {

            calculateNextPositionWithDestination(movable,destinationPosition);
            drawable.setPosition(movable.getPosition());
            updateDirection(drawable,movable);

            Process.hasChange = true;
        }

    }




}
