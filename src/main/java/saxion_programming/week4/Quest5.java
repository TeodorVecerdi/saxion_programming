package saxion_programming.week4;

import processing.core.PApplet;

import java.awt.*;

public class Quest5 extends PApplet {
    private int w = 600;
    private int h = 600;
    private int stripedBackgroundResolution = 10;
    private int numCircles = 10;

    public static void main (String[] args) {
        PApplet.main("saxion_programming.week4.Quest5");
    }

    public void settings () {
        size(w, h);
        smooth(8);
    }

    public void draw () {
        background(0xff);
        stripedBackground(Color.white, Color.pink);
        fill(0x11);
        float circleSize = width / numCircles;
        for (int i = 0; i < numCircles; i++) {
            for (int j = 0; j < numCircles; j++) {
                ellipse(i * circleSize + circleSize / 2f, j * circleSize + circleSize / 2f, circleSize, circleSize);
            }
        }
        fill(0x33);
        for (int i = 0; i < 8; i++) {
            ellipse(50+ i * (width / 10f), 100, (i + 1) * 8, (i + 1) * 8);
        }
        fill(0x55);
        stroke(0);
        strokeWeight(1f);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < i+1; j++) {
                rect(10 + j * 20, 10 + i * 20, 20, 20);
            }
        }
    }

    private void stripedBackground (Color first, Color second) {
        noStroke();
        float halfResolution = (width / ((float) stripedBackgroundResolution * 2));
        int resolution = (int) halfResolution * 2;
        for (int i = 0; i < stripedBackgroundResolution; i++) {
            fill(first.getRed(), first.getGreen(), first.getBlue());
            rect(i * resolution, 0, halfResolution, height);
            fill(second.getRed(), second.getGreen(), second.getBlue());
            rect(i * resolution + halfResolution, 0, halfResolution, height);
        }
    }

}
