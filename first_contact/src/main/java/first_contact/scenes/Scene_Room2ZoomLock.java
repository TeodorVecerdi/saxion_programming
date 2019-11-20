package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class Scene_Room2ZoomLock extends Scene {

    private MouseHotspot backHotspot;
    private MouseHotspot debugWinHotspot;

    private PImage Background;

    public Scene_Room2ZoomLock () {
        super();
        var a = Entry.Instance;

        Background = a.loadImage("Room2/lockZoom.png");
        backHotspot = new MouseHotspot(660, 890, 550, 190, () -> {
            a.ActiveScene = "Room2/Main";
        });
        debugWinHotspot = new MouseHotspot(750, 0, 385, 350, () -> {
            System.out.println("YES YES YOU WON!!!!!!!");
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        backHotspot.update(deltaTime);
        debugWinHotspot.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        backHotspot.render();
        debugWinHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
