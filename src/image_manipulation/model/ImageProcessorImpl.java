package image_manipulation.model;

import java.util.HashMap;
import java.util.Map;

import image_manipulation.model.image.ImageModel;
import image_manipulation.model.enums.Component;
import image_manipulation.model.image.RGBImage;
import image_manipulation.model.image.RGBPixel;

/**
 * The ImageProcessorImpl class implements the ImageProcessor interface, providing the core
 * functionality for processing and manipulating images. It manages a collection of images and
 * offers methods for loading, saving, transforming, and modifying these images.
 */
public class ImageProcessorImpl implements ImageProcessor {

    private final Map<String, ImageModel> images;

    /**
     * Constructs a new ImageProcessorImpl instance with an empty map to store images.
     */
    public ImageProcessorImpl() {
        this.images = new HashMap<>();
    }

    @Override
    public void load(String imgName, ImageModel imageModel) {
        this.images.put(imgName, imageModel);
    }

    @Override
    public ImageModel save(String imgName) {
        return this.get(imgName);
    }

    @Override
    public void colorTransform(String imgName, String destImgName, double[][] transformer) {
        this.images.put(destImgName, this.get(imgName).colorTransform(transformer));
    }

    @Override
    public void grayscale(String imgName, String destImgName, Component c) {
        this.images.put(destImgName, this.get(imgName).grayscale(c));
    }

    @Override
    public void brighten(String imgName, String destImgName, int increment) {
        this.images.put(destImgName, this.get(imgName).brighten(increment));
    }

    @Override
    public void filter(String imgName, String destImgName, double[][] kernel) {
        this.images.put(destImgName, this.get(imgName).filter(kernel));
    }

    @Override
    public void horizontalFlip(String imgName, String destImgName) {
        this.images.put(destImgName, this.get(imgName).horizontalFlip());
    }

    @Override
    public void verticalFlip(String imgName, String destImgName) {
        this.images.put(destImgName, this.get(imgName).verticalFlip());
    }

    @Override
    public void rgbSplit(String imgName, String destRedImgName, String destGreenImageName,
                         String destBlueImgName) {
        this.images.put(destRedImgName, this.get(imgName).grayscale(Component.RED));
        this.images.put(destGreenImageName, this.get(imgName).grayscale(Component.GREEN));
        this.images.put(destBlueImgName, this.get(imgName).grayscale(Component.BLUE));
    }

    @Override
    public void rgbCombine(String redImgName, String greenImageName, String blueImgName,
                           String destImgName) {
        ImageModel red = this.get(redImgName);
        ImageModel green = this.get(greenImageName);
        ImageModel blue = this.get(blueImgName);

        if (red.getHeight() == green.getHeight() && blue.getHeight() == red.getHeight()
                && red.getWidth() == blue.getWidth() && red.getWidth() == green.getWidth()) {

            RGBPixel[][] pixelResults = new RGBPixel[red.getHeight()][red.getWidth()];

            for (int i = 0; i < red.getHeight(); i++) {
                for (int j = 0; j < red.getWidth(); j++) {
                    pixelResults[i][j] = new RGBPixel(red.getPixelValues(i, j).getR(),
                            green.getPixelValues(i, j).getG(),
                            blue.getPixelValues(i, j).getB());
                }
            }

            ImageModel combinedImage = new RGBImage(red.getHeight(), red.getWidth(), pixelResults);
            this.images.put(destImgName, combinedImage);
        } else {
            throw new IllegalArgumentException("Images do not have the same dimension.");
        }
    }

    private ImageModel get(String imgName) throws IllegalArgumentException {
        if (this.images.get(imgName) == null) {
            throw new IllegalArgumentException("Image Not Found");
        }
        return this.images.get(imgName);
    }
}
