package display;

import java.awt.*;

class Display {
    Frame frame;
    Panel panel;

    void createDisplay(Color[][] colors)
    {
        panel = new Panel(colors);
        frame = new Frame(panel);
    }

    void updateDisplay(Color [][] colors)
    {
        panel.setColors(colors);
    }
}