package image_manipulation.controller.commands;

import image_manipulation.model.ImageProcessor;

import java.util.Scanner;

/**
 * The Brighten class represents an image manipulation command that increases the brightness of
 * an input image.
 * It applies a brightening effect to the image by adding a specified increment to the pixel values.
 */
public class Brighten implements ImageProcessorCommand {

  private final int increment;
  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a Bright command with the specified brightness increment, input image name,
   * and destination image name.
   *
   * @param increment    The brightness increment to apply to the image (positive values
   *                     increase brightness).
   * @param imgName      The name of the input image.
   * @param destImgName  The name of the destination image where the result will be saved.
   */
  public Brighten(int increment, String imgName, String destImgName) {
    this.increment = increment;
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the Brighten command by increasing the brightness of the input image based on
   * the specified increment and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.brighten(imgName, destImgName, increment);
  }

  /**
   * Creates and returns a Brighten command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Brighten command with the specified brightness increment, input and destination
   * image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    int increment = s.nextInt();
    String imgName = s.next();
    String destImgName = s.next();

    return new Brighten(increment, imgName, destImgName);
  }
}
