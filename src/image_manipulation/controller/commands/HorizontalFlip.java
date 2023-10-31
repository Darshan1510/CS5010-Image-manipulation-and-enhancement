package image_manipulation.controller.commands;

import image_manipulation.model.ImageProcessor;

import java.util.Scanner;

/**
 * The HorizontalFlip class represents an image manipulation command that flips an input
 * image horizontally. It applies a horizontal flip operation to the image, reversing the order
 * of pixels along the horizontal axis.
 */
public class HorizontalFlip implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a HorizontalFlip command with the specified input image name and destination
   * image name.
   *
   * @param imgName      The name of the input image.
   * @param destImgName  The name of the destination image where the result will be saved.
   */
  public HorizontalFlip(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the HorizontalFlip command by applying a horizontal flip operation to the
   * input image and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.horizontalFlip(imgName, destImgName);
  }

  /**
   * Creates and returns a HorizontalFlip command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A HorizontalFlip command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new HorizontalFlip(imgName, destImgName);
  }
}
