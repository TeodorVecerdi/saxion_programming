package main.misc;

import main.MainApp;
import main.Transform;
import processing.core.PVector;

public abstract class GameObject implements Loopable {
    public Transform Transform;
    public PVector Position;
    public PVector Size;
    public GameObject () {
        MainApp.Instance.loopables.add(this);
        Transform = new Transform();
        Position = Transform.Position;
        Size = Transform.Size;
    }
}
