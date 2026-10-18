package view;

import model.Point2D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    private String version;
    private BufferedImage image;
    private Graphics g;

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        g.setColor(Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        g.setColor(Color.WHITE);
        g.drawString( version , 10, 10);

    }

    public BufferedImage getRaster() {
        return image;
    }

    public void paintPixel (int x, int y, int color) {
        image.setRGB(x, y, color);
        this.repaint();
    }

    public void paintLine (Point2D point2D1, Point2D point2D2) {
        //for ()

        g.drawLine(point2D1.getX(), point2D1.getY(), point2D2.getX(), point2D2.getY());
        this.repaint();
    }

    public void clearCanvas() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.repaint();
        g.setColor(Color.RED);
        paintLine(new Point2D(10, 10), new Point2D(100, 100));
    }

    public void setVersionInfo(String version) {
        this.version = version;
        this.repaint();
    }
}