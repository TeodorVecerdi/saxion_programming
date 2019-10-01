package week5;

import processing.core.PApplet;

public class LabQuestion1 extends PApplet {
    int x = 300;
    int y = 600;
    float a = x + random(2, 20);

    public static void main (String[] args) {
        PApplet.main("week5.LabQuestion1");
    }

    @Override
    public void settings () {
        size(600, 600);
    }

    @Override
    public void setup () {
        background(32, 191, 254);
        fill(248, 248, 0);
        rect(0, 550, width, 50);
        noFill();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                int x = i * 100 + j * 16;
                int y = 600 - j * 8;
                float a = x + random(2, 20);
                stroke(random(96, 191), 64, 32);
                strokeWeight(random(2, 10));
                bezier(x, y, a, 550, a - random(40), 550 - random(20), x + random(-20, 20), random(140, 500));
            }
        }
    }

    private void update () {

    }

    private void render () {
        background(32, 191, 254);

        fill(248, 248, 0);
        rect(0, 550, width, 50);

        noFill();

        // draws one strand of seaweed
        stroke(random(96, 191), 64, 32);
        strokeWeight(random(2, 10));
        bezier(x, y, a, 550, a - random(40), 550 - random(20), x + random(-20, 20), random(140, 500));
    }

    @Override
    public void draw () {
        //        update();
        //        render();
    }

    private void drawSeaweed (int stroke, int strokeWeight, int x, int y, float a) {
        stroke(stroke);
        strokeWeight(strokeWeight);
        bezier(x, y, a, 550, a - random(40), 550 - random(20), x + random(-20, 20), random(140, 500));
    }
}
