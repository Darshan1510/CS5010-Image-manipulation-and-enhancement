package image_manipulation.controller.helpers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image_manipulation.model.ImageModel;
import image_manipulation.model.RGBImage;
import image_manipulation.model.RGBPixel;

public class GenericImageHelper implements ImageHelper {
  @Override
  public RGBImage readImage(String filepath) throws IOException {

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
    ImageIO.write(bufferedImage, AbstractImageHelper.getExtension(filepath), outputFile);
  }
}
