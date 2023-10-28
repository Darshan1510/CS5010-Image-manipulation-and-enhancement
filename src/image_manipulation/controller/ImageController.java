package image_manipulation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import image_manipulation.controller.commands.BlueComponent;
import image_manipulation.controller.commands.Load;
import image_manipulation.controller.enums.Command;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.ImageProcessorImpl;

public class ImageController implements ImageControllerInterface {
  private Readable in;
  private Appendable out;

  public ImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  private static ImageProcessorCommand apply(Scanner s, int requiredArgs) {
    String line = s.nextLine().trim();
    String[] args = line.split(" ");
    int numOfArgs = args.length;

    if (numOfArgs != requiredArgs) {
      throw new IllegalArgumentException("Invalid arguments, required arguments: " + requiredArgs);
    }

    String imgName = args[0];
    String destImgName = args[1];
    return new BlueComponent(imgName, destImgName);
  }

  @Override
  public void execute() {
    Scanner scan = new Scanner(in);
    ImageProcessor imageProcessor = new ImageProcessorImpl();

    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands
            = getImageProcessorCommand();

    while (scan.hasNext()) {
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
      }
    }
  }

  private static Map<String, Function<Scanner, ImageProcessorCommand>> getImageProcessorCommand() {
    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands = new HashMap<>();

    knownCommands.put(Command.BLUE_COMPONENT.command(), BlueComponent::apply);

    knownCommands.put(Command.RED_COMPONENT.command(),
            s -> apply(s, Command.RED_COMPONENT.args()));
    knownCommands.put(Command.GREEN_COMPONENT.command(),
            s -> apply(s, Command.GREEN_COMPONENT.args()));
    knownCommands.put(Command.SAVE.command(),
            s -> apply(s, Command.SAVE.args()));
    knownCommands.put(Command.LOAD.command(), Load::apply);
    knownCommands.put(Command.INTENSITY_COMPONENT.command(),
            s -> apply(s, Command.INTENSITY_COMPONENT.args()));
    knownCommands.put(Command.VALUE_COMPONENT.command(),
            s -> apply(s, Command.VALUE_COMPONENT.args()));
    knownCommands.put(Command.SEPIA.command(),
            s -> apply(s, Command.SEPIA.args()));
    knownCommands.put(Command.SHARPEN.command(), s -> apply(s, Command.SHARPEN.args()));
    knownCommands.put(Command.BRIGHTEN.command(), s -> apply(s, Command.BRIGHTEN.args()));
    knownCommands.put(Command.HORIZONTAL_FLIP.command(),
            s -> apply(s, Command.HORIZONTAL_FLIP.args()));
    knownCommands.put(Command.VERTICAL_FLIP.command(),
            s -> apply(s, Command.VERTICAL_FLIP.args()));
    knownCommands.put(Command.RUN_SCRIPT.command(), s -> apply(s, Command.RUN_SCRIPT.args()));
    knownCommands.put(Command.BLUR.command(), s -> apply(s, Command.BLUR.args()));
    knownCommands.put(Command.RGB_COMBINE.command(), s -> apply(s, Command.RGB_COMBINE.args()));
    knownCommands.put(Command.RGB_SPLIT.command(), s -> apply(s, Command.RGB_SPLIT.args()));

    return knownCommands;
  }
}
