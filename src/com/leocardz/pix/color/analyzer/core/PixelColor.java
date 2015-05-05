package com.leocardz.pix.color.analyzer.core;

/**
 * Created by leocardz on 12/09/2014.
 */

public class PixelColor {

    private Integer alpha;
    private Integer red;
    private Integer green;
    private Integer blue;

    public PixelColor(Integer alpha, Integer red, Integer green, Integer blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Integer getAlpha() {
        return alpha;
    }

    public Integer getRed() {
        return red;
    }

    public Integer getGreen() {
        return green;
    }

    public Integer getBlue() {
        return blue;
    }

    public String toHex() {
        return String.format("#%02x%02x%02x", getRed(), getGreen(), getBlue());
    }

    public String toAlphaHex() {
        return String.format("#%02x%02x%02x%02x", getAlpha(), getRed(), getGreen(), getBlue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PixelColor)) return false;

        PixelColor that = (PixelColor) o;

        if (alpha != null ? !alpha.equals(that.alpha) : that.alpha != null) return false;
        if (blue != null ? !blue.equals(that.blue) : that.blue != null) return false;
        if (green != null ? !green.equals(that.green) : that.green != null) return false;
        if (red != null ? !red.equals(that.red) : that.red != null) return false;

        return true;
    }

    public String getHex() {
        return (getAlpha().equals(255) ? toHex() : toAlphaHex());
    }

    @Override
    public int hashCode() {
        int result = alpha != null ? alpha.hashCode() : 0;
        result = 31 * result + (red != null ? red.hashCode() : 0);
        result = 31 * result + (green != null ? green.hashCode() : 0);
        result = 31 * result + (blue != null ? blue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getHex() +
                " {alpha=" + alpha +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
