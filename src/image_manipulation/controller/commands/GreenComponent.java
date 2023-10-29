package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.enums.Component;

public class GreenComponent implements ImageProcessorCommand {

  private final String imgName;
  private final String destImgName;

  public GreenComponent(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  @Override
  public void process(ImageProcessor p) {
    p.grayscale(imgName, destImgName, Component.GREEN);
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new BlueComponent(imgName, destImgName);
  }
}
