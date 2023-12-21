package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{
    private static Image images = null;
    private Image(){}
    public static Image getImages() {
        if (images == null) {
            images = new Image();
        }
        return images;
    }

    private static Color[][] passenger;
    private static Color[][] map;
    private static Color[][] shuttle;
    private static Color[][] mirroredPassenger;
    private static Color[][] mirroredShuttle;

    //private static final String filePath = "C:\\Users\\salih\\IdeaProjects\\OOTransportMaster\\design-patterns-project\\Image\\";
    private static final String filePath = "/Users/neslisahcelek/Desktop/CSE/Design Patterns/project/design-patterns-project/Image/";




    public static Color[][] getMap() {
        if (map == null) {
            map = createImage("map.png");
        }
        return map;
    }
    public static Color[][] getPassenger() {
        if (passenger == null) {
            passenger = createImage("passenger.png");
        }
        return passenger;
    }

    public static Color[][] getShuttle() {
        if (shuttle == null) {
            shuttle = createImage("shuttle.png");
        }
        return shuttle;
    }

    public static Color[][] getMirroredPassenger() {
        if (mirroredPassenger == null) {
            mirroredPassenger = createImage("passenger.png");
            mirroredPassenger = Display.mirror(mirroredPassenger);
        }
        return mirroredPassenger;
    }

    public static Color[][] getMirroredShuttle() {
        if (mirroredShuttle == null) {
            mirroredShuttle = createImage("shuttle.png");
            mirroredShuttle = Display.mirror(mirroredShuttle);
        }
        return mirroredShuttle;
    }

    private static Color[][] createImage(String path)
    {
        return PNGtoColorArray(filePath+path);
    }

    private static Color[][] PNGtoColorArray(String filePath) {

        File file = new File(filePath);
        BufferedImage image;
//BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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


    }
}


