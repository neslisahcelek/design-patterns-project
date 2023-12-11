package display;

import inputmanager.Input;

import javax.swing.*;
import java.awt.*;

public class Display {
    Frame frame;
    Panel panel;

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

}