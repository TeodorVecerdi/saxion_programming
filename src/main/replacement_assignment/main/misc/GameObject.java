package main.misc;

import main.MainApp;
import main.Transform;
import processing.core.PVector;

import java.util.concurrent.TimeUnit;

public abstract class GameObject implements Loopable {
    public Transform Transform;
    public PVector Position;
    public PVector Size;
    public boolean ShouldDestroy;
    public String Tag;

    public GameObject () {
        MainApp.Instance.gameObjects.add(this);
        Transform = new Transform();
        Position = Transform.Position;
        Size = Transform.Size;
        ShouldDestroy = false;
        Tag = "Untagged";
    }

    public void DestroyAfterMillis(long millis) {
        MainApp.Instance.scheduler.schedule(() -> {
            ShouldDestroy = true;
        }, millis, TimeUnit.MILLISECONDS);
    }
}
