package inputmanager;

import display.Frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input
{
    //eksik nasıl dönecek
    public static void mouseEvent(Frame frame)
    {
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1) {



                }
                else if (e.getButton() == MouseEvent.BUTTON3) {



                }

            }
        });


    }



}

