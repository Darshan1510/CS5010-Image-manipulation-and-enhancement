package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

public class RunScript implements ImageProcessorCommand {

  private final String filepath;

  public RunScript(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void process(ImageProcessor p) {

  }

  public static ImageProcessorCommand apply(Scanner s) {
    String filepath = s.next();
    return new RunScript(filepath);
  }
}
