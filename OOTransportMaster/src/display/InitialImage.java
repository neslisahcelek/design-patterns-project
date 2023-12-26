package display;

import java.awt.*;

public class InitialImage {
    private static InitialImage image = null;
    private final Color[][] newImage;

    private InitialImage() {
        this.newImage = new Color[Image.getImage().getMap().length][Image.getImage().getMap()[0].length];
    }

    public static InitialImage getImage() {
        if (image == null) {
            image = new InitialImage();
        }
        return image;
    }


}