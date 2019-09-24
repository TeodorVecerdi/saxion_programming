package saxion_programming.week4;

import processing.core.PApplet;

//        surname: Vecerdi
//      firstname: Teodor
//          class: ECM1V.L
// student number: 475884

public class Lab_Exercise2 extends PApplet {
    int black = color(0);
    int white = color(254);
    int navy = color(32, 64, 191);
    int cyan = color(156, 254, 254);
    int yellow = color(248, 248, 0);
    int orange = color(254, 127, 64);
    int brown = color(127, 64, 32);

    int x = 450;
    int clawAngle = 10;

    public static void main (String[] args) {
        PApplet.main("saxion_programming.week4.Lab_Exercise2");
    }

    public void settings () {
        size(800, 600);
    }

    public void draw () {
        if (mousePressed) {
            clawAngle = 10;
        } else {
            clawAngle = 45;
        }

        if (keyPressed) {
            if (key == 'J' || key == 'j') x -= 5;
            if (key == 'L' || key == 'l') x += 5;
        }

        background(navy);
        fill(cyan);
        noStroke();
        float seaLevel = map(mouseX, 0, width, 50, 250);
        rect(0, 0, width, seaLevel); // the sky/sea level


        // the sea bottom
        fill(yellow);
        noStroke();
        rect(0, 550, width, 50);
        x = constrain(x, 150, width - 150);

        // draw crab
        fill(orange);
        stroke(brown);


        // draw crab legs
        strokeWeight(16);
        line(x, 500, x - 75, 580);
        line(x - 50, 500, x - 125, 580);
        line(x, 500, x + 75, 580);
        line(x + 50, 500, x + 125, 580);


        // draw crab body
        strokeWeight(8);
        ellipse(x, 500, 210, 160);


        // draw crab arms
        ellipse(x - 70, 430, 40, 40);
        ellipse(x - 90, 410, 40, 40);
        ellipse(x - 100, 385, 40, 40);

        ellipse(x + 70, 430, 40, 40);
        ellipse(x + 90, 410, 40, 40);
        ellipse(x + 100, 385, 40, 40);


        // draw crab claws
        arc(x + 100, 340, 80, 80, radians(clawAngle - 90), radians(270 - clawAngle), PIE);
        arc(x - 100, 340, 80, 80, radians(clawAngle - 90), radians(270 - clawAngle), PIE);


        // crab eyes
        fill(black);
        strokeWeight(8);
        ellipse(x - 25, 400, 30, 60);
        ellipse(x + 25, 400, 30, 60);
    }
}

