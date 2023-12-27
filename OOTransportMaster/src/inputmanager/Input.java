package inputmanager;

import display.Frame;
import observer.passenger.Passenger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.util.ArrayList;

public class Input
{
    private static ArrayList<Click> clicks = new ArrayList<>();

    public static void mouseEvent(Frame frame)
    {
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1) {
                    clicks.add(new Click(e.getY(),e.getX(),false));
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    clicks.add(new Click(e.getY(),e.getX(),true));
                }
            }
        });
    }


    public static ArrayList<Click> getClicks() {
        return clicks;
    }






}

