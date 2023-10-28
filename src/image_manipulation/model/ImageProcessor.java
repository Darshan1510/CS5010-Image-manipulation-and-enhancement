package image_manipulation.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import image_manipulation.model.enums.Component;

public interface ImageProcessor {

  void load(String imgName, InputStream is, int width, int height) throws IOException;

  void save(String imgName);

  void colorTransform(String imgName, String destImgName, double[][] transformer);

  void colorRepresentation(String imgName, String destImgName, Component c);

  void filter(String imgName, String destImgName, double[][] kernel);

  void horizontalFlip(String imgName, String destImgName);

  void verticalFlip(String imgName, String destImgName);

  void rgbSplit(String imgName, String destRedImgName,
                String destGreenImageName, String destBlueImgName);

  void rgbCombine(String redImgName,
                  String greenImageName, String blueImgName, String destImgName);

}
