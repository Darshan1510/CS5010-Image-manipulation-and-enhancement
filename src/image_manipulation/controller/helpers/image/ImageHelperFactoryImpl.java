package image_manipulation.controller.helpers.image;

import image_manipulation.controller.utils.ImageUtil;

public class ImageHelperFactoryImpl implements ImageHelperFactory {
    @Override
    public ImageHelper getImageHelper(String filepath) {
        String ext = ImageUtil.getExtension(filepath);
        ImageHelper imageHelper;
        switch (ext) {
            case "ppm":
                imageHelper = new PPMImageHelper();
                break;
            case "jpg":
            case "png":
            case "jpeg":
                imageHelper = new GenericImageHelper();
                break;
            default:
                throw new IllegalArgumentException("Unsupported format: " + ext);
        }
        return imageHelper;

    }
}
