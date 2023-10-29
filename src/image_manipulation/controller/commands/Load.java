package image_manipulation.controller.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.controller.enums.Command;
import image_manipulation.controller.helpers.AbstractImageHelper;
import image_manipulation.controller.helpers.ImageHelper;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.RGBImage;

public class Load implements ImageProcessorCommand {

  private final String imgPath;

  private final String imgName;

  public Load(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void process(ImageProcessor p) {
    try {
      ImageHelper helper = new AbstractImageHelper();
      RGBImage rgbImage = helper.readImage(imgPath);
      p.load(imgName, rgbImage);
    } catch (IOException ex) {
      // TODO: update the exception to the custom exception.
      throw new RuntimeException("IO Exception");
    }
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String imgPath = s.next();
    String imgName = s.next();
    return new Load(imgPath, imgName);
  }
}
