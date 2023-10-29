package image_manipulation.controller.commands;

import java.io.IOException;
import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.controller.helpers.AbstractImageHelper;
import image_manipulation.controller.helpers.ImageHelper;
import image_manipulation.model.ImageModel;
import image_manipulation.model.ImageProcessor;

public class Save implements ImageProcessorCommand {
  private final String imgPath;

  private final String imgName;

  public Save(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void process(ImageProcessor p) {
    try {
      ImageModel image = p.save(imgName);
      ImageHelper helper = new AbstractImageHelper();
      helper.saveImage(image, imgPath);
    } catch (IOException ex) {
      // TODO: update the exception to the custom exception.
      throw new RuntimeException("IO Exception");
    }
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String imgPath = s.next();
    String imgName = s.next();

    return new Save(imgPath, imgName);
  }
}
