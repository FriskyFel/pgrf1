package controller;

import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller2D {
    private final Panel panel;
    private enum VERSION {LINE, POLYGON};
    private ArrayList<Line> lines = new ArrayList<>();
    private Polygon polygon = new Polygon();

    public Controller2D(Panel panel) {
        this.panel = panel;
        initlizeEventListeners();
    }

    private void initlizeEventListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //panel.paintPixel(e.getX(), e.getY(), 0xFFFF0000 );
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                panel.paintPixel(e.getX(), e.getY(), 0xFFFF0000 );
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    panel.clearCanvas();
                    System.out.println("it worked?");
                }
            }
        });
    }
}
