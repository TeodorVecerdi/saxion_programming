package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Input;
import first_contact.misc.Utils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseHotspot extends GameObject {
    public static Boolean ShowMouseHotspots = false;
    public boolean Enabled;
    public List<Runnable> Actions;
    public List<Utils.Triangle> CollisionMesh;

    public MouseHotspot () {
        super();
        this.CollisionMesh = new ArrayList<>();
        this.Actions = new ArrayList<>();
        Enabled = true;
    }

    public MouseHotspot AddAction (Runnable action) {
        Actions.add(action);
        return this;
    }

    public MouseHotspot AddCollisionTriangle (Utils.Triangle triangle) {
        CollisionMesh.add(triangle);
        return this;
    }

    @Override
    public void update (float deltaTime) {
        if (Enabled && Input.GetButtonDown(KeyEvent.VK_LEFT) && IsMouseInside()) Actions.forEach(Runnable::run);
    }

    public boolean IsMouseInside () {
        boolean collides = CollisionMesh.stream().anyMatch(triangle -> Utils.PointInTriangle(Input.MousePosition, triangle));
        return collides;
    }

    /*public boolean IsMouseInside () {
        return Input.MouseX >= x && Input.MouseX <= x + w && Input.MouseY >= y && Input.MouseY <= y + h;
    }*/

    @Override
    public void render () {
        if (!ShowMouseHotspots || !Enabled) return;
        var a = Entry.Instance;
        a.pushMatrix();
        a.fill(0xaa00ff00);
        a.stroke(0x00);
        //        a.noStroke();
        CollisionMesh.forEach(t -> {
            a.triangle(t.v1.x, t.v1.y, t.v2.x, t.v2.y, t.v3.x, t.v3.y);
        });
        a.popMatrix();
    }

    public void SetEnabled (boolean enabled) {
        Enabled = enabled;
    }
}
