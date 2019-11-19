package week4;

import processing.core.PApplet;

import java.awt.*;

public class Quest5 extends PApplet {
    private int w = 600;
    private int h = 600;
    private int stripedBackgroundResolution = 10;
    private int numCircles = 10;
    private float noiseBackgroundResolution = 2.5f;
    private float noiseScale = 25f;
    private long noiseSeed = System.currentTimeMillis();

    public static void main (String[] args) {
        PApplet.main("week4.Quest5");
    }

    public void settings () {
        size(w, h);
        smooth(8);
        noiseSeed(noiseSeed);
    }

    public void draw () {
        background(0xff);
        //        stripedBackground(Color.white, Color.pink);
        //        randomNoiseBackground();
        regularBackground(2);

        /*fill(0x11);
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
        }*/
    }

    private void randomNoiseBackground () {
        noStroke();
        float currentNoise;
        float stepsX = width / noiseBackgroundResolution;
        float stepsY = height / noiseBackgroundResolution;
        for (float x = 0; x < width; x += noiseBackgroundResolution) {
            for (float y = 0; y < height; y += noiseBackgroundResolution) {
                currentNoise = noise(x / noiseScale, y / noiseScale);
                fill(currentNoise * 255, 255);
                //                point(x, y);
                rect(x, y, noiseBackgroundResolution, noiseBackgroundResolution);
            }
        }
    }

    private void regularBackground (int type) {
        switch (type) {
            case 0: { //stripped background
                stripedBackground(Color.white, Color.gray);
                break;
            }
            case 1: { //mountains
                int mountainWidth = 100;
                fill(100, 100, 200);
                stroke(80, 80, 180);
                strokeWeight(2);
                for (int i = 0; i < width / (float) mountainWidth; i++) {
                    triangle(i * mountainWidth, height, (i + 0.5f) * mountainWidth, height - mountainWidth, (i + 1) * mountainWidth, height);
                }
                break;
            }
            case 2: { //trees
                int treeWidth = 100;
                for (int i = 0; i < width / (float) treeWidth; i++) {
                    fill(100, 0, 0);
                    stroke(50, 0, 0);
                    rect(i * treeWidth - 10 + treeWidth / 2, height - 100, 20, 100);
                    fill(0, 255, 0);
                    stroke(0, 200, 0);
                    strokeWeight(2);
                    ellipse(i * treeWidth + treeWidth / 2, height - 100, treeWidth, treeWidth);

                }
                break;
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
