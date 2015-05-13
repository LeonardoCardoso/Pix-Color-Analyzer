package com.leocardz.pix.color.analyzer;

import com.leocardz.pix.color.analyzer.core.Analyzer;

/**
 * Created by leocardz on 12/09/2014.
 */

public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            new Analyzer(args[0], Integer.valueOf(args[1])).analyze();
        } else if (args.length == 1) {
            new Analyzer(args[0]).analyze();
        } else {
            System.out.println("Argument options: [image path] [dominant colors amount]");
            System.exit(1);
        }
    }

}
