package br.com.leonardo.filter;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Arrays;

/**
 * Created by leonardocordeiro on 23/01/17.
 */
public final class MedianFilter implements Filter {
    private BufferedImage image;

    public MedianFilter(BufferedImage image) {
        if(image == null) throw new NullPointerException("image can't be null");

        this.image = image;
    }

    @Override
    public BufferedImage apply() {
        BufferedImage filteredImage = new BufferedImage(image.getWidth(),
                                                        image.getHeight(),
                                                        image.getType());

        Raster readerRaster = image.getRaster();
        WritableRaster writableRaster = filteredImage.getRaster();

        for(int i = 0; i < image.getWidth() - 2; i++) {
            for(int j = 0; j < image.getHeight() - 2; j++) {
    
                int[] windowRed = getWindow(readerRaster, i, j, 0);
                int medianRed = getMedian(windowRed, i, j);

                writableRaster.setSample(i, j, 0, medianRed);

            }
        }

        return filteredImage;
    }

    private int getMedian(int[] windowRed, int i, int j) {
        Arrays.sort(windowRed);
        return windowRed[4];
    }

    private int[] getWindow(Raster readerRaster, int i, int j, int band) {
        int[] window = new int[9];

        window[0] = readerRaster.getSample(i, j, band);
        window[1] = readerRaster.getSample(i + 1, j, band);
        window[2] = readerRaster.getSample(i + 2, j, band);
        window[3] = readerRaster.getSample(i, j + 1, band);
        window[4] = readerRaster.getSample(i + 1, j + 1, band);
        window[5] = readerRaster.getSample(i + 2, j + 1, band);
        window[6] = readerRaster.getSample(i, j + 2, band);
        window[7] = readerRaster.getSample(i + 1, j + 2, band);
        window[8] = readerRaster.getSample(i + 2, j + 2, band);

        return window;
    }

}
