package display;

import observer.passenger.Passenger;
import observer.shuttle.Shuttle;
import display.drawable.DrawableBehavior;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayManager {

    static ArrayList<DrawableBehavior> drawableArrayList = new ArrayList<>();
    public static void updateDrawableArrayList (ArrayList<Shuttle> shuttle, ArrayList<Passenger> passenger) {

        drawableArrayList.clear();

        for (Object obj : shuttle) {
            if (obj instanceof DrawableBehavior) {
                drawableArrayList.add((DrawableBehavior) obj);
            }
        }

        for (Object obj : passenger) {
            if (obj instanceof DrawableBehavior) {
                drawableArrayList.add((DrawableBehavior) obj);
            }
        }

        Collections.sort(drawableArrayList, Comparator.comparingDouble(d -> d.getPosition().getI()));
    }

    public static Color[][] updateImage (ArrayList<Shuttle> shuttle, ArrayList<Passenger> passenger) {

        Color[][] newImage = Image.getMap();

        updateDrawableArrayList(shuttle,passenger);

        for (int i = 0; i < drawableArrayList.size(); i++) {

            addToImage(drawableArrayList.get(i),newImage);
        }

        return newImage;
    }

    public static void addToImage (DrawableBehavior drawable, Color[][] baseImage)
    {
        Color[][] additionalImage;
        int i = (int) drawable.getPosition().getI();
        int j = (int) drawable.getPosition().getJ();
        int type = drawable.getType();

        if(type == 2) {additionalImage = Image.getPassenger();}
        else {additionalImage = Image.getShuttle();}

        for (int k = 0; k < additionalImage.length ; k++) {
            for (int l = 0; l < additionalImage[0].length; l++) {
                if((i+k<=baseImage.length-1) && (j+l<=baseImage[0].length-1))
                {baseImage[i+k][j+l] = additionalImage[k][l];}
            }
        }
    }

    public static void display(ArrayList<Shuttle> shuttles, ArrayList<Passenger> passengers)
    {
        Display display1 = new Display();
        display1.createDisplay(Image.getMap());

        //for
        display1.updateDisplay(updateImage(shuttles,passengers));


        //frame oynatmalaır burada yönetilir?
    }

}