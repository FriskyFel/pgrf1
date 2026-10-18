package rasterize;

import model.Point2D;
import model.Polygon;

public class PolygonRasterizer {

    private final LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

     public void rasterize(Polygon polygon, int color) {
        rasterize(polygon, color, true);
    }


    public void rasterize(Polygon polygon, int color, boolean closed) {
        int n = polygon.size();

        if (n == 0) {
            return;
        }

        if (n == 1) {
            rasterizePoint(polygon.getFirst(), color);
            return;
        }

        int edges = (closed && n > 2) ? n : n - 1;

        for (int i = 0; i < edges; i++) {
            Point2D a = polygon.getPoint(i);
            Point2D b = polygon.getPoint((i + 1) % n);
            lineRasterizer.rasterize(a, b, color);
        }
    }

     public void rasterizePoint(Point2D p, int color) {
        int x = p.getX();
        int y = p.getY();
        lineRasterizer.rasterize(x - 2, y, x + 2, y, color);
        lineRasterizer.rasterize(x, y - 2, x, y + 2, color);
    }
}
