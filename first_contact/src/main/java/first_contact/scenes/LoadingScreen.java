package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.objects.Scene;
import processing.core.PConstants;

public class LoadingScreen extends Scene {
    public float loadAmount = 0f;
    public boolean loading = true;

    public LoadingScreen () {
        super();
    }

    @Override
    public void update (float deltaTime) {

    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.noStroke();

        a.fill(0x22);
        a.rect(200, Constants.HEIGHT / 2, Constants.WIDTH - 200, 60);
        a.fill(0xff);
        a.rect(202, Constants.HEIGHT / 2 + 2, (Constants.WIDTH - 204)*loadAmount, 56);
        a.popMatrix();
    }
}
