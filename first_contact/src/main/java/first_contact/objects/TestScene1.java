package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.Input;

import java.awt.event.KeyEvent;

public class TestScene1 extends Scene {

    private MouseHotspot changeToScene2;

    public TestScene1 () {
        super();
        changeToScene2 = new MouseHotspot(Constants.WIDTH - 105, Constants.HEIGHT / 2 - 50, 100, 100, () -> {
            var a = Entry.Instance;
            a.ActiveScene = "TestScene2";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        changeToScene2.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
//        a.fill(0xffaaaaaa);
//        a.rect(Constants.WIDTH - 105, Constants.HEIGHT / 2 - 50, 100, 100);
        changeToScene2.render();

        a.fill(255, 0, 0);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
