package image_manipulation.controller.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.controller.enums.Command;
import image_manipulation.model.ImageProcessor;

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
      p.load(imgName, new FileInputStream(imgPath), 0, 0);
    } catch (IOException ex) {
      throw new RuntimeException("File exception!");
    }
  }

  public static ImageProcessorCommand apply(Scanner s) {
    String line = s.nextLine().trim();
    String[] args = line.split(" ");
    int numOfArgs = args.length;
    System.out.println(Arrays.toString(args));
    System.out.println("Args:"+ numOfArgs);

    int requiredArgs = Command.LOAD.args();

    if (numOfArgs != requiredArgs) {
      throw new IllegalArgumentException("Invalid number of " +
              "arguments, required arguments: " + requiredArgs);
    }

    String imgPath = args[0];
    String imgName = args[1];
    return new Load(imgPath, imgName);
  }
}
