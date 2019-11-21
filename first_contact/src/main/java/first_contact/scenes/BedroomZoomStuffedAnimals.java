package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class BedroomZoomStuffedAnimals extends Scene {
    public MouseHotspot backHotspot;
    private PImage Background;

    public BedroomZoomStuffedAnimals() {
        var a = Entry.Instance;
        Background = a.loadImage("Bedroom/stuffedAnimals.png");
        backHotspot = new MouseHotspot(721, 937, 474, 143, () -> {
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        backHotspot.update(deltaTime);
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
