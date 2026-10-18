package rasterize;

import model.Line;
import model.Point2D;
import raster.Raster;


public abstract class LineRasterizer {

    protected final Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }


    public abstract void rasterize(int x1, int y1, int x2, int y2, int color);

    public void rasterize(Point2D p1, Point2D p2, int color) {
        rasterize(p1.getX(), p1.getY(), p2.getX(), p2.getY(), color);
    }

    public void rasterize(Line line, int color) {
        rasterize(line.getP1(), line.getP2(), color);
    }
}
