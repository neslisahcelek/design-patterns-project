package movement;

import display.Display;
import display.Image;
import display.drawable.DrawableBehavior;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Movement {

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





    //ayrıca array için yazmada yuvarlamayı görselleştiriciye bırakalım
    // bu double tipinde ciddi bir konum sadece

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

        for (Shuttle s : Process.shuttles) { //yanlış process sınıfı
            movableArrayList.add(s.getMovable());
        }

        for (Passenger p : Process.passengers) {
            movableArrayList.add(p.getMovable());
        }
    }


    public static void updatePositions () {

        updateMovableArrayList();

        for (int i = 0; i < movableArrayList.size(); i++) {
            //tüm movable olanların hızına hız yönünde göre yeni konumlarını atar
        }
        // hız atamaları ve diğer ler başka yerde

    }

}
