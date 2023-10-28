package image_manipulation.model;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

import image_manipulation.model.enums.Component;

// TODO: separate interfaces for the colorTransform, colorRepresentation and Filter, flipping.

public interface ImageModel {

  ImageModel horizontalFlip();

  ImageModel verticalFlip();

  ImageModel filter(double[][] kernel); // sharpen & blur

  ImageModel colorTransform(double[][] transformer); // greyscale & sepia

  ImageModel colorRepresentation(Component component); // Red, blue, green, value, intensity,

}
