package week3;

import processing.core.PApplet;

public class BoxCollider extends GameObject{
    private GameObject parent;
    public boolean Visible = true;

    public BoxCollider(PApplet main, float centerX, float centerY, float width, float height) {
        this.main = main;
        X = centerX;
        Y = centerY;
        Width = width;
        Height = height;
    }

    public void update() {}
    public void render() {
        if(!Visible) return;

        main.rectMode(main.CENTER);
        main.noFill();
        main.stroke(0, 255, 0);
        main.strokeWeight(1);
        main.rect(X, Y, Width, Height);
        main.rectMode(main.CORNER);
    }
}
