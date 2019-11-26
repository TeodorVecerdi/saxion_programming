package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;


public class Hallway extends Scene {
    private PImage HallwayLitUp, HallwayNotLitUp;
    private PImage Background;
    private __animation[] hallwayAnimation;
    private float currentTimer = 0f;
    private int animationIndex = -1;

    private MouseHotspot bedroomDoorHotspot;
    private MouseHotspot waitingRoomDoorHotspot;

    public Hallway () {
        super();
        var a = Entry.Instance;
        HallwayLitUp = a.Assets.GetSprite("scene/hallwayLitUp");
        HallwayNotLitUp = a.Assets.GetSprite("scene/hallwayNotLitUp");
        hallwayAnimation = new __animation[] {new __animation(4f, HallwayLitUp), new __animation(0.1f, HallwayNotLitUp), new __animation(0.4f, HallwayLitUp), new __animation(0.2f, HallwayNotLitUp)};
        Background = HallwayLitUp;
        bedroomDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(200, 968, 200, 343, 404, 851)).AddCollisionTriangle(new Utils.Triangle(200, 343, 404, 851, 405, 398)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText(Messages.GetRandom(Messages.WrongRoom), 1.5f);
        });
        waitingRoomDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1529, 858, 1529, 392, 1736, 975)).AddCollisionTriangle(new Utils.Triangle(1529, 392, 1738, 975, 1736, 338)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        currentTimer -= deltaTime;
        if (currentTimer <= 0f) {
            animationIndex++;
            animationIndex = Math.floorMod(animationIndex, hallwayAnimation.length);
            currentTimer = hallwayAnimation[animationIndex].duration;
            Background = hallwayAnimation[animationIndex].sprite;
        }

        bedroomDoorHotspot.update(deltaTime);
        waitingRoomDoorHotspot.update(deltaTime);

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
        bedroomDoorHotspot.render();
        waitingRoomDoorHotspot.render();
        //UI
        if (Constants.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

    private static class __animation {
        public float duration;
        public PImage sprite;

        public __animation (float d, PImage s) {
            duration = d;
            sprite = s;
        }
    }
}
