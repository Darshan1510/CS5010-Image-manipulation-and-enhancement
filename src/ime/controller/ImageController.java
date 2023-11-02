package ime.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import ime.controller.commands.BlueComponent;
import ime.controller.commands.Blur;
import ime.controller.commands.Brighten;
import ime.controller.commands.GreenComponent;
import ime.controller.commands.HorizontalFlip;
import ime.controller.commands.ImageProcessorCommand;
import ime.controller.commands.IntensityComponent;
import ime.controller.commands.Load;
import ime.controller.commands.RGBCombine;
import ime.controller.commands.RGBSplit;
import ime.controller.commands.RedComponent;
import ime.controller.commands.RunScript;
import ime.controller.commands.Save;
import ime.controller.commands.Sepia;
import ime.controller.commands.Sharpen;
import ime.controller.commands.ValueComponent;
import ime.controller.commands.VerticalFlip;
import ime.controller.enums.Command;
import ime.model.ImageProcessor;

/**
 * Responsible for handling image manipulation commands provided through user input.
 * It utilizes a Scanner to read commands, maps these commands to specific ImageProcessorCommand
 * functions, and applies them to an ImageProcessor object. The available commands and their
 * associated functions are defined in the getImageProcessorCommand() method.
 */
public class ImageController implements ImageControllerInterface {
  private Readable in;
  private Appendable out;

  /**
   * Constructor for ImageController.
   *
   * @param in  A Readable source for user input.
   * @param out An Appendable destination for program output.
   */
  public ImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * This method creates and populates a map of commands to their corresponding
   * ImageProcessorCommand functions.
   *
   * @return A map containing command strings and associated functions.
   */
  private static Map<String, Function<Scanner, ImageProcessorCommand>> getImageProcessorCommand() {
    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands = new HashMap<>();

    knownCommands.put(Command.BLUE_COMPONENT.command(), BlueComponent::apply);

    knownCommands.put(Command.RED_COMPONENT.command(), RedComponent::apply);
    knownCommands.put(Command.GREEN_COMPONENT.command(), GreenComponent::apply);
    knownCommands.put(Command.SAVE.command(), Save::apply);
    knownCommands.put(Command.LOAD.command(), Load::apply);
    knownCommands.put(Command.INTENSITY_COMPONENT.command(), IntensityComponent::apply);
    knownCommands.put(Command.VALUE_COMPONENT.command(), ValueComponent::apply);
    knownCommands.put(Command.SEPIA.command(), Sepia::apply);
    knownCommands.put(Command.SHARPEN.command(), Sharpen::apply);
    knownCommands.put(Command.BRIGHTEN.command(), Brighten::apply);
    knownCommands.put(Command.HORIZONTAL_FLIP.command(), HorizontalFlip::apply);
    knownCommands.put(Command.VERTICAL_FLIP.command(), VerticalFlip::apply);
    knownCommands.put(Command.RUN_SCRIPT.command(), RunScript::apply);
    knownCommands.put(Command.BLUR.command(), Blur::apply);
    knownCommands.put(Command.RGB_COMBINE.command(), RGBCombine::apply);
    knownCommands.put(Command.RGB_SPLIT.command(), RGBSplit::apply);

    return knownCommands;
  }

  /**
   * The execute() method reads user input, processes image manipulation commands,
   * and applies them to an ImageProcessor object until the user exits.
   */
  @Override
  public void execute(ImageProcessor imageProcessor) throws IOException {
    Objects.requireNonNull(imageProcessor);
    Scanner scan = new Scanner(in);

    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands
            = getImageProcessorCommand();

    while (scan.hasNext()) {
      try {
        ImageProcessorCommand c;
        String in = scan.next();
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
          return;
        Function<Scanner, ImageProcessorCommand> cmd =
                knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          throw new IllegalArgumentException("Invalid command");
        } else {
          c = cmd.apply(scan);
          c.process(imageProcessor);
          out.append("Command performed: ").append(in).append("\n");
        }
      } catch (IOException | IllegalArgumentException e) {
        this.out.append(e.getMessage()).append("\n");
      }
    }
  }
}
