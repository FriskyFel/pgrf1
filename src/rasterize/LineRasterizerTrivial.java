package rasterize;

import raster.Raster;

public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            raster.setPixel(x1, y1, color);
            return;
        }

        if (Math.abs(dx) >= Math.abs(dy)) {

            if (x1 > x2) {
                int tmp = x1; x1 = x2; x2 = tmp;
                tmp = y1; y1 = y2; y2 = tmp;
            }

            double k = (double) (y2 - y1) / (x2 - x1);
            double q = y1 - k * x1;

            for (int x = x1; x <= x2; x++) {
                int y = (int) Math.round(k * x + q);
                raster.setPixel(x, y, color);
            }
        } else {
            if (y1 > y2) {
                int tmp = x1; x1 = x2; x2 = tmp;
                tmp = y1; y1 = y2; y2 = tmp;
            }

            double k = (double) (x2 - x1) / (y2 - y1);
            double q = x1 - k * y1;

            for (int y = y1; y <= y2; y++) {
                int x = (int) Math.round(k * y + q);
                raster.setPixel(x, y, color);
            }
        }
    }
}
