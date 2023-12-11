package display;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(Panel panel) {
        super("OOCampus");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);

        Dimension panelSize = panel.getPreferredSize();
        int extraWidth = 14;
        int extraHeight = 37;

        setSize(1248+extraWidth, 702+extraHeight);


        /*pack();*/

       /* Dimension panelSize = panel.getPreferredSize();
        setSize(panelSize.width + getInsets().left + getInsets().right,
                panelSize.height + getInsets().top + getInsets().bottom);*/


        setVisible(true);
    }
}