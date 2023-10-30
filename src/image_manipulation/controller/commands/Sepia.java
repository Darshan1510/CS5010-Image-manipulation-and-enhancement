package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

/**
 * The Sepia class represents an image manipulation command that applies a Sepia tone effect to
 * an input image.
 * This command utilizes a color transformation matrix to achieve the Sepia effect.
 */
public class Sepia implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  // Sepia transformation matrix
  private static final double[][] transformer = new double[3][3];

  /**
   * Constructs a Sepia command with the specified input image name and destination image name.
   *
   * @param imgName      The name of the input image to which the Sepia effect will be applied.
   * @param destImgName  The name of the destination image where the Sepia-processed image will
   *                     be saved.
   */
  public Sepia(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the Sepia command by applying the Sepia tone effect to the input image and saving
   * the result to the specified destination image. It uses a color transformation matrix for
   * the Sepia effect.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.colorTransform(imgName, destImgName, transformer);
  }

  /**
   * Creates and returns a Sepia command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Sepia command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new Sepia(imgName, destImgName);
  }
}
