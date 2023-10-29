package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

public class Blur implements ImageProcessorCommand {

  private final String imgName;

  private final String destImgName;

  private static final double[][] kernel = new double[3][3];

  public Blur(String imgName, String destImgName) {
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  @Override
  public void process(ImageProcessor p) {
    p.filter(imgName, destImgName, kernel);
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destImgName = s.next();

    return new Blur(imgName, destImgName);
  }
}
