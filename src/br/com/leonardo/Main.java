package br.com.leonardo;

import br.com.leonardo.filter.MedianFilter;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by leonardocordeiro on 23/01/17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            BufferedImage image = ImageIO.read(new FileInputStream("image.jpg"));

            MedianFilter medianFilter = new MedianFilter(image);
            BufferedImage filteredImage = medianFilter.apply();

            ImageIO.write(filteredImage, "jpg", new File("filtered-image.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
