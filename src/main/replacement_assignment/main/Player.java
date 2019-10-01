package main;

import main.misc.GameObject;
import main.misc.Input;
import processing.core.PVector;

public class Player extends GameObject {

    public float Speed;
    public RectCollider2D collider2D;

    public Player (float x, float y, float width, float height, float speed) {
        super();
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        Speed = speed;
        collider2D = new RectCollider2D(x, y, width, height, this);
    }

    @Override
    public void update () {
        float dx = Speed * Input.GetAxisHorizontal();
        float dy = Speed * Input.GetAxisVertical();

        collider2D.Transform.Move(dx, dy);
        for (var collider : MainApp.Instance.colliders) {
            if (collider.equals(this.collider2D)) continue;
            if (collider2D.checkCollisionWith(collider)) {
                PVector response = collider2D.getCollisionResponse(collider);
                dx += response.x;
                dy += response.y;
            }
        }
        Position.x += dx;
        Position.y += dy;
    }

    @Override
    public void render () {
        MainApp.Instance.fill(0xffff3333);
        MainApp.Instance.noStroke();
        MainApp.Instance.rect(Position.x, Position.y, Size.x, Size.y);
    }
}
