package image_manipulation.model.image;

import java.util.Objects;

/**
 * Represents a concrete RGB pixel with individual red, green, and blue components.
 * This class implements the PixelModel interface, providing storage and retrieval mechanisms
 * for each RGB color component of a pixel.
 * RGB values are expected to be non-negative integers.
 */
public class RGBPixel implements PixelModel {

    /**
     * The red component of the pixel.
     */
    private int r;

    /**
     * The green component of the pixel.
     */
    private int g;

    /**
     * The blue component of the pixel.
     */
    private int b;


    /**
     * Constructs a new Pixel with the specified RGB values.
     *
     * @param r The red component value.
     * @param g The green component value.
     * @param b The blue component value.
     * @throws IllegalArgumentException if any of the RGB values are negative.
     */
    public RGBPixel(int r, int g, int b) throws IllegalArgumentException {
        if (r < 0 || g < 0 || b < 0) {
            throw new IllegalArgumentException("r, g or b values cannot be negative.");
        }

        // TODO: Please write the condition to check the upper bound of the pixel too.
        this.r = this.clamp(r);
        this.g = this.clamp(g);
        this.b = this.clamp(b);
    }

    @Override
    public int getR() {
        return r;
    }

    @Override
    public int getG() {
        return g;
    }

    @Override
    public int getB() {
        return b;
    }

    @Override
    public void setRed(int value) {
        this.r = value;
    }

    @Override
    public void setGreen(int value) {
        this.g = value;
    }

    @Override
    public void setBlue(int value) {
        this.b = value;
    }

    private int clamp(int value) {
        if (value < 0) {
            return 0;
        }
        return Math.min(255, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof RGBPixel)) {
            return false;
        }

        RGBPixel other = (RGBPixel) o;

        return Objects.equals(this.toString(), other.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.r, this.g, this.b);
    }
}
