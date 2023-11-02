package ime.controller.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import ime.controller.helpers.image.ImageHelper;
import ime.controller.helpers.image.ImageHelperFactoryImpl;
import ime.model.ImageProcessor;

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
      OutputStream outputStream = p.save(imgName);
      // Initialize an ImageHelper (e.g., ImageHelperFactoryImpl) to save the image
      ImageHelper helper = new ImageHelperFactoryImpl().getImageHelper(imgPath);
      helper.saveImage(outputStream, imgPath);
    } catch (IOException ex) {
      throw new RuntimeException("Issue occurred while saving the file.");
    }
  }
}
