package main;

import main.misc.GameObject;

public class Box extends GameObject {
    RectCollider2D collider2D;

    public Box (float x, float y, float width, float height) {
        super();
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        collider2D = new RectCollider2D(x, y, width, height, this);
    }

    @Override
    public void update () {

    }

    @Override
    public void render () {
        var a = MainApp.Instance;
        a.fill(0xffaa9999);
        a.stroke(0xffaa7777);
        a.strokeWeight(2f);
        a.rect(Position.x, Position.y, Size.x, Size.y);
    }
}
