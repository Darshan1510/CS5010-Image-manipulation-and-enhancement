package ime.controller.commands;

import java.util.Scanner;

import ime.model.ImageProcessor;

/**
 * The GreenComponent class represents an image manipulation command that converts an input image
 * to grayscale while preserving the green component. It utilizes the 'grayscale' operation with
 * the 'Component.GREEN' option from an ImageProcessor to perform this transformation.
 */
public class GreenComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a GreenComponent command with the specified input image name and destination
   * image name.
   *
   * @param imgName     The name of the input image.
   * @param destImgName The name of the destination image where the result will be saved.
   */
  public GreenComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Creates and returns a GreenComponent command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A GreenComponent command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new GreenComponent(imgName, destImgName);
  }

  /**
   * Executes the GreenComponent command by applying the 'grayscale' operation with
   * the 'Component.GREEN' option on the input image and saving the result to the destination
   * image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.greenGrayscale(imgName, destImgName);
  }
}
