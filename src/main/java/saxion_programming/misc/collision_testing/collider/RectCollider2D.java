package saxion_programming.misc.collision_testing.collider;

import processing.core.PVector;
import saxion_programming.misc.collision_testing.App;
import saxion_programming.misc.collision_testing.GameObject;

public class BoxCollider2D extends Collider2D {

    public int ColliderColor = 0xff00ff00;

    private GameObject parent;
    private App main;

    public BoxCollider2D () {
    }

    public BoxCollider2D (App main, GameObject parent, float x, float y, float width, float height, float offsetX, float offsetY) {
        this.main = main;
        this.parent = parent;
        Position = new PVector(x, y);
        Offset = new PVector(offsetX, offsetY);
        Size = new PVector(width, height);
    }

    public PVector RespondToCollisionWith (BoxCollider2D other) {
        float overlapX, overlapY;
        if (Position.x < other.Position.x) overlapX = -(Position.x + Size.x - other.Position.x);
        else overlapX = other.Position.x + other.Size.x - Position.x;
        if (Position.y < other.Position.y) overlapY = -(Position.y + Size.y - other.Position.y);
        else overlapY = other.Position.y + other.Size.y - Position.y;
        PVector moveAmount = new PVector(overlapX, overlapY);
        System.out.println(moveAmount);
        if (Math.abs(overlapX) < Math.abs(overlapY)) moveAmount.y = 0;
        else moveAmount.x = 0;
        return moveAmount;
    }

    public boolean CheckCollisionWith (BoxCollider2D other) {
        if (Position.x < other.Position.x + other.Size.x && Position.x + Size.x > other.Position.x && Position.y < other.Position.y + other.Size.y && Position.y + Size.y > other.Position.y) {
            other.ColliderColor = 0xffff0000;
            ColliderColor = 0xffff0000;
            return true;
        }
        other.ColliderColor = 0xff00ff00;
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
