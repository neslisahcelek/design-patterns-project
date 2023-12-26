package display;

import java.awt.*;

public class InitialImage {
    private static InitialImage initialImage = null;
    private Color[][] newImage;

    private InitialImage() {
        this.newImage = new Color[Image.getImage().getMap().length][Image.getImage().getMap()[0].length];
    }

    public static InitialImage getInitialImage() {
        if (initialImage == null) {
            initialImage = new InitialImage();
        }
        return initialImage;
    }

    public Color[][] getNewImage() {
        return newImage;
    }

    public void setNewImage(Color[][] newImage) {
        this.newImage = newImage;
    }
}