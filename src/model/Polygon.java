package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final List<Point2D> points = new ArrayList<>();

    public void addPoint(Point2D point) {
        points.add(point);
    }

    public Point2D getPoint(int index) {
        return points.get(index);
    }

    public Point2D getFirst() {
        return points.get(0);
    }

    public Point2D getLast() {
        return points.get(points.size() - 1);
    }

    public int size() {
        return points.size();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public void clear() {
        points.clear();
    }
}
