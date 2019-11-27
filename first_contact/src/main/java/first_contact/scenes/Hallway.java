package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;


public class Hallway extends Scene {
//    private PImage HallwayLitUp, HallwayNotLitUp;
    private PImage Hallway;
    private PImage Background;
//    private __animation[] hallwayAnimation;
//    private float currentTimer = 0f;
//    private int animationIndex = -1;

    private MouseHotspot bedroomDoorHotspot;
    private MouseHotspot bedroom2DoorHotspot;
    private MouseHotspot waitingRoomDoorHotspot;
    private MouseHotspot doctorOfficeDoorHotspot;

    public Hallway () {
        super();
        var a = Entry.Instance;
        Hallway = a.Assets.GetSprite("scene/hallway");
//        HallwayLitUp = a.Assets.GetSprite("scene/hallwayLitUp");
//        HallwayNotLitUp = a.Assets.GetSprite("scene/hallwayNotLitUp");
//        hallwayAnimation = new __animation[] {new __animation(4f, HallwayLitUp), new __animation(0.1f, HallwayNotLitUp), new __animation(0.4f, HallwayLitUp), new __animation(0.2f, HallwayNotLitUp)};
        Background = Hallway;
        bedroomDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(200, 349, 200, 964, 401, 852)).AddCollisionTriangle(new Utils.Triangle(402, 401, 200, 352, 400, 853)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText(Messages.GetRandom(Messages.WrongRoom), 1.5f);
        });
        bedroom2DoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1528, 398, 1736, 342, 1734, 969)).AddCollisionTriangle(new Utils.Triangle(1528, 397, 1528, 857, 1734, 966)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText(Messages.GetRandom(Messages.DoorLocked), 1.5f);
        });
        waitingRoomDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1251, 701, 1251, 450, 1356, 758)).AddCollisionTriangle(new Utils.Triangle(1250, 450, 1357, 761, 1356, 420)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
        doctorOfficeDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(555, 431, 555, 763, 663, 703)).AddCollisionTriangle(new Utils.Triangle(555, 432, 662, 703, 663, 457)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText(Messages.GetRandom(Messages.DoorLocked)+"\n"+Messages.GetRandom(Messages.NoItem), 1.5f);
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        /*currentTimer -= deltaTime;
        if (currentTimer <= 0f) {
            animationIndex++;
            animationIndex = Math.floorMod(animationIndex, hallwayAnimation.length);
            currentTimer = hallwayAnimation[animationIndex].duration;
            Background = hallwayAnimation[animationIndex].sprite;
        }*/

        bedroomDoorHotspot.update(deltaTime);
        bedroom2DoorHotspot.update(deltaTime);
        waitingRoomDoorHotspot.update(deltaTime);
        doctorOfficeDoorHotspot.update(deltaTime);

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
        bedroom2DoorHotspot.render();
        waitingRoomDoorHotspot.render();
        doctorOfficeDoorHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
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
