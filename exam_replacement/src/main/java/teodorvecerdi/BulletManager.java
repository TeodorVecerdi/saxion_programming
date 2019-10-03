package teodorvecerdi;

import java.util.ArrayList;
import java.util.Iterator;

import teodorvecerdi.misc.Loopable;
import teodorvecerdi.objects.Bullet;

public class BulletManager implements Loopable {
    ArrayList<Bullet> bulletL;

    public BulletManager() {
        bulletL = new ArrayList<>();
    }

    public void update() {
        Iterator<Bullet> it = bulletL.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.update();
            if (b.ShouldDestroy) {
                it.remove();
            }
        }
    }

    public void render() {
        MainApp.Instance.fill(0, 255, 0);
        MainApp.Instance.text("Nb bullets: " + bulletL.size(), 0, 0);
    }

    public void fire(float x, float y, float rotation, float speed) {
        bulletL.add(new Bullet(x, y, rotation, speed));
    }
}
