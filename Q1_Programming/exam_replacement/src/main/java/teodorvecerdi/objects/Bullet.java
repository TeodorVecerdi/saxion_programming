package teodorvecerdi.objects;

import processing.core.PVector;
import teodorvecerdi.MainApp;
import teodorvecerdi.RectCollider2D;
import teodorvecerdi.misc.CollisionLayer;
import teodorvecerdi.misc.RenderLayer;

public class Bullet extends GameObject {
    private final float MaxSpeedPerUpdate = 2f;
    public PVector Direction;
    public RectCollider2D collider2D;
    public float Speed;
    private float Rotation;

    public Bullet(float x, float y, float rotation, float speed) {
        super();
        renderLayer = RenderLayer.World;
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
        if (ShouldDestroy) return;
        int updates = (int) (Speed / MaxSpeedPerUpdate);
        float leftOver = Speed - MaxSpeedPerUpdate * updates;
        for (int i = 0; i < updates; i++) doUpdate(MaxSpeedPerUpdate);
        doUpdate(leftOver);
    }

    private void doUpdate(float Speed) {
        if (ShouldDestroy) return;
        Position.x += Direction.x * Speed;
        Position.y += Direction.y * Speed;
        for (var collider : MainApp.Instance.colliders) {
            if ((collider.collisionLayer & (CollisionLayer.None | CollisionLayer.Trigger | CollisionLayer.Player)) == 0 && collider2D.checkCollisionWith(collider)) {
                System.out.println(this + " collided with " + collider.parent);
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
