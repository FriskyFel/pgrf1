package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

    private BufferedImage image;

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public BufferedImage getRaster() {
        return image;
    }

    public void paintPixel (int x, int y, int color) {
        image.setRGB(x, y, color);
        this.repaint();
    }

    public void paintLine (int x1, int y1, int x2, int y2, int color) {
        //for ()
        //this.repaint();
    }
}
