package teodorvecerdi.test;

import g4p_controls.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.*;

import teodorvecerdi.math.SimplexNoise;

public class SimplexNoiseTest extends PApplet {


    public int width = 800;
    public int height = 800;

    private GSlider octavesSlider;
    private GLabel octavesSliderLabel;
    private int octaves = 8;

    private GSlider persistanceSlider;
    private GLabel persistanceSliderLabel;
    private float persistance = 0.4f;

    private GSlider frequencySlider;
    private GLabel frequencySliderLabel;
    private float frequency = 0.003f;

    private float offsetX;
    private float offsetY;
    private float radius = 200f;
    private float smoothRadius = 270f;
    private int waterThreshold = 90;
    private int sandThreshold = 127;
    private float[][] pixels;
    private float[][] mask;
    private Color seaBlue = new Color(0x00, 0x69, 0x94, 0xff);
    private Color sand = new Color(0xc2, 0xb2, 0x80, 0xff);
    private PImage noiseImage;
    private boolean shouldDraw = true;

    public static void main(String[] args) {
        PApplet.main("teodorvecerdi.test.SimplexNoiseTest");
    }

    @Override
    public void settings() {
        size(width, height);
        smooth(8);

    }

    @Override
    public void setup() {
        G4P.setCursor(CROSS);
        G4P.setGlobalColorScheme(G4P.RED_SCHEME);

        octavesSlider = new GSlider(this, 80, 10, 150, 60, 15);
        octavesSlider.setLimits(8, 1, 16);
        octavesSlider.setShowValue(true);
        octavesSlider.setTextOrientation(G4P.ORIENT_TRACK);
        octavesSlider.setLocalColor(2, color(255));
        octavesSlider.setNbrTicks(16);
        octavesSlider.setStickToTicks(true);
        octavesSliderLabel = new GLabel(this, 0, 10, 80, 60, "Octaves");
        octavesSliderLabel.setTextAlign(GAlign.RIGHT, GAlign.CENTER);
        octavesSliderLabel.setLocalColor(2, color(255));

        persistanceSlider = new GSlider(this, 80, 70, 150, 60, 15);
        persistanceSlider.setLimits(0.4f, 0.1f, 0.9f);
        persistanceSlider.setShowValue(true);
        persistanceSlider.setTextOrientation(G4P.ORIENT_TRACK);
        persistanceSlider.setLocalColor(2, color(255));
        persistanceSlider.setNbrTicks(9);
        persistanceSlider.setStickToTicks(true);
        persistanceSlider.setPrecision(1);
        persistanceSliderLabel = new GLabel(this, 0, 70, 80, 60, "Persistance");
        persistanceSliderLabel.setTextAlign(GAlign.RIGHT, GAlign.CENTER);
        persistanceSliderLabel.setLocalColor(2, color(255));

        frequencySlider = new GSlider(this, 80, 130, 150, 60, 15);
        frequencySlider.setLimits(0.003f, 0.001f, 0.02f);
        frequencySlider.setShowValue(true);
        frequencySlider.setTextOrientation(G4P.ORIENT_TRACK);
        frequencySlider.setLocalColor(2, color(255));
        frequencySlider.setNbrTicks(20);
        frequencySlider.setStickToTicks(true);
        frequencySlider.setPrecision(3);
        frequencySliderLabel = new GLabel(this, 0, 130, 80, 60, "Frequency");
        frequencySliderLabel.setTextAlign(GAlign.RIGHT, GAlign.CENTER);
        frequencySliderLabel.setLocalColor(2, color(255));

        noiseImage = new PImage(width, height);
        pixels = new float[width][height];
        mask = new float[width][height];
        calculate();
        calculateMask();
    }

    @Override
    public void draw() {
        background(0);
        if (shouldDraw) {
            noiseImage.loadPixels();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    var val = pixels[x][y] * mask[x][y];
                    if (val < waterThreshold) {
                        noiseImage.set(x, y, seaBlue.getRGB());
//                        stroke(seaBlue.getRed(), seaBlue.getGreen(), seaBlue.getBlue());
                    } else if (val < sandThreshold) {
                        var red = lerp(sand.getRed(), seaBlue.getRed(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                        var green = lerp(sand.getGreen(), seaBlue.getGreen(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                        var blue = lerp(sand.getBlue(), seaBlue.getBlue(), (sandThreshold - val) / (sandThreshold - waterThreshold));
                        noiseImage.set(x, y, color(red, green, blue));
//                        stroke(red, green, blue);
                    } else {
                        noiseImage.set(x, y, sand.getRGB());
//                        stroke(sand.getRed(), sand.getGreen(), sand.getBlue());
                    }
                    point(x, y);
                }
            }
            noiseImage.updatePixels();
            shouldDraw = false;
        }
        image(noiseImage, 0, 0);
    }

    private float sumOctaves(int octaves, float x, float y, float persistance, float frequency, float low, float high) {
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

    private void calculate() {
        //get random offset
        offsetX = random(0, 1000000);
        offsetY = random(0, 1000000);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = sumOctaves(octaves, x + offsetX, y + offsetY, persistance, frequency, 0, 255);
            }
        }
    }

    private void calculateMask() {
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
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            calculate();
            shouldDraw = true;
        }
    }

    public void handleSliderEvents(GValueControl slider, GEvent event) {
        if (slider == octavesSlider) {
            octaves = octavesSlider.getValueI();
        } else if (slider == persistanceSlider) {
            persistance = persistanceSlider.getValueF();
        } else if (slider == frequencySlider) {
            frequency = frequencySlider.getValueF();
        }
    }

}
