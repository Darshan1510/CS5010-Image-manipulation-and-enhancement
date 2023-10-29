package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

public class RGBSplit implements ImageProcessorCommand {

  private final String imgName;

  private final String destRedImg;

  private final String destGreenImg;

  private final String destBlueImg;

  public RGBSplit(String imgName, String destRedImg, String destGreenImg, String destBlueImg) {
    this.imgName = imgName;
    this.destRedImg = destRedImg;
    this.destGreenImg = destGreenImg;
    this.destBlueImg = destBlueImg;
  }


  @Override
  public void process(ImageProcessor p) {
    p.rgbSplit(imgName, destRedImg, destGreenImg, destBlueImg);
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String imgName = s.next();
    String destRedImg = s.next();
    String destGreenImg = s.next();
    String destBlueImg = s.next();

    return new RGBCombine(imgName, destRedImg, destGreenImg, destBlueImg);
  }
}
