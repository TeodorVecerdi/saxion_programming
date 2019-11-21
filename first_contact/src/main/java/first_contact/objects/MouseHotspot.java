package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Input;

import java.awt.event.KeyEvent;

public class MouseHotspot extends GameObject {
    public static Boolean ShowMouseHotspots = true;
    public int x;
    public int y;
    public int w;
    public int h;
    public Runnable Action;
    public boolean Enabled;


    public MouseHotspot () {
        this(0, 0, 0, 0, () -> {
            System.out.println("Hotspot clicked");
        });
    }

    public MouseHotspot (int x, int y, int w, int h) {
        this(x, y, w, h, () -> {
            System.out.println("Hotspot clicked");
        });
    }

    public MouseHotspot (int x, int y, int w, int h, Runnable action) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        Action = action;
        Enabled = true;
    }

    @Override
    public void update (float deltaTime) {
        if (!Enabled) return;
        if (Input.GetButtonDown(KeyEvent.VK_LEFT) && IsMouseInside()) Action.run();
    }

    @Override
    public void render () {
        if(!ShowMouseHotspots || !Enabled) return;
        var a = Entry.Instance;
        a.fill(0xaa00ff00);
        a.noStroke();
        a.rect(x, y, w, h);
    }

    public boolean IsMouseInside () {
        return Input.MouseX >= x && Input.MouseX <= x + w && Input.MouseY >= y && Input.MouseY <= y + h;
    }

    public void SetEnabled(boolean enabled) {
        Enabled = enabled;
    }

}
