package Client.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JPanel {
    private static final int SCREEN_HEIGHT = 1080;
    private static final int SCREEN_WIDTH = 1920;
    private static JFrame frame;
    private BufferedImage blueCar;
    private BufferedImage redCar;
    private BufferedImage background;
    private keyThread keyThread;
    static double angle = 0;
    static double blueCarX = SCREEN_WIDTH / 2.0;
    static double blueCarY = SCREEN_HEIGHT / 2.0;
    static double redCarX = SCREEN_WIDTH / 2.0;
    static double redCarY = SCREEN_HEIGHT / 2.0;

    public Window(KeyListener keyListener){
        frame = new JFrame();
        keyThread = new keyThread(this);
        blueCar = getImage("Images/car_blue_small.png");
        redCar = getImage("Images/car_red_small.png");
        background = getImage("Images/Track.jpg");
        this.setBackground(Color.blue);
        new Thread(keyThread).start();
        blueCarX = redCar.getWidth() / 2.0;
        blueCarY -= redCar.getHeight() / 2.0;
        redCarX -= redCar.getWidth() / 2.0;
        redCarY -= redCar.getHeight() / 2.0;
        frame.addKeyListener(keyListener);


        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.toFront();
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setVisible(true);
    }





    private BufferedImage getImage(String imageName) {
        try {
            return ImageIO.read(getClass().getResource(imageName));
        } catch (IOException e) {
            throw new Error("no path found");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform atBlueCar = AffineTransform.getTranslateInstance(blueCarX, blueCarY);
        AffineTransform atRedCar = AffineTransform.getTranslateInstance(redCarX, redCarY);
        g.setFont(new Font("arial", 1, 50));
        g.setColor(Color.red);

        g.drawString(angle + "", 70, 50);

        atBlueCar.rotate(Math.toDegrees(angle), blueCar.getWidth() / 2.0, blueCar.getHeight() / 2.0);
        atRedCar.rotate(Math.toDegrees(angle), redCar.getWidth() / 2.0, redCar.getHeight() / 2.0);

        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(background, 0, 0, this);
        graphics2D.drawImage(blueCar, atBlueCar, null);
        graphics2D.drawImage(redCar, atRedCar, null);
    }

}
