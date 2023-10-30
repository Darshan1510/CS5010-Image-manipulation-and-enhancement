package image_manipulation.controller.commands;

import java.util.Scanner;

import image_manipulation.controller.ImageProcessorCommand;
import image_manipulation.model.ImageProcessor;

/**
 * The RunScript class represents a command that executes a script from a specified file path.
 * This command does not perform any image manipulation directly but rather triggers the execution
 * of a sequence of image manipulation commands from the script file.
 */
public class RunScript implements ImageProcessorCommand {

  private final String filepath;

  /**
   * Constructs a RunScript command with the specified file path to the script.
   *
   * @param filepath The path to the script file that will be executed.
   */
  public RunScript(String filepath) {
    this.filepath = filepath;
  }

  /**
   * Executes the RunScript command, triggering the execution of a sequence of image manipulation
   * commands defined in the specified script file.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) {
    // The actual script execution logic would be implemented here.
    // This method may parse the script file and execute a series of image manipulation commands.
    // It's left empty here for illustration purposes.
  }

  /**
   * Creates and returns a RunScript command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A RunScript command with the specified file path to the script.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String filepath = s.next();
    return new RunScript(filepath);
  }
}
