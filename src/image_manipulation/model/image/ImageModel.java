package image_manipulation.model.image;

import image_manipulation.model.enums.Component;

public interface ImageModel {

  int getHeight();

  int getWidth();

  int getMaxValue();

  PixelModel getPixelValues(int i, int j);

  RGBPixel[][] getPixels();

  ImageModel horizontalFlip();

  ImageModel verticalFlip();

  ImageModel brighten(int increment);

  ImageModel filter(double[][] kernel); // sharpen & blur

  ImageModel colorTransform(double[][] transformer); // greyscale & sepia

  ImageModel grayscale(Component component); // Red, blue, green, value, intensity, luma

}
