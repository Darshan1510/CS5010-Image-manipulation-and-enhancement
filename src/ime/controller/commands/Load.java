package ime.controller.commands;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import ime.controller.helpers.image.ImageHelperFactory;
import ime.controller.helpers.image.ImageHelperFactoryImpl;
import ime.model.ImageProcessor;

/**
 * The Load class represents an image manipulation command that loads an image from a specified
 * file path and associates it with a given image name. It uses an ImageHelper to read and process
 * the image file.
 */
public class Load implements ImageProcessorCommand {

  private final String imgPath;
  private final String imgName;

  /**
   * Constructs a Load command with the specified image file path and image name.
   *
   * @param imgPath The file path of the image to be loaded.
   * @param imgName The name to associate with the loaded image.
   */
  public Load(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  /**
   * Creates and returns a Load command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Load command with the specified image file path and image name.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgPath = s.next();
    String imgName = s.next();
    return new Load(imgPath, imgName);
  }

  /**
   * Executes the Load command by reading an image from the specified file path using an
   * ImageHelper, and then associating it with the provided image name.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    try {
      ImageHelperFactory factory = new ImageHelperFactoryImpl();
      InputStream inputStream = factory.getImageHelper(imgPath).readImage(imgPath);
      p.load(imgName, inputStream);
    } catch (IOException ex) {
      // TODO: update the exception to the custom exception.
      throw new RuntimeException("IO Exception " + ex.getMessage());
    }
  }
}
