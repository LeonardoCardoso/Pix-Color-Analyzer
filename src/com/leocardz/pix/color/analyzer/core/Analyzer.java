package com.leocardz.pix.color.analyzer.core;

import com.leocardz.pix.color.analyzer.graphics.SurfaceView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by leocardz on 12/09/2014.
 */

public class Analyzer {

    private int max;
    private File file;
    private String path;
    private HashMap<PixelColor, MutableInt> map;

    public Analyzer() {

    }

    public Analyzer(String path) {

        init(path, -1);

    }

    public Analyzer(String path, int max) {
        init(path, max);
    }

    private void init(String path, int max) {

        try {
            this.max = max;
            this.path = path;
            instantiateFile(path);
        } catch (FileNotFoundException e) {
            System.err.print(path + ": ");
            e.printStackTrace();
        }

    }

    /**
     * Instantiate file
     */
    private void instantiateFile(String path) throws FileNotFoundException {

        file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

    }

    public void analyze() {

        try {

            BufferedImage image = ImageIO.read(file);

            int w = image.getWidth();
            int h = image.getHeight();
            map = new HashMap<PixelColor, MutableInt>();
            PixelColor pixelColor = null;

            for (int i = 0; i < w; i++) {

                for (int j = 0; j < h; j++) {

                    int rgb = image.getRGB(i, j);

                    Integer a = (rgb >> 24) & 0xFF;
                    Integer r = (rgb >> 16) & 0xFF;
                    Integer g = (rgb >> 8) & 0xFF;
                    Integer b = (rgb & 0xFF);

                    if (pixelColor == null ||
                            !a.equals(pixelColor.getAlpha()) ||
                            !r.equals(pixelColor.getRed()) ||
                            !g.equals(pixelColor.getGreen()) ||
                            !b.equals(pixelColor.getBlue())) {
                        pixelColor = new PixelColor(a, r, g, b);
                    }

                    MutableInt value = map.get(pixelColor);
                    if (value == null) {
                        value = new MutableInt();
                        map.put(pixelColor, value);
                    } else {
                        value.inc();
                    }

                    map.put(pixelColor, value);

                }

            }

            displayResult();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void displayResult() {
        try {

            ArrayList<ColorAmount> mostDominantColours = sortMostDominantColors();
            new SurfaceView(path, mostDominantColours, max);
            printMostDominantColors(mostDominantColours);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printMostDominantColors(ArrayList<ColorAmount> mostDominantColours) {

        int edge = (max != -1 && max < mostDominantColours.size() ? max : mostDominantColours.size());

        for (int i = 0; i < edge; i++) {
            ColorAmount ca = mostDominantColours.get(i);
            System.out.println("Most dominant color #" + (i + 1) + ": " + ca.getColor().getHex() + " " + ca.getAmount() + " pixels");
        }

    }

    /**
     * Get image's most dominant colours
     */
    private ArrayList<ColorAmount> sortMostDominantColors() {

        ArrayList<ColorAmount> dominantColours = new ArrayList<ColorAmount>();

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            dominantColours.add(new ColorAmount((PixelColor) pairs.getKey(), (MutableInt) pairs.getValue()));
            it.remove();
        }

        if (!dominantColours.isEmpty()) {
            Collections.sort(dominantColours, new Comparator<ColorAmount>() {
                public int compare(ColorAmount o1, ColorAmount o2) {
                    if (o1.getAmount().equals(o2.getAmount()))
                        return 0;
                    return o1.getAmount() > o2.getAmount() ? -1 : 1; // Descending
                }
            });
        }

        return dominantColours;

    }

}