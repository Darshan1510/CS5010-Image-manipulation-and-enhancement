package ime.controller.commands;

import java.util.Scanner;

import ime.model.ImageProcessor;

/**
 * The RGBCombine class represents an image manipulation command that combines three separate
 * image components (red, green, and blue) to create a composite RGB image. It utilizes the
 * 'rgbCombine' operation from an ImageProcessor.
 */
public class RGBCombine implements ImageProcessorCommand {

  private final String destImgName;
  private final String redImg;
  private final String greenImg;
  private final String blueImg;

  /**
   * Constructs an RGBCombine command with the specified names of the destination image and
   * component images.
   *
   * @param destImgName The name of the destination image where the RGB composite will be saved.
   * @param redImg      The name of the red component image.
   * @param greenImg    The name of the green component image.
   * @param blueImg     The name of the blue component image.
   */
  public RGBCombine(String destImgName, String redImg, String greenImg, String blueImg) {
    this.destImgName = destImgName;
    this.redImg = redImg;
    this.greenImg = greenImg;
    this.blueImg = blueImg;
  }

  /**
   * Creates and returns an RGBCombine command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return An RGBCombine command with the specified destination image and component image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String destImgName = s.next();
    String redImg = s.next();
    String greenImg = s.next();
    String blueImg = s.next();

    return new RGBCombine(destImgName, redImg, greenImg, blueImg);
  }

  /**
   * Executes the RGBCombine command by combining the red, green, and blue component images to
   * create an RGB image
   * and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.rgbCombine(redImg, greenImg, blueImg, destImgName);
  }
}
