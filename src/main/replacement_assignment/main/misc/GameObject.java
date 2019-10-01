package main.misc;

import main.MainApp;
import processing.core.PVector;

public abstract class GameObject implements Loopable {
    public PVector Position;
    public PVector Size;

    public GameObject () {
        MainApp.Instance.loopables.add(this);
        Position = new PVector(0f, 0f);
        Size = new PVector(1f, 1f);
    }

    public void Move (PVector delta) {
        Move(delta.x, delta.y);
    }

    public void Move (float dx, float dy) {
        Position.add(dx, dy);
    }

}
