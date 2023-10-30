package image_manipulation.controller.commands;

import java.io.IOException;
import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.controller.helpers.AbstractImageHelper;
import image_manipulation.controller.helpers.ImageHelper;
import image_manipulation.model.ImageModel;
import image_manipulation.model.ImageProcessor;

/**
 * The Save class represents an image manipulation command that saves an image processed by the
 * ImageProcessor to a specified file path. This command leverages an ImageHelper to save the
 * image in a desired format.
 */
public class Save implements ImageProcessorCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Constructs a Save command with the specified file path and the name of the image to be saved.
   *
   * @param imgPath The path where the image will be saved.
   * @param imgName The name of the image to be saved.
   */
  public Save(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  /**
   * Executes the Save command by saving the image processed by the ImageProcessor to the
   * specified file path.
   * It uses an ImageHelper to perform the actual image saving operation.
   *
   * @param p The ImageProcessor used to process the command.
   * @throws RuntimeException if an IO Exception occurs during the image saving process.
   */
  @Override
  public void process(ImageProcessor p) {
    try {
      // Get the image to be saved from the ImageProcessor
      ImageModel image = p.save(imgName);
      // Initialize an ImageHelper (e.g., AbstractImageHelper) to save the image
      ImageHelper helper = new AbstractImageHelper();
      helper.saveImage(image, imgPath);
    } catch (IOException ex) {
      // TODO: Update the exception to a custom exception if available.
      throw new RuntimeException("IO Exception");
    }
  }

  /**
   * Creates and returns a Save command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Save command with the specified file path and image name.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgPath = s.next();
    String imgName = s.next();
    return new Save(imgPath, imgName);
  }
}
