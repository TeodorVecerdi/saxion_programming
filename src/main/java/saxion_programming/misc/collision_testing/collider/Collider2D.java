package saxion_programming.misc.collision_testing.collider;


import processing.core.PVector;
import saxion_programming.misc.collision_testing.GameObject;

public abstract class Collider extends GameObject {
    public enum ColliderType {
        RECT,
        CIRCLE
    }

    public ColliderType colliderType;
    public PVector Size;
    public PVector Offset;
    public boolean enabled = true;
}
