package ime.model;

public interface ExtensibleImageProcessor extends ImageProcessor {

  void colorCorrection(String imgName, String destImgName);

  void compression(double percentage, String imgName, String destImgName);

  void histogram(String imgName, String destImgName);

}
