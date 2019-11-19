package first_contact.objects;

import first_contact.misc.Loopable;
import first_contact.misc.Transform;
import processing.core.PVector;

public abstract class GameObject implements Loopable {
    public String Tag;
    public String Name;
    public PVector Position;
    public PVector Size;
    public Transform Transform;

    public GameObject () {
        Transform = new Transform();
        Position = Transform.Position;
        Size = Transform.Size;
        Tag = "Untagged";
        Name = String.format("%s-%s", getClass().getSimpleName(), hashCode());
    }

    @Override public String toString () {
        return Name;
    }
}
