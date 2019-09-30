package saxion_programming.misc.collision_testing.collider;

import processing.core.PVector;
import saxion_programming.misc.collision_testing.App;
import saxion_programming.misc.collision_testing.GameObject;

public class RectCollider2D extends Collider2D {

    public int ColliderColor = 0xff00ff00;
    private GameObject parent;
    private App main;

    public RectCollider2D (App main, GameObject parent, float x, float y, float width, float height, float offsetX, float offsetY) {
        this.main = main;
        this.parent = parent;
        Position = new PVector(x, y);
        Offset = new PVector(offsetX, offsetY);
        Size = new PVector(width, height);
        colliderType = ColliderType.RECT;
    }

    public boolean CheckCollisionWith (Collider2D other) {
        if (super.CheckCollisionWith(other)) {
            ColliderColor = 0xffff0000;
            return true;
        }
        ColliderColor = 0xff00ff00;
        return false;
    }

    @Override
    public void update () {
        if (parent != null) {
            Position.x = parent.Position.x;
            Position.y = parent.Position.y;
        }
    }

    @Override
    public void render () {
        main.noFill();
        main.stroke(ColliderColor);
        main.strokeWeight(1f);
        main.rect(Position.x + Offset.x, Position.y + Offset.y, Size.x, Size.y);
    }
}
