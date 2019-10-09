package teodorvecerdi.test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import teodorvecerdi.math.SimplexNoise;

import java.awt.*;

public class SimplexNoiseTest extends PApplet {

    int width = 800, height = 800;
    int octaves = 8;
    float persistance = 0.4f;
    float frequency = 0.003f;
    float offsetX;
    float offsetY;
    float radius = 250f;
    float smoothRadius = 300f;
    int waterThreshold = 90;
    int sandThreshold = 127;
    Color seaBlue = new Color(0x00, 0x69, 0x94, 0xff);
    Color sand = new Color(0xc2, 0xb2, 0x80, 0xff);

    float[][] pixels;
    float[][] mask;

    public static void main (String[] args) {
        PApplet.main("teodorvecerdi.test.SimplexNoiseTest");
    }

    @Override
    public void settings () {
        size(width, height);
        smooth(8);
        pixels = new float[width][height];
        mask = new float[width][height];
        calculate();
        calculateMask();
    }

    @Override
    public void draw () {
        background(0);
        noFill();
        strokeWeight(1f);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                var val = pixels[x][y] * mask[x][y];
                if (val < waterThreshold) stroke(seaBlue.getRed(), seaBlue.getGreen(), seaBlue.getBlue());
                else if (val < sandThreshold) {
                    var red = lerp(sand.getRed(), seaBlue.getRed(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                    var green = lerp(sand.getGreen(), seaBlue.getGreen(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                    var blue = lerp(sand.getBlue(), seaBlue.getBlue(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                    stroke(red, green, blue);
                } else stroke(sand.getRed(), sand.getGreen(), sand.getBlue());
                point(x, y);
            }
        }
        //        for (int x = 0; x < width; x++) {
        //            for (int y = 0; y < height; y++) {
        //                //                var tmp = sumOctaves(octaves, x + offsetX, y + offsetY, persistance, frequency, 0, 255);
        //                //                stroke(tmp);
        //                stroke(mask[x][y]);
        //                point(x, y);
        //            }
        //        }
        System.out.println("Loop done");
        noLoop();
    }

    private float sumOctaves (int octaves, float x, float y, float persistance, float frequency, float low, float high) {
        float maxAmp = 0;
        float amp = 1;
        float noise = 0;
        for (int i = 0; i < octaves; i++) {
            noise += SimplexNoise.noise(x * frequency, y * frequency) * amp;
            maxAmp += amp;
            amp *= persistance;
            frequency *= 2;
        }
        noise /= maxAmp;
        noise = noise * (high - low) / 2 + (high + low) / 2;
        return noise;
    }

    private void calculate () {
        //get random offset
        offsetX = random(0, 1000000);
        offsetY = random(0, 1000000);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = sumOctaves(octaves, x + offsetX, y + offsetY, persistance, frequency, 0, 255);
            }
        }
    }

    private void calculateMask () {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float distSq = (width / 2f - x) * (width / 2f - x) + (height / 2f - y) * (height / 2f - y);
                if (distSq <= radius * radius) mask[x][y] = 1;
                else if (distSq <= smoothRadius * smoothRadius) {
                    mask[x][y] = lerp(0, 1, (smoothRadius * smoothRadius - distSq) / (smoothRadius * smoothRadius - radius * radius));
                } else mask[x][y] = 0;
            }
        }
    }

    @Override
    public void keyReleased (KeyEvent event) {
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            calculate();
            looping = true;
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            octaves++;
            System.out.println(octaves);
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            octaves--;
            System.out.println(octaves);
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            frequency -= 0.001f;
            if (frequency < 0) frequency = 0;
            System.out.println(frequency);
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            frequency += 0.001f;
            System.out.println(frequency);
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
            persistance += 0.1f;
            System.out.println(persistance);
        }
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
            persistance -= 0.1f;
            if (persistance < 0) persistance = 0;
            System.out.println(persistance);
        }
    }
}
