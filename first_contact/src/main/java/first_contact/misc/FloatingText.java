package first_contact.misc;

import first_contact.Entry;
import first_contact.objects.GameObject;

import java.util.UUID;

public class FloatingText extends GameObject {
    public boolean done;
    private int x;
    private int y;
    private String text;
    private float timeLeft;
    private float duration;
    private UUID uid;

    public FloatingText (String text, float duration) {
        this(Input.MouseX, Input.MouseY, text, duration);
    }

    public FloatingText (int x, int y, String text, float duration) {
        super();
        this.x = x;
        this.y = y;
        this.text = text;
        timeLeft = duration;
        this.duration = duration;
        uid = UUID.randomUUID();
        Entry.Instance.FloatingTexts.put(uid, this);
        done = false;
    }

    @Override
    public void update (float deltaTime) {
        if (done) return;

        timeLeft -= deltaTime;
        if (timeLeft <= 0) {
            //            Entry.Instance.FloatingTexts.remove(uid);
            done = true;
        }
    }

    @Override
    public void render () {
        if (done) return;
        float scale = Utils.Map(timeLeft, 0f, duration, 0.7f, 1f);
        float opacity = Utils.Map(timeLeft, 0f, duration, 0f, 255f);
        var a = Entry.Instance;
        a.pushMatrix();
        a.translate(x, y);
        a.scale(scale);
        a.translate(-x, -y);

        a.fill(0x44, opacity);
        a.textSize(24);
        a.text(text, x, y);
        a.popMatrix();
    }
}
