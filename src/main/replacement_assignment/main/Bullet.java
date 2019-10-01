package main;

import main.misc.GameObject;
import processing.core.PVector;

public class Bullet extends GameObject {
    public PVector Direction;
    public float Speed;
    public RectCollider2D collider2D;
    private float Rotation;

    public Bullet(float x, float y, float rotation, float speed) {
        super();
        Position.x = x;
        Position.y = y;
        Size.x = 5f;
        Size.y = 20f;
        Direction = new PVector((float) Math.cos(rotation - Math.PI / 2f), (float) Math.sin(rotation - Math.PI / 2f));
        Speed = speed;
        Rotation = rotation;
        Tag = "Bullet";
        collider2D = new RectCollider2D(Position.x, Position.y, Size.x, Size.y, this, CollisionLayer.Trigger);
        DestroyAfterMillis(1000);
    }

    @Override
    public void update() {
        Position.x += Direction.x * Speed;
        Position.y += Direction.y * Speed;
        for (var collider : MainApp.Instance.colliders) {
            if (collider.equals(this.collider2D)) continue;
            if ((collider.collisionLayer & (CollisionLayer.None | CollisionLayer.Trigger | CollisionLayer.Player)) == 0 && collider2D.checkCollisionWith(collider)) {
                System.out.println("Bullet collided with " + collider.parent.Tag);
                DestroyAfterMillis(0);
                return;
            }
        }
    }

    @Override
    public void render() {
        MainApp.Instance.fill(0x44);
        MainApp.Instance.noStroke();
        MainApp.Instance.pushMatrix();
        MainApp.Instance.translate(Position.x + Size.x / 2f, Position.y + Size.y / 2f);
        MainApp.Instance.rotate(Rotation);
        MainApp.Instance.translate(-Position.x - Size.x / 2f, -Position.y - Size.y / 2f);
        MainApp.Instance.triangle(Position.x, Position.y + Size.x, Position.x + Size.x, Position.y + Size.x, Position.x + Size.x / 2f, Position.y);
        MainApp.Instance.rect(Position.x, Position.y + Size.x, Size.x, Size.y - Size.x);
        MainApp.Instance.popMatrix();
    }
}
