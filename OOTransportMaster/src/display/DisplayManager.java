package display;

import observer.Passenger;
import observer.Shuttle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayManager {

    static ArrayList<Drawable> drawableArrayList = new ArrayList<>();
    public static void updateDrawableArrayList (ArrayList<Shuttle> shuttle, ArrayList<Passenger> passenger) {

        drawableArrayList.clear();

        for (Object obj : shuttle) {
            if (obj instanceof Drawable) {
                drawableArrayList.add((Drawable) obj);
            }
        }

        for (Object obj : passenger) {
            if (obj instanceof Drawable) {
                drawableArrayList.add((Drawable) obj);
            }
        }

        Collections.sort(drawableArrayList, Comparator.comparingDouble(d -> d.getPosition().getI()));
    }

    void draw(ArrayList<Shuttle> shuttle, ArrayList<Passenger> passenger) {

        Color[][] newFrame = Image.getMap();

        updateDrawableArrayList(shuttle,passenger);

        for (int i = 0; i < drawableArrayList.size(); i++) {

            drawdraw(drawableArrayList.get(i).getPosition().getI(),
                    drawableArrayList.get(i).getPosition().getJ(),
                    drawableArrayList.get(i).getType());
        }







        display(newFrame);
    }

    void drawdraw (double i, double j, int type)
    {
        //sol üstten başlanarak yazılır yani sol üst konumudur
        Color[][] add;

        if(type == 2) {add = Image.getPassenger();}
        //if(type == 3)
        else {add = Image.getShuttle();}

        for (int k = 0; k < add.length ; k++) {
            for (int l = 0; l < add[0].length; l++) {
                //???
            }

        }



    }

    void display(Color[][] newFrame)
    {
        Display display1 = new Display();
        display1.createDisplay(Image.getMap());

        //arraylist döner yukarıda olanı önce yazar
        //sol üstten yazmaya başlar

        display1.updateDisplay(newFrame);
        //display1.updateDisplay(Images.getMap());


        //frame oynatmalaır burada yönetilir?

        //veya başka bi yerden, neyse şimdilik onu düşünmeyelim
    }





}