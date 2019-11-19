package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.Input;

import java.awt.event.KeyEvent;

public class TestScene1 extends GameObject {
    public TestScene1 () {
        super();
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if (a.mouseX >= 1495 && a.mouseX <= 1595 && a.mouseY >= 400 && a.mouseY <= 500)
                a.ActiveScene = a.TestScene2;
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.fill(0xffaaaaaa);
        a.rect(Constants.WIDTH - 105, Constants.HEIGHT / 2 - 50, 100, 100);

        a.fill(255, 0, 0);
        a.textSize(35);
        a.text(Name, 20, 30);
        a.textSize(28);
        a.text("Scene", 20, 60);

        a.popMatrix();
    }
}
