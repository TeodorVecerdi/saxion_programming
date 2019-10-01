package week5;

import processing.core.PApplet;

public class LabQuestion2 extends PApplet {
    int sand = color(254, 227, 164);

    public static void main (String[] args) {
        PApplet.main("week5.LabQuestion2");
    }

    @Override
    public void settings () {
        size(800, 600);
    }

    private void update () {}

    private void render () {
        background(sand);

        drawFigure(150, 100, 200);
        drawFigure(250, 200, 150);
        drawFigure(460, 350, 100);
        drawFigure(100, 400, 50);
        drawFigure(170, 600, 300);
        drawFigure(450, 400, 100);
    }

    private void drawFigure (int x, int y, int size) {
        stroke(0);
        strokeWeight(2);
        fill(104, 164, 164);
        rect(x - size / 8, y, size / 4, size / 8);
        for (int angle = 180; angle < 360; angle += 15) {
            if (angle % 30 == 0) {
                fill(104, 164, 164);
            } else {
                fill(104, 164, 104);
            }
            arc(x, y, size, size, radians(angle), radians(angle + 15), PIE);
        }
    }

    @Override
    public void draw () {
        update();
        render();
    }
}
