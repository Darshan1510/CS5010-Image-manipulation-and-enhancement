package ime.controller;

import java.io.IOException;

import ime.model.ExtensibleImageProcessor;
import ime.model.ImageProcessor;

public interface ExtensibleImageControllerInterface {
  void execute(ExtensibleImageProcessor processor) throws IOException;
}
