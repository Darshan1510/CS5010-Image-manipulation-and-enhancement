package image_manipulation.controller.commands;

import image_manipulation.model.ImageProcessor;

import java.util.Scanner;

/**
 * The RGBSplit class represents an image manipulation command that splits an input RGB image
 * into its three color channels: red, green, and blue, and saves them as separate images.
 * It utilizes the 'rgbSplit' operation from an ImageProcessor.
 */
public class RGBSplit implements ImageProcessorCommand {

  private final String imgName;
  private final String destRedImg;
  private final String destGreenImg;
  private final String destBlueImg;

  /**
   * Constructs an RGBSplit command with the specified input image name and destination image
   * names for the red, green, and blue channels.
   *
   * @param imgName      The name of the input RGB image to be split.
   * @param destRedImg   The name of the destination image for the red channel.
   * @param destGreenImg The name of the destination image for the green channel.
   * @param destBlueImg  The name of the destination image for the blue channel.
   */
  public RGBSplit(String imgName, String destRedImg, String destGreenImg, String destBlueImg) {
    this.imgName = imgName;
    this.destRedImg = destRedImg;
    this.destGreenImg = destGreenImg;
    this.destBlueImg = destBlueImg;
  }

  /**
   * Executes the RGBSplit command by splitting the input RGB image into its red, green, and
   * blue channels and saving them as separate images with the specified destination names.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.rgbSplit(imgName, destRedImg, destGreenImg, destBlueImg);
  }

  /**
   * Creates and returns an RGBSplit command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return An RGBSplit command with the specified input and destination image names for
   * the RGB channels.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destRedImg = s.next();
    String destGreenImg = s.next();
    String destBlueImg = s.next();

    return new RGBSplit(imgName, destRedImg, destGreenImg, destBlueImg);
  }
}
