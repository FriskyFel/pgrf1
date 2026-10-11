package controller;

import view.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller2D {
    private final Panel panel;

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
    }
}
