
//import display.Drawable;
//import display.drawable.DrawableShuttleBehavior;
//import motion.movable.MovableShuttle;
import display.Image;
import manager.Process;

public class Main {
    public static void main(String[] args) {
        testSingleton();
        Process.display();
    }

    public static void testSingleton() {  //Singleton Pattern
        Image image1 = Image.getImage();
        Image image2 = Image.getImage();
        if (image1 == image2) {
            System.out.println("Image1 and Image2 reference the same object. Image class is Singleton!");
        } else {
            System.out.println("Image1 and Image2 reference different objects.");
        }
    }







}