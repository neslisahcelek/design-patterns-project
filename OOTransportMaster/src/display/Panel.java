package display;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private Color[][] colors;

    public Panel(Color[][] colors) {
        this.colors = colors;
    }

    public void setColors(Color[][] newColors) {
        this.colors = newColors;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = getWidth() / colors[0].length;
        int cellHeight = getHeight() / colors.length;

        for (int y = 0; y < colors.length; y++) {
            for (int x = 0; x < colors[0].length; x++) {
                g.setColor(colors[y][x]);
                g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }


    }
}