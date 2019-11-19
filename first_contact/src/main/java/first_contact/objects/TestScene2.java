package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.Input;

import java.awt.event.KeyEvent;

public class TestScene2 extends Scene {

    private MouseHotspot changeToScene1;

    public TestScene2 () {
        super();
        changeToScene1 = new MouseHotspot(5, Constants.HEIGHT/2 - 50, 100, 100, () -> {
           var a = Entry.Instance;
           a.ActiveScene = "TestScene1";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        changeToScene1.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
//        a.fill(0xffaaaaaa);
//        a.rect(5, Constants.HEIGHT/2 - 50, 100, 100);
        changeToScene1.render();

        a.fill(0,0,255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20,30);
        a.popMatrix();
    }
}
