package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

public class RGBCombine implements ImageProcessorCommand {

  private final String destImgName;

  private final String redImg;

  private final String greenImg;

  private final String blueImg;

  public RGBCombine(String destImgName, String redImg, String greenImg, String blueImg) {
    this.destImgName = destImgName;
    this.blueImg = blueImg;
    this.redImg = redImg;
    this.greenImg = greenImg;
  }


  @Override
  public void process(ImageProcessor p) {
    p.rgbCombine(redImg, greenImg, blueImg, destImgName);
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String destImgName = s.next();
    String redImg = s.next();
    String greenImg = s.next();
    String blueImg = s.next();

    return new RGBCombine(destImgName, redImg, greenImg, blueImg);
  }
}
