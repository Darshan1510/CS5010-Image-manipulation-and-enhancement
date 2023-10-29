package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

public class Brighten implements ImageProcessorCommand {

  private final int increment;
  private final String imgName;
  private final String destImgName;

  public Brighten(int increment, String imgName, String destImgName) {
    this.increment = increment;
    this.imgName = imgName;
    this.destImgName = destImgName;
  }

  @Override
  public void process(ImageProcessor p) {
    p.brighten(imgName, destImgName, increment);
  }

  public static ImageProcessorCommand apply(Scanner s) {
    int increment = s.nextInt();
    String imgName = s.next();
    String destImgName = s.next();

    return new Brighten(increment, imgName, destImgName);
  }
}
