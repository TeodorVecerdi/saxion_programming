package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Globals;
import first_contact.misc.Utils;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class Start extends Scene {

    public PImage Background;
    public MouseHotspot StartHotspot;

    public Start() {
        super();
        var a = Entry.Instance;
        Background = a.loadImage("startScreen.png");;
        StartHotspot = new MouseHotspot()
                .AddCollisionTriangle(new Utils.Triangle(698, 470, 698, 608, 1221, 608))
                .AddCollisionTriangle(new Utils.Triangle(695, 470, 1217, 608, 1219, 475))
                .AddAction(() -> {
                   a.ActiveScene = "Loading";
                   Loading.StartLoading = true;
                });
    }

    @Override
    public void Load() {}

    @Override
    public void update (float deltaTime) {
        StartHotspot.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);

        StartHotspot.render();
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
