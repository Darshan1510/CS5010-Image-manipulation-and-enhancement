package ime.controller.commands;

import java.util.Scanner;

import ime.model.ImageProcessor;

/**
 * The Sharpen class represents an image manipulation command that applies a sharpening filter
 * to an input image.
 * This command utilizes a convolution kernel for sharpening.
 */
public class Sharpen implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a Sharpen command with the specified input image name and destination image name.
   *
   * @param imgName     The name of the input image to which the sharpening filter will be applied.
   * @param destImgName The name of the destination image where the sharpened image will be saved.
   */
  public Sharpen(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Creates and returns a Sharpen command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A Sharpen command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new Sharpen(imgName, destImgName);
  }

  /**
   * Executes the Sharpen command by applying a sharpening filter to the input image and saving
   * the result to the specified destination image. It uses a convolution kernel for sharpening.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.sharpen(imgName, destImgName);
  }
}
