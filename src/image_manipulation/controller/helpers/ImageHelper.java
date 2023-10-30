package image_manipulation.controller.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import image_manipulation.model.ImageModel;
import image_manipulation.model.RGBImage;

/**
 * The ImageHelper interface defines a contract for classes that provide utility
 * methods for reading and saving image files in various formats. It includes methods
 * for reading images in specific formats and for saving images to specific file paths.
 */
public interface ImageHelper {

  /**
   * Reads an image file in a specific format and returns an RGBImage representation
   * of the image.
   *
   * @param filepath The path to the image file to be read.
   * @return An RGBImage object representing the image.
   * @throws IOException If an I/O error occurs during the reading process.
   */
  RGBImage readImage(String filepath) throws IOException;

  /**
   * Saves an ImageModel as an image file at the specified file path.
   *
   * @param image    The ImageModel to be saved as an image.
   * @param filepath The path where the image will be saved.
   * @throws IOException If an I/O error occurs during the saving process.
   */
  void saveImage(ImageModel image, String filepath) throws IOException;
}
