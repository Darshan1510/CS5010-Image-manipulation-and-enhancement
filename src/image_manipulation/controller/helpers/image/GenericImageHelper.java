package image_manipulation.controller.helpers.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image_manipulation.controller.utils.ImageUtil;
import image_manipulation.model.image.ImageModel;
import image_manipulation.model.image.RGBImage;
import image_manipulation.model.image.RGBPixel;

/**
 * The GenericImageHelper class provides utility methods for reading and saving image files
 * in a generic format using the Java AWT and BufferedImage libraries. It can read and save
 * images in various formats such as JPG and PNG.
 */
public class GenericImageHelper implements ImageHelper {

    /**
     * Reads an image file in a generic format (e.g., JPG or PNG) and returns an RGBImage
     * representation of the image.
     *
     * @param filepath The path to the image file to be read.
     * @return An RGBImage object representing the image.
     * @throws IOException If an I/O error occurs during the reading process or if the file format is not supported.
     */
    @Override
    public ImageModel readImage(String filepath) throws IOException {

        BufferedImage image = null;
        File f;
        try {
            f = new File(filepath);
            image = ImageIO.read(f);
        } catch (IOException e) {
            throw new IOException();
        }

        int height = image.getHeight();
        int width = image.getWidth();

        RGBPixel[][] pixels = new RGBPixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                Color color = new Color(pixel);
                RGBPixel rgbPixel = new RGBPixel(color.getRed(), color.getGreen(), color.getBlue());
                pixels[i][j] = rgbPixel;
            }
        }
        return new RGBImage(height, width, pixels);
    }

    /**
     * Saves an ImageModel as an image file in a generic format (e.g., JPG or PNG) at the
     * specified file path.
     *
     * @param image    The ImageModel to be saved as an image.
     * @param filepath The path where the image will be saved.
     * @throws IOException If an I/O error occurs during the saving process or if the file format is not supported.
     */
    @Override
    public void saveImage(ImageModel image, String filepath) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        RGBPixel[][] pixels = image.getPixels();
        // Create a BufferedImage from the image data
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = pixels[i][j].getR();
                int green = pixels[i][j].getG();
                int blue = pixels[i][j].getB();
                Color rgb = new Color(
                        red,
                        green,
                        blue
                );
                bufferedImage.setRGB(j, i, rgb.getRGB());
            }
        }
        // Write the BufferedImage to a file
        File outputFile = new File(filepath);
        ImageIO.write(bufferedImage, ImageUtil.getExtension(filepath), outputFile);
    }
}
