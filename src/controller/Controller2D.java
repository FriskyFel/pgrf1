package controller;

import model.Line;
import model.Point2D;
import model.Polygon;
import raster.Raster;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerTrivial;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Controller2D {
    private static final int LINE_COLOR = 0xFFFFFF;
    private static final int POLYGON_COLOR = 0xFFFF00;
    private static final int RUBBER_COLOR = 0xFF0000;

    private enum Drag { NONE, LINE, POLYGON }

    private final Panel panel;
    private final Raster raster;
    private final LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;

    private final List<Line> lines = new ArrayList<>();
    private final Polygon polygon = new Polygon();

    private Drag drag = Drag.NONE;
    private Point2D lineStart;
    private Point2D mouse;

    public Controller2D(Panel panel) {
        this.panel = panel;

         raster = panel.getRaster();
        lineRasterizer = new LineRasterizerTrivial(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

        initlizeEventListeners();
        redraw();
    }

    private void initlizeEventListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (drag != Drag.NONE) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {

                    drag = Drag.LINE;
                    lineStart = new Point2D(e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON1) {

                    drag = Drag.POLYGON;
                } else {
                    return;
                }
                mouse = new Point2D(e.getX(), e.getY());
                redraw();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point2D end = new Point2D(e.getX(), e.getY());

                if (drag == Drag.LINE && e.getButton() == MouseEvent.BUTTON3) {

                    lines.add(new Line(lineStart, end));
                } else if (drag == Drag.POLYGON && e.getButton() == MouseEvent.BUTTON1) {
                    polygon.addPoint(end);
                } else {
                    return; }
                stopDrag();
                redraw();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drag == Drag.NONE) {
                    return;
                }
                mouse = new Point2D(e.getX(), e.getY());
                redraw();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    clearAll();
                }
            }
        });
    }

    private void clearAll() {
        lines.clear();
        polygon.clear();
        stopDrag();
        redraw();
    }

    private void stopDrag() {
        drag = Drag.NONE;
        lineStart = null;
        mouse = null;
    }

    private void redraw() {
        raster.clear();

        for (Line line : lines) {
            lineRasterizer.rasterize(line, LINE_COLOR);
        }

           polygonRasterizer.rasterize(polygon, POLYGON_COLOR, drag != Drag.POLYGON);

        if (drag == Drag.LINE) {
            lineRasterizer.rasterize(lineStart, mouse, RUBBER_COLOR);
        } else if (drag == Drag.POLYGON) {
            if (polygon.isEmpty()) {
                 polygonRasterizer.rasterizePoint(mouse, RUBBER_COLOR);
            } else {
                lineRasterizer.rasterize(polygon.getFirst(), mouse, RUBBER_COLOR);
                lineRasterizer.rasterize(polygon.getLast(), mouse, RUBBER_COLOR);
            }
        }

        panel.repaint();
    }
}
