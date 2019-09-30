package saxion_programming.misc.collision_testing.test1_rect_rect.collider;


import processing.core.PVector;
import saxion_programming.misc.collision_testing.test1_rect_rect.GameObject;
import saxion_programming.misc.collision_testing.test1_rect_rect.misc.Constants;

public abstract class Collider2D extends GameObject {
    public ColliderType colliderType;
    public PVector Size;
    public PVector Offset;
    public boolean enabled = true;

    public Collider2D () {enabled = false;}

    public boolean CheckCollisionWith (Collider2D other) {
        if (colliderType == ColliderType.RECT && other.colliderType == ColliderType.RECT)
            return _CheckCollision_RECT_RECT(other);
        if (colliderType == ColliderType.RECT && other.colliderType == ColliderType.CIRCLE) {
            return _CheckCollision_RECT_CIRCLE(other);
        }
        if (colliderType == ColliderType.CIRCLE && other.colliderType == ColliderType.RECT) {
            return other._CheckCollision_RECT_CIRCLE(this);
        }
        if (colliderType == ColliderType.CIRCLE && other.colliderType == ColliderType.CIRCLE)
            return _CheckCollision_CIRCLE_CIRCLE(other);
        return false;
    }

    public PVector RespondToCollision (Collider2D other) {
        if (colliderType == ColliderType.RECT && other.colliderType == ColliderType.RECT)
            return _RespondToCollision_RECT_RECT(other);
        if (colliderType == ColliderType.RECT && other.colliderType == ColliderType.CIRCLE) {
            return _RespondToCollision_RECT_CIRCLE(other);
        }
        if (colliderType == ColliderType.CIRCLE && other.colliderType == ColliderType.RECT) {
            return other._RespondToCollision_RECT_CIRCLE(this);
        }
        if (colliderType == ColliderType.CIRCLE && other.colliderType == ColliderType.CIRCLE)
            return _RespondToCollision_CIRCLE_CIRCLE(other);
        return Constants.VECTOR2F_ZERO;
    }

    private boolean _CheckCollision_RECT_RECT (Collider2D other) {
        return Position.x < other.Position.x + other.Size.x && Position.x + Size.x > other.Position.x && Position.y < other.Position.y + other.Size.y && Position.y + Size.y > other.Position.y;
    }

    private boolean _CheckCollision_CIRCLE_CIRCLE (Collider2D other) {
        float distanceSq = (Position.x - other.Position.x) * (Position.x - other.Position.x) + (Position.y - other.Position.y) * (Position.y - other.Position.y);
        return distanceSq < (Size.x + other.Size.x) * (Size.x + other.Size.x);
    }

    private boolean _CheckCollision_RECT_CIRCLE (Collider2D other) {
        System.err.println("Collision check for RECT-CIRCLE is not implemented");
        return false;
    }

    private PVector _RespondToCollision_RECT_RECT (Collider2D other) {
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

    private PVector _RespondToCollision_CIRCLE_CIRCLE (Collider2D other) {
        System.err.println("Collision response for CIRCLE-CIRCLE is not implemented");
        return Constants.VECTOR2F_ZERO;
    }

    private PVector _RespondToCollision_RECT_CIRCLE (Collider2D other) {
        System.err.println("Collision response for RECT-CIRCLE is not implemented");
        return Constants.VECTOR2F_ZERO;
    }

    public enum ColliderType {
        RECT, CIRCLE
    }
}
