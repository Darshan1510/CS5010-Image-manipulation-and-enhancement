package image_manipulation.controller.helpers.image;

import image_manipulation.model.image.ImageModel;
import image_manipulation.model.image.RGBImage;
import image_manipulation.model.image.RGBPixel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The PPMImageHelper class provides utility methods for reading and saving
 * PPM (Portable Pixmap Format) image files.
 */
public class PPMImageHelper implements ImageHelper {

    /**
     * Reads a PPM image file and creates an RGBImage representation of the image.
     *
     * @param filePath The path to the PPM image file to be read.
     * @return An RGBImage object representing the image.
     * @throws FileNotFoundException If the specified file is not found.
     */
    @Override
    public ImageModel readImage(String filePath) throws FileNotFoundException {
        Scanner sc = getScanner(filePath);

        String token;

        token = sc.next();
        if (!token.equals("P3")) {
            throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
        }
        int width = sc.nextInt();

        int height = sc.nextInt();

        int maxValue = sc.nextInt();

        RGBPixel[][] pixels = new RGBPixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = sc.nextInt();
                int g = sc.nextInt();
                int b = sc.nextInt();
                pixels[i][j] = new RGBPixel(r, g, b);
            }
        }

        return new RGBImage(height, width, pixels);
    }

    private static Scanner getScanner(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(filePath));

        StringBuilder builder = new StringBuilder();
        //read the file line by line, and populate a string. This will throw away any comment lines
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.charAt(0) != '#') {
                builder.append(s + System.lineSeparator());
            }
        }

        //now set up the scanner to read from the string we just built
        sc = new Scanner(builder.toString());
        return sc;
    }

    /**
     * Saves an RGBImage as a PPM image file.
     *
     * @param image    The RGBImage to be saved.
     * @param filepath The path to the PPM image file where the image will be saved.
     * @throws IOException If an I/O error occurs during the save operation.
     */
    @Override
    public void saveImage(ImageModel image, String filepath) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();

            int width = image.getWidth();
            int height = image.getHeight();
            RGBPixel[][] pixels = image.getPixels();

            sb.append("P3").append(System.lineSeparator());
            sb.append(width).append(" ").append(height).append(System.lineSeparator());
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    sb.append(pixels[i][j].getR()).append(System.lineSeparator());
                    sb.append(pixels[i][j].getG()).append(System.lineSeparator());
                    sb.append(pixels[i][j].getB()).append(System.lineSeparator());
                }
            }
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(sb.toString());
            myWriter.close();
        } catch (IOException e) {
            throw new IOException("Invalid path");
        }

    }

}

