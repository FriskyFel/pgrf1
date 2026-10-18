package controller;

import model.Line;
import model.Point2D;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller2D {
    private final Panel panel;
    private enum VERSION {LINE, POLYGON};
    private VERSION version = VERSION.LINE;
    private ArrayList<Line> lines = new ArrayList<>();
    private Polygon polygon = new Polygon();
    private Point2D dragPoint2D = new Point2D();

    public Controller2D(Panel panel) {
        this.panel = panel;
        initlizeEventListeners();
        updateMode();
    }

    private void updateMode() {
        String name = (version == VERSION.LINE) ? "line" : "polygon";
        panel.setVersionInfo(name);
    }

    private void initlizeEventListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    dragPoint2D.setX(e.getX());
                    dragPoint2D.setY(e.getY());
                }
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
                }
            }
        });
    }
}
