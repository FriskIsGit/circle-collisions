package tests.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingSandbox {

    public static void main(String[] args) throws AWTException, InterruptedException{
        JFrame frame = buildFrame();
        Robot bot = new Robot();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D gfx = (Graphics2D)g;
                super.paintComponent(gfx);
                gfx.drawImage(bot.createScreenCapture(new Rectangle(1200, 800)), 0, 0, null);
            }
        };

        frame.add(panel);
        panel.repaint();
        while(true){
            Thread.sleep(50);
            panel.repaint();
        }
    }


    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        return frame;
    }


}
