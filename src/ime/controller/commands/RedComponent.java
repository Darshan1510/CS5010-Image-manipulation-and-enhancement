package ime.controller.commands;

import java.util.Scanner;

import ime.model.ImageProcessor;

/**
 * The RedComponent class represents an image manipulation command that converts an input image
 * to grayscale while preserving the red component. It utilizes the 'grayscale' operation with
 * the 'Component.RED' option from an ImageProcessor to perform this transformation.
 */
public class RedComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a RedComponent command with the specified input image name and destination image
   * name.
   *
   * @param imgName     The name of the input image.
   * @param destImgName The name of the destination image where the result will be saved.
   */
  public RedComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Creates and returns a RedComponent command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A RedComponent command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new RedComponent(imgName, destImgName);
  }

  /**
   * Executes the RedComponent command by applying the 'grayscale' operation with the
   * 'Component.RED' option on the input image and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.redGrayscale(imgName, destImgName);
  }
}
