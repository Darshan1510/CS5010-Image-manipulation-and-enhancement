package image_manipulation.controller.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import image_manipulation.model.ImageModel;
import image_manipulation.model.RGBImage;

public interface ImageHelper {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filepath the path of the file.
   */
  RGBImage readImage(String filepath) throws IOException;

  void saveImage(ImageModel image, String filepath) throws IOException;
}
