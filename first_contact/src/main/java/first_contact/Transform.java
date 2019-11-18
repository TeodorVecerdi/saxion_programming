package first_contact;

import processing.core.PVector;

public class Transform {

    public PVector Position;
    public PVector Size;

    public Transform() {
        this(0f, 0f, 0f, 0f);
    }

    public Transform(float x, float y) {
        this(x, y, 0f, 0f);
    }

    public Transform(float x, float y, float width, float height) {
        Position = new PVector(x, y);
        Size = new PVector(width, height);
    }

    public void Move(PVector delta) {
        Move(delta.x, delta.y);
    }

    public void Move(float dx, float dy) {
        Position.add(dx, dy);
    }
}
