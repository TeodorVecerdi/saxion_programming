package saxion_programming.week4;

import processing.core.PApplet;

public class Quest4 extends PApplet {

    public static void main (String[] args) {
        PApplet.main("saxion_programming.week4.Quest4");
    }

    public void settings() {
        size(600, 400);
        smooth(8);
    }

    public void draw() {
        background(0xdd);
//        drawFace(150, 150, 255, 100, 100);
//        drawFace(50, 190, 127, 100, 52);
//        drawFace(250, 0, 0, 200, 232);

        drawBuilding(100, 100, 60, 120);
        drawBuilding(200, 130, 100, 40);
        drawBuilding(90, 330, 100, 100);
        drawBuilding(330, 230, 30, 200);
        drawBuilding(500, 100, 50, 100);
        drawBuilding(410, 120, 123, 234);
    }

    private void drawBuilding(float x, float y, float width, float height) {
        rectMode(CENTER);
        fill(0x66);
        stroke(0x44);
        strokeWeight(2);
        rect(x, y, width, height);
        triangle(x-width/2 - 10, y - height/2, x, y - height/2 - 30, x + width/2 + 10, y - height/2);
        rectMode(CORNER);
    }
    private void drawFace(float x, float y, float r, float g, float b) {
        noFill();
        stroke(r, g, b);
        strokeWeight(2);
        ellipse(x, y, 100, 80);
        ellipse(x-20, y - 10, 10, 10);
        ellipse(x+20, y - 10, 10, 10);
    }
}
