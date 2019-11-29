package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomSignOverlay extends Scene {

    public PImage Background;
    public MouseHotspot BackHotspot;

    public WaitingRoomSignOverlay() {
        super();
        var a = Entry.Instance;
        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
    }
    @Override public void Load() {
        var a = Entry.Instance;
        Background = a.Assets.GetSprite("scene/wrSignOverlay");
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        if(FirstLoad) {
            Load();
            FirstLoad = false;
        }
        if(ShouldFade && FadeInTimeLeft >= 0) {
            FadeInTimeLeft -= deltaTime;
        }
        if(ShouldFade && FadeInTimeLeft <= 0) {
            ShouldFade = false;
        }
        BackHotspot.update(deltaTime);

        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if (!Scene.HotspotClickedThisFrame) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            }
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }
        BackHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
