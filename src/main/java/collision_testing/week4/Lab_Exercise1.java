package collision_testing.week4;

import processing.core.PApplet;

//        surname: Vecerdi
//      firstname: Teodor
//          class: ECM1V.L
// student number: 475884

public class Lab_Exercise1 extends PApplet {
    int black = color(0);
    int white = color(254);
    int sand = color(254, 254, 227);
    int cyan = color(0, 254, 254);
    int blue = color(0, 127, 254);
    int brown = color(127, 64, 0);

    float b1x = 292;
    float b1y = 30;
    float b2x = 325;
    float b2y = 20;
    float b3x = 305;
    float b3y = 10;
    float b1s = 2.5f;
    float b2s = 2.5f;
    float b3s = 2.5f;

    public static void main (String[] args) {
        PApplet.main("collision_testing.week4.Lab_Exercise1");
    }

    public void settings () {
        size(600, 600);
    }

    public void draw () {
        // sky
        background(blue);

        // bubbles
        fill(cyan);
        stroke(0, 191, 254);
        strokeWeight(2);
        ellipse(b1x, b1y, 25, 25);
        ellipse(b2x, b2y, 20, 20);
        ellipse(b3x, b3y, 10, 10);

        //ground
        noStroke();
        fill(sand);
        rect(0, height - 50, width, 50);


        // small mount
        fill(brown);
        stroke(black);
        quad(200, height - 50, 250, height - 100, 400, height - 100, 450, height - 50);

        b1y -= b1s;
        b2y -= b2s;
        b3y -= b3s;

        if (b1y < 0) b1y = height;
        if (b2y < 0) b2y = height;
        if (b3y < 0) b3y = height;
    }
}
