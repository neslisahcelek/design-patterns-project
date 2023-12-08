package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Images
{
    private static Images images;

    private Images(){}

    public static Images getImages() {
        if (images == null) {
            images = new Images();
        }
        return images;
    }

    private static Color[][] passenger;
    private static Color[][] map;
    private static Color[][] shuttle;

    private static final String filePath = "C:\\Users\\salih\\IdeaProjects\\png\\";

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
                colors[y][x] = new Color(rgb, true);
            }
        }

        return colors;


    }
}

