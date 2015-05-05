package com.leocardz.pix.color.analyzer.graphics;

import com.leocardz.pix.color.analyzer.core.ColorAmount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by leocardz on 01/10/2014.
 */

public class SurfaceView extends JPanel {

    private static final String TITLE = "Pix Color Analyzer - leocardz";
    private static final long serialVersionUID = -9054075219988539709L;
    Node selection = null;
    private int max;

    private String path = "";
    private int screenWidth, screenHeight;
    private ArrayList<ColorAmount> colorAmounts;

    public SurfaceView(String path, ArrayList<ColorAmount> colorAmounts, int max) {
        setDimensions();
        this.max = max;
        this.path = path;
        this.colorAmounts = colorAmounts;

        setDimensions();
        setLayout(null);

        setPreferredSize(new Dimension(screenWidth, screenHeight));

        setSelection(null);

        buildData();

        JFrame frame = makeFrame();
        frame.setVisible(true);
    }

    private void setDimensions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }

    public JFrame makeFrame() {

        JFrame f = new JFrame(TITLE);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dim = new Dimension((int) (screenWidth / 1.4), (int) (screenHeight / 1.4));
        Dimension dim2 = new Dimension((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));
        setPreferredSize(new Dimension(dim2));
        JScrollPane scrollFrame = new JScrollPane(this);
        setAutoscrolls(true);
        scrollFrame.setPreferredSize(dim);

        Container contentPane = f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollFrame, BorderLayout.CENTER);
        f.pack();

        f.setLocation(screenWidth / 2 - this.getSize().width / 2, screenHeight / 2 - this.getSize().height / 2);

        return f;
    }

    private void buildData() {

        int edge = (max != -1 && max < colorAmounts.size() ? max : colorAmounts.size());

        for (int i = 0; i < edge; i++) {
            ColorAmount colorAmount = colorAmounts.get(i);
            makeNode(colorAmount.getColor().toHex(), colorAmount.getColor().toHex(), i);
        }

        makeNode(null, null, -1);
    }

    // make a new tree.node
    public Node makeNode(String text, String color, int index) {
        Node n = new Node(text, color, index);
        add(n);
        return n;
    }

    public void setSelection(Node n) {

        if (selection != null) {
            selection.repaint();
        }

        selection = n;
    }

    public class Node extends JLabel {

        private static final long serialVersionUID = -3405121483030773951L;

        public Node(String text, String color, int index) {
            super((index == -1 ?
                    "<html><div style='width: " + screenWidth + "px; height: " + screenHeight + "px; background: #121f2d; z-index: 99'></div></html>" :
                    "<html><div style='background: #121f2d; padding-top: " + (100 * (index / 10) + index / 10) + "px; padding-left: " + (index % 10 * screenWidth / 20) + "px; color: white;'>" + (index + 1) + "  " + text + "<div style='width: " + screenWidth / 20 + "px; height: " + screenWidth / 20 + "px; background: " + color + ";'></div></div></html>"));

            setOpaque(true);
            setSize(getPreferredSize());
        }

        public void setText(String text) {
            super.setText(text);
            setSize(getPreferredSize());
        }

    } // end of Node class
}