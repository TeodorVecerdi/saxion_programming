package teodorvecerdi;

import processing.core.PVector;
import teodorvecerdi.misc.CollisionLayer;
import teodorvecerdi.objects.GameObject;

public class RectCollider2D extends GameObject {
    public int ColliderColor;
    public boolean ShouldRender = true;
    public GameObject parent;
    public int collisionLayer;


    public RectCollider2D(float x, float y, float width, float height, GameObject parent, int collisionLayer) {
        this(x, y, width, height, 0xff00ff00, parent, collisionLayer);
    }

    public RectCollider2D(float x, float y, float width, float height, int color, GameObject parent) {
        this(x, y, width, height, color, parent, CollisionLayer.None);
    }

    public RectCollider2D(float x, float y, float width, float height, GameObject parent) {
        this(x, y, width, height, 0xff00ff00, parent, CollisionLayer.None);
    }

    public RectCollider2D(float x, float y, float width, float height, int color, GameObject parent, int collisionLayer) {
        super();
        renderLayer = parent.renderLayer;
        MainApp.Instance.colliders.add(this);
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        ColliderColor = color;
        this.parent = parent;
        this.collisionLayer |= collisionLayer;
    }

    public boolean checkCollisionWith(RectCollider2D other) {
        return Position.x < other.Position.x + other.Size.x && Position.x + Size.x > other.Position.x && Position.y < other.Position.y + other.Size.y && Position.y + Size.y > other.Position.y;
    }

    @SuppressWarnings("Duplicates")
    public PVector getCollisionResponse(RectCollider2D other) {
        float overlapX, overlapY;
        if (Position.x < other.Position.x) overlapX = -(Position.x + Size.x - other.Position.x);
        else overlapX = other.Position.x + other.Size.x - Position.x;
        if (Position.y < other.Position.y) overlapY = -(Position.y + Size.y - other.Position.y);
        else overlapY = other.Position.y + other.Size.y - Position.y;
        PVector moveAmount = new PVector(overlapX, overlapY);
        if (Math.abs(overlapX) < Math.abs(overlapY)) moveAmount.y = 0;
        else moveAmount.x = 0;
        return moveAmount;
    }

    public void AddCollisionLayer(int layer) {
        collisionLayer |= layer;
    }

    @Override
    public void update() {
        if (parent.ShouldDestroy) {
            ShouldDestroy = true;
            return;
        }
        Position.x = parent.Position.x;
        Position.y = parent.Position.y;
    }

    @Override
    public void render() {
        if (!ShouldRender) return;
        var a = MainApp.Instance;
        a.noFill();
        a.stroke(ColliderColor);
        a.strokeWeight(1f);
        a.rect(Position.x, Position.y, Size.x, Size.y);
    }
}
