package ime.model;

import ime.model.ExtensibleImageProcessor;
import ime.model.ImageProcessorImpl;

public class ExtensibleImageProcessorImpl extends ImageProcessorImpl
        implements ExtensibleImageProcessor {

  public ExtensibleImageProcessorImpl(){
    super();
  }

  @Override
  public void colorCorrection(String imgName, String destImgName) {

  }

  @Override
  public void compression(double percentage, String imgName, String destImgName) {

  }

  @Override
  public void histogram(String imgName, String destImgName) {

  }
}
