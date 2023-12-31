package display;

import display.drawable.DrawableBehavior;
import manager.Process;
import movement.movable.MovableBehavior;
import observer.passenger.Passenger;
import observer.shuttle.Shuttle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Display {
    private Frame frame;
    private Panel panel;
    static ArrayList<DrawableBehavior> drawableArrayList = new ArrayList<>();

    public static void addToImage (DrawableBehavior drawable, Color[][] baseImage) {
        Color[][] additionalImage;

        int type = drawable.getType();
        int widthShift = drawable.widthShift;
        int lengthShift = drawable.lengthShift;
        boolean direction = drawable.direction;

        int i = (int) drawable.getPosition().getI() + lengthShift;
        int j = (int) drawable.getPosition().getJ() + widthShift ;

        if(type == 2 && direction) {
            additionalImage = Image.getImage().getPassenger();
        }
        else if(type == 2 && !direction) {
            additionalImage = Image.getImage().getMirroredPassenger();
        }
        else if(type == 3 && direction) {
            additionalImage = Image.getImage().getShuttle();
        }
        else {
            additionalImage = Image.getImage().getMirroredShuttle();
        }


        for (int k = 0; k < additionalImage.length ; k++) {
            for (int l = 0; l < additionalImage[0].length; l++) {
                if(isValidPosition(i+k,j+l,baseImage) && !additionalImage[k][l].equals(new Color(255, 255, 255)))
                {baseImage[i+k][j+l] = additionalImage[k][l];}
            }
        }
    }

    static boolean isValidPosition (int i, int j, Color[][] addition) {
        return isValidPosition(i,j,addition.length-1,addition[0].length-1);
    }

    static boolean isValidPosition (int i, int j,int fieldI, int fieldJ) {
        return i>=0 && fieldI>=i && j>=0 && fieldJ>=j;
    }

    public static void updateImage (Color[][] newImage) {

        for (DrawableBehavior drawableBehavior : drawableArrayList) {
            Display.addToImage(drawableBehavior, newImage);
        }
    }

    public static void updateDrawableArrayList () {

        drawableArrayList.clear();

        drawableArrayList.add(Process.shuttle.getDrawable());

        for (Passenger p : Process.passengers) {
            drawableArrayList.add(p.getDrawable());
        }

        drawableArrayList.sort(Comparator.comparingDouble(d -> d.getPosition().getI()));
    }

    public static Color[][] mirror(Color[][] Image) {

        Color[][] mirroredImage = new Color[ Image.length][ Image[0].length];

        for (int i = 0; i <  Image.length; i++) {
            for (int j = 0; j <  Image[0].length; j++) {
                mirroredImage[i][j] = Image[i][ Image[0].length - 1 - j];
            }
        }

        return mirroredImage;
    }

    public static void updateDirection(DrawableBehavior drawable, MovableBehavior movable)
    {
        if (movable.getVelocityDirection().getJ() > 0) {
            drawable.direction = false;
        } else {
            drawable.direction = true;
        }
    }


    public void createDisplay(Color [][] colors) {
        panel = new Panel(colors);
        frame = new Frame(panel);
    }

    public void updateDisplay(Color [][] colors)
    {
        panel.setColors(colors);
    }

    public Frame getFrame() {
        return frame;
    }

}