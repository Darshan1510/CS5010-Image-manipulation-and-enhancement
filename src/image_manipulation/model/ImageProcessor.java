package image_manipulation.model;

import image_manipulation.model.image.ImageModel;
import image_manipulation.model.enums.Component;

/**
 * The ImageProcessor interface represents the core functionality for processing and manipulating
 * images. It defines methods for loading, saving, transforming, and modifying images using
 * various image processing operations.
 */
public interface ImageProcessor {

    /**
     * Loads an RGBImage into the image processor with the given name.
     *
     * @param imgName    The name under which the image is loaded.
     * @param imageModel The ImageModel to be loaded.
     */
    void load(String imgName, ImageModel imageModel);

    /**
     * Saves the current state of an image with the given name as an ImageModel.
     *
     * @param imgName The name of the image to be saved.
     * @return An ImageModel representing the saved image.
     */
    ImageModel save(String imgName);

    /**
     * Transforms an image using a specified color transformation matrix and saves the result as
     * a new image.
     *
     * @param imgName     The name of the input image.
     * @param destImgName The name of the destination image where the transformed image will be
     *                    saved.
     * @param transformer A color transformation matrix to apply to the input image.
     */
    void colorTransform(String imgName, String destImgName, double[][] transformer);

    /**
     * Converts an input image to grayscale using the specified component (e.g., RED, GREEN, BLUE,
     * INTENSITY, VALUE, etc.).
     *
     * @param imgName     The name of the input image to be converted to grayscale.
     * @param destImgName The name of the destination image where the grayscale image will be saved.
     * @param c           The component to use for the grayscale conversion.
     */
    void grayscale(String imgName, String destImgName, Component c);

    /**
     * Brightens an image by adding an increment value to each pixel's color components.
     *
     * @param imgName     The name of the input image to be brightened.
     * @param destImgName The name of the destination image where the brightened image will be saved.
     * @param increment   The increment value for brightening the image.
     */
    void brighten(String imgName, String destImgName, int increment);

    /**
     * Applies a filter with a specified kernel to the input image, resulting in a modified image.
     *
     * @param imgName     The name of the input image to which the filter will be applied.
     * @param destImgName The name of the destination image where the filtered image will be saved.
     * @param kernel      The kernel representing the filter to apply.
     */
    void filter(String imgName, String destImgName, double[][] kernel);

    /**
     * Performs a horizontal flip on the input image and saves the flipped image as a new image.
     *
     * @param imgName     The name of the input image to be horizontally flipped.
     * @param destImgName The name of the destination image where the flipped image will be saved.
     */
    void horizontalFlip(String imgName, String destImgName);

    /**
     * Performs a vertical flip on the input image and saves the flipped image as a new image.
     *
     * @param imgName     The name of the input image to be vertically flipped.
     * @param destImgName The name of the destination image where the flipped image will be saved.
     */
    void verticalFlip(String imgName, String destImgName);

    /**
     * Splits an RGB image into its red, green, and blue components and saves them as separate images.
     *
     * @param imgName            The name of the input RGB image to be split.
     * @param destRedImgName     The name of the destination image for the red component.
     * @param destGreenImageName The name of the destination image for the green component.
     * @param destBlueImgName    The name of the destination image for the blue component.
     */
    void rgbSplit(String imgName, String destRedImgName, String destGreenImageName,
                  String destBlueImgName);

    /**
     * Combines red, green, and blue images into a single RGB image and saves it as a new image.
     *
     * @param redImgName     The name of the red image to combine.
     * @param greenImageName The name of the green image to combine.
     * @param blueImgName    The name of the blue image to combine.
     * @param destImgName    The name of the destination RGB image where the combined image will
     *                       be saved.
     */
    void rgbCombine(String redImgName, String greenImageName, String blueImgName, String destImgName);
}
