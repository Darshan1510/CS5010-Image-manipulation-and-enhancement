package image_manipulation.controller.commands;

import image_manipulation.model.ImageProcessor;

import java.util.Scanner;

/**
 * The VerticalFlip class represents an image manipulation command that performs a vertical flip
 * on an input image.
 * This command flips the input image vertically and saves the result as the destination image.
 */
public class VerticalFlip implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a VerticalFlip command with the specified input image name and destination
   * image name.
   *
   * @param imgName      The name of the input image that will be vertically flipped.
   * @param destImgName  The name of the destination image where the vertically flipped image
   *                     will be saved.
   */
  public VerticalFlip(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the VerticalFlip command by flipping the input image vertically and saving the
   * result to the specified destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.verticalFlip(imgName, destImgName);
  }

  /**
   * Creates and returns a VerticalFlip command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A VerticalFlip command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new VerticalFlip(imgName, destImgName);
  }
}
