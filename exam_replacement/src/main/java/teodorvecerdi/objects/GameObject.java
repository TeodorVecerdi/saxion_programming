package teodorvecerdi.objects;

import processing.core.PVector;
import teodorvecerdi.MainApp;
import teodorvecerdi.Transform;
import teodorvecerdi.misc.Loopable;
import teodorvecerdi.misc.RenderLayer;

import java.util.concurrent.TimeUnit;

public abstract class GameObject implements Loopable {
    public Transform Transform;
    public PVector Position;
    public PVector Size;
    public boolean ShouldDestroy;
    public String Tag;
    public String Name;
    public int renderLayer = RenderLayer.Default;


    public GameObject() {
        MainApp.Instance.gameObjectsToAdd.add(this);
        Transform = new Transform();
        Position = Transform.Position;
        Size = Transform.Size;
        ShouldDestroy = false;
        Tag = "Untagged";
        Name = String.format("[%s %s]", getClass().getSimpleName(), hashCode());
    }

    public void DestroyAfterMillis(long millis) {
        if (millis == 0) {
            ShouldDestroy = true;
            return;
        }
        MainApp.Instance.scheduler.schedule(() -> {
            ShouldDestroy = true;
        }, millis, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString () {
        return Name;
    }
}
