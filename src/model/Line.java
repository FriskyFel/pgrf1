package model;

public class Line {
    private final Point2D p1, p2;

    Line (int x1, int y1, int x2, int y2) {
         p1 = new Point2D(x1, y1);
         p2 = new Point2D(x2, y2);
    }

    public Line(Point2D point2D1, Point2D point2D2) {
        this.p1 = point2D1;
        this.p2 = point2D2;
    }

    public Point2D getP1 (){
        return p1;
    }

    public Point2D getP2 (){
        return p2;
    }
}