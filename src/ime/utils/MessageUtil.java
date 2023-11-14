package ime.utils;

import ime.controller.enums.Command;

/**
 * The MessageUtil class provides utility methods for generating messages related to errors,
 * or where ever required.
 */
public class MessageUtil {

  /**
   * Generates a message indicating an invalid number of arguments for a command.
   *
   * @param command The command for which the number of arguments is invalid.
   * @return A formatted message indicating the invalid number of arguments for the command.
   */
  public static String getInvalidNumberOfArgsMessage(Command command) {
    return "Invalid number of Arguments for command: "
            + command.command() + " required Args: "
            + command.requiredArgs();
  }
}
