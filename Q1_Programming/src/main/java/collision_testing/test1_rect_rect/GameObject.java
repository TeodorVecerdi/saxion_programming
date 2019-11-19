package collision_testing.test1_rect_rect;

import processing.core.PVector;

public abstract class GameObject {
    public PVector Position = new PVector(0f, 0f);
    public PVector Scale = new PVector(1f, 1f);

    public abstract void update ();

    public abstract void render ();
}
