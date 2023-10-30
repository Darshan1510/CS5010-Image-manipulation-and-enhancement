package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.enums.Component;

/**
 * The ValueComponent class represents an image manipulation command that converts an input
 * image to grayscale based on its value component (brightness). This command retains the
 * brightness information and discards color.
 */
public class ValueComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  /**
   * Constructs a ValueComponent command with the specified input image name and destination
   * image name.
   *
   * @param imgName      The name of the input image from which the value (brightness) component
   *                     will be extracted.
   * @param destImgName  The name of the destination image where the value component image will
   *                     be saved.
   */
  public ValueComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  /**
   * Executes the ValueComponent command by converting the input image to grayscale based on its
   * value component.
   * The resulting image retains the brightness information while discarding color information.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    p.grayscale(imgName, destImgName, Component.VALUE);
  }

  /**
   * Creates and returns a ValueComponent command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A ValueComponent command with the specified input and destination image names.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new ValueComponent(imgName, destImgName);
  }
}
