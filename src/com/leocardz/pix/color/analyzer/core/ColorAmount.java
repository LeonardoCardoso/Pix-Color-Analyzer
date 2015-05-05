package com.leocardz.pix.color.analyzer.core;

/**
 * Created by leocardz on 15/09/2014.
 */

public class ColorAmount {

    private PixelColor color;
    private MutableInt amount;

    public ColorAmount(PixelColor color, MutableInt amount) {
        this.color = color;
        this.amount = amount;
    }

    public PixelColor getColor() {
        return color;
    }

    public Integer getAmount() {
        return amount.get();
    }

}
