package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

/**
 * The Blur class represents an image manipulation command that applies a blur filter to an
 * input image.
 * It utilizes a kernel (a 3x3 matrix) to perform a blur operation on the image, resulting
 * in a smoother, less detailed appearance.
 */
public class Blur implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  // A 3x3 kernel used for blurring the image
  private static final double[][] kernel = {
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
  };

  /**
   * Constructs a Blur command with the specified input image name and destination image name.
   *
   * @param imgName      The name of the input image.
   * @param destImgName  The name of the destination image where the result will be saved.
   */
  public Blur(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the Blur command by applying a blur filter to the input image using a specified kernel
   * and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.filter(imgName, destImgName, kernel);
  }

  /**
   * Creates and returns a Blur command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Blur command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new Blur(imgName, destImgName);
  }
}
