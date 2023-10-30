package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.enums.Component;

/**
 * The IntensityComponent class represents an image manipulation command that converts an input
 * image to grayscale while preserving the intensity component. It utilizes the 'grayscale'
 * operation with the 'Component.INTENSITY' option from an ImageProcessor to perform this
 * transformation.
 */
public class IntensityComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs an IntensityComponent command with the specified input image name and destination
   * image name.
   *
   * @param imgName      The name of the input image.
   * @param destImgName  The name of the destination image where the result will be saved.
   */
  public IntensityComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the IntensityComponent command by applying the 'grayscale' operation with
   * the 'Component.INTENSITY' option on the input image and saving the result to the destination
   * image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.grayscale(imgName, destImgName, Component.INTENSITY);
  }

  /**
   * Creates and returns an IntensityComponent command based on the input provided through a
   * Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return An IntensityComponent command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new IntensityComponent(imgName, destImgName);
  }
}
