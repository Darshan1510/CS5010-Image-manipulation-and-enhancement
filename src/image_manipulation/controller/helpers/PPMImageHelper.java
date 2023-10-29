package image_manipulation.controller.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import image_manipulation.model.ImageModel;
import image_manipulation.model.RGBImage;
import image_manipulation.model.RGBPixel;


/**
 * This class contains utility methods to read a PPM, JPG and PNG images.
 */
public class PPMImageHelper implements ImageHelper {

  @Override
  public RGBImage readImage(String filePath) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(filePath));

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();

    int height = sc.nextInt();

    int maxValue = sc.nextInt();

    RGBPixel[][] pixels = new RGBPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new RGBPixel(r, g, b);
      }
    }

    return new RGBImage(height, width, pixels);
  }

  @Override
  public void saveImage(ImageModel image, String filepath) throws IOException {
    try {
      StringBuilder sb = new StringBuilder();

      int width = image.getWidth();
      int height = image.getHeight();
      RGBPixel[][] pixels = image.getPixels();

      sb.append("P3").append(System.lineSeparator());
      sb.append(width).append(" ").append(height).append(System.lineSeparator());
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          sb.append(pixels[i][j].getR()).append(System.lineSeparator());
          sb.append(pixels[i][j].getG()).append(System.lineSeparator());
          sb.append(pixels[i][j].getB()).append(System.lineSeparator());
        }
      }
      FileWriter myWriter = new FileWriter(filepath);
      myWriter.write(sb.toString());
      myWriter.close();
    } catch (IOException e) {
      throw new IOException("Invalid path");
    }

  }
}
}

