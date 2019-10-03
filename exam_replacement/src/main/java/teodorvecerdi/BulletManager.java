package teodorvecerdi;

import teodorvecerdi.misc.RenderLayer;
import teodorvecerdi.objects.Bullet;
import teodorvecerdi.objects.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

public class BulletManager extends GameObject {
    private ArrayList<Bullet> bullets;

    public BulletManager() {
        super();
        renderLayer = RenderLayer.UI;
        bullets = new ArrayList<>();
    }

    public void update() {
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.update();
            if (b.ShouldDestroy) {
                it.remove();
            }
        }
    }

    public void render() {
        MainApp.Instance.fill(0xff);
        MainApp.Instance.textSize(20f);
        MainApp.Instance.text("Active bullets: " + bullets.size(), 25, 25);
    }

    public void fire(float x, float y, float rotation, float speed) {
        bullets.add(new Bullet(x, y, rotation, speed));
    }
}
