package raster;

import java.awt.image.BufferedImage;
import java.util.Arrays;


public class RasterBufferedImage implements Raster {

    private final BufferedImage img;
    private int clearColor = 0x000000;

    public RasterBufferedImage(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        if (isInside(x, y)) {
            img.setRGB(x, y, color);
        }
    }

    @Override
    public int getPixel(int x, int y) {
        if (!isInside(x, y)) {
            return clearColor;
        }
       return img.getRGB(x, y) & 0xFFFFFF;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public void clear() {
        int width = img.getWidth();
        int[] row = new int[width];
        Arrays.fill(row, clearColor);
         for (int y = 0; y < img.getHeight(); y++) {
            img.setRGB(0, y, width, 1, row, 0, width);
        }
    }

    public void setClearColor(int color) {
        clearColor = color;
    }

     public BufferedImage getImg() {
        return img;
    }

    private boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < img.getWidth() && y < img.getHeight();
    }
}
