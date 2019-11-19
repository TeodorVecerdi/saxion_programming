package first_contact.objects;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.Input;

import java.awt.event.KeyEvent;

public class TestScene2 extends GameObject {
    public TestScene2 () {
        super();
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        if(Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if(a.mouseX >= 5 && a.mouseX <= 105 && a.mouseY >= 400 && a.mouseY <= 500)
                a.ActiveScene = a.TestScene1;
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.fill(0xffaaaaaa);
        a.rect(5, Constants.HEIGHT/2 - 50, 100, 100);

        a.fill(0,0,255);
        a.textSize(35);
        a.text(Name, 20,30);
        a.textSize(28);
        a.text("Scene", 20,60);
        a.popMatrix();
    }
}
