package display;

import display.drawable.DrawableBehavior;
import inputmanager.Input;
import inputmanager.Process;
import observer.Passenger;
import observer.Shuttle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Display {
    Frame frame;
    Panel panel;
    static ArrayList<DrawableBehavior> drawableArrayList = new ArrayList<>();

    public static void display()
    {
        Display display1 = new Display();
        display1.createDisplay(Image.getMap());


        Timer timer = new Timer(100, e -> {

            display1.updateDisplay(updateImage(Process.shuttles,Process.passengers));
            Process.applyAddRequests();

            //  colorArray[y][x] = Color.RED;
            //  Display.updateDisplay(colorArray);

        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public static Color[][] updateImage (ArrayList<Shuttle> shuttle, ArrayList<Passenger> passenger) {

        Color[][] newImage = Image.getMap();

        updateDrawableArrayList(shuttle,passenger);

        for (int i = 0; i < drawableArrayList.size(); i++) {

            Display.addToImage(drawableArrayList.get(i),newImage);
        }

        return newImage;
    }

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

    void createDisplay(Color [][] colors)
    {
        panel = new Panel(colors);
        frame = new Frame(panel);
    }

    void updateDisplay(Color [][] colors)
    {
        panel.setColors(colors);
    }

    JLabel createLabel(int x, int y, int width, int height, String text)
    {
        //frame.setLayout(null);
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);

        return label;
    }

    JLabel setLabel (JLabel label, String text)
    {
        label.setText(text);
        return label;
    }

    void addLabel(Frame frame, JLabel label)
    {
        frame.add(label);
    }



    void mainExample()
    {
        JLabel label = createLabel(20,20,200,30,"neden");

        Input.mouseEvent(frame);

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




}