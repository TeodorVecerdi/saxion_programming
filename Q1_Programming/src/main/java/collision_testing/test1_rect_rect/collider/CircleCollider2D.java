package collision_testing.test1_rect_rect.collider;

import collision_testing.test1_rect_rect.App;
import collision_testing.test1_rect_rect.GameObject;
import processing.core.PConstants;
import processing.core.PVector;

public class CircleCollider2D extends Collider2D {
    public int ColliderColor = 0xff00ff00;
    private GameObject parent;
    private App main;

    public CircleCollider2D (App main, GameObject parent, float x, float y, float radius, float offsetX, float offsetY) {
        this.main = main;
        this.parent = parent;
        Position = new PVector(x, y);
        Offset = new PVector(offsetX, offsetY);
        Size = new PVector(radius, radius);
        colliderType = ColliderType.CIRCLE;
    }

    @Override
    public void update () {
        if (parent != null) {
            Position.x = parent.Position.x + Size.x / 2f;
            Position.y = parent.Position.y;
        }
    }

    @Override
    public void render () {
        main.noFill();
        main.stroke(ColliderColor);
        main.strokeWeight(1f);
        main.ellipseMode(PConstants.RADIUS);
        main.ellipse(Position.x + Offset.x, Position.y + Offset.y, Size.x, Size.y);
        main.ellipseMode(PConstants.CORNER);
    }
}
