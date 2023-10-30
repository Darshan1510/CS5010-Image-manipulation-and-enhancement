package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.controller.enums.Command;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.enums.Component;

/**
 * The BlueComponent class represents an image manipulation command that converts an input image
 * to grayscale while preserving the blue component. It utilizes the 'grayscale' operation with
 * the 'Component.BLUE' option from an ImageProcessor to perform this transformation.
 */
public class BlueComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a BlueComponent command with the specified input image name and destination
   * image name.
   *
   * @param imgName      The name of the input image.
   * @param destImgName  The name of the destination image where the result will be saved.
   */
  public BlueComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the BlueComponent command by applying the 'grayscale' operation with
   * the 'Component.BLUE' option on the input image and saving the result to the destination image.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.grayscale(imgName, destImgName, Component.BLUE);
  }

  /**
   * Creates and returns a BlueComponent command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A BlueComponent command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new BlueComponent(imgName, destImgName);
  }
}
