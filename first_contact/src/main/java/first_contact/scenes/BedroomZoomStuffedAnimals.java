package first_contact.scenes;

import com.jogamp.newt.event.MouseEvent;
import first_contact.Entry;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomZoomStuffedAnimals extends Scene {
    public MouseHotspot backHotspot;
    private PImage Background;

    public BedroomZoomStuffedAnimals() {
        var a = Entry.Instance;
        Background = a.loadImage("Bedroom/stuffedAnimals.png");
        backHotspot = new MouseHotspot(721, 937, 474, 143, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        backHotspot.update(deltaTime);
        if(Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if(!HotspotClickedThisFrame) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            }
        }
        HotspotClickedThisFrame = false;
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        backHotspot.render();
        a.popMatrix();
    }

}
