package display;

import display.drawable.util.ImageUrl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Image
{
    private static Image image = null;
    private Image(){}
    public static Image getImage() {
        if (image == null) {
            image = new Image();
        }
        return image;
    }

    private static Color[][] passenger;
    private static Color[][] map;
    private static Color[][] shuttle;
    private static Color[][] mirroredPassenger;
    private static Color[][] mirroredShuttle;


    public Color[][] getMap() {
        if (map == null) {
            map = PNGtoColorArray(ImageUrl.getMapUrl());
        }
        return map;
    }
    public Color[][] getPassenger() {
        if (passenger == null) {
            passenger = PNGtoColorArray(ImageUrl.getPassengerUrl());
        }
        return passenger;
    }
    public Color[][] getShuttle() {
        if (shuttle == null) {
            shuttle = PNGtoColorArray(ImageUrl.getShuttleUrl());
        }
        return shuttle;
    }
    public Color[][] getMirroredPassenger() {
        if (mirroredPassenger == null) {
            mirroredPassenger = PNGtoColorArray(ImageUrl.getPassengerUrl());
            mirroredPassenger = Display.mirror(mirroredPassenger);
        }
        return mirroredPassenger;
    }
    public Color[][] getMirroredShuttle() {
        if (mirroredShuttle == null) {
            mirroredShuttle = PNGtoColorArray(ImageUrl.getShuttleUrl());
            mirroredShuttle = Display.mirror(mirroredShuttle);
        }
        return mirroredShuttle;
    }

    private static Color[][] PNGtoColorArray(String url) {
        try {
            URL imageUrl = new URL(url);
            BufferedImage image;
            image = ImageIO.read(imageUrl);
            int width = image.getWidth();
            int height = image.getHeight();

            Color[][] colors = new Color[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x,y);
                    colors[y][x] = new Color(rgb);
                }
            }

            return colors;

        } catch (IOException e) {
            System.out.println("Images can not loaded.");
            return null;
        }
    }

    private static Color[][] PNGtoColorArrayWithFile(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage image;
            image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            Color[][] colors = new Color[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x,y);
                    colors[y][x] = new Color(rgb);
                }
            }

            return colors;

        } catch (IOException e) {
            System.out.println("Images can not loaded.");
            return null;
        }
    }
}


