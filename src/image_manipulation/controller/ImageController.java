package image_manipulation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import image_manipulation.controller.commands.BlueComponent;
import image_manipulation.controller.commands.Blur;
import image_manipulation.controller.commands.Brighten;
import image_manipulation.controller.commands.GreenComponent;
import image_manipulation.controller.commands.HorizontalFlip;
import image_manipulation.controller.commands.IntensityComponent;
import image_manipulation.controller.commands.Load;
import image_manipulation.controller.commands.RGBCombine;
import image_manipulation.controller.commands.RGBSplit;
import image_manipulation.controller.commands.RedComponent;
import image_manipulation.controller.commands.RunScript;
import image_manipulation.controller.commands.Save;
import image_manipulation.controller.commands.Sepia;
import image_manipulation.controller.commands.Sharpen;
import image_manipulation.controller.commands.ValueComponent;
import image_manipulation.controller.commands.VerticalFlip;
import image_manipulation.controller.enums.Command;
import image_manipulation.model.ImageProcessor;
import image_manipulation.model.ImageProcessorImpl;

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
}
