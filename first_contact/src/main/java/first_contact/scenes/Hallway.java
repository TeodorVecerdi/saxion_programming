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



public class Hallway extends Scene{
    private PImage HallwayLitUp, HallwayNotLitUp;
    private PImage Background;
    private __animation[] hallwayAnimation;
    private float currentTimer = 0f;
    private int animationIndex = -1;
    private MouseHotspot bedroomDoorHotspot;
    private MouseHotspot waitingRoomDoorHotspot;

    public Hallway() {
        super();
        var a = Entry.Instance;
        HallwayLitUp = a.loadImage("Hallway/hallwayLitUp.png");
        HallwayNotLitUp = a.loadImage("Hallway/hallwayNotLitUp.png");
        hallwayAnimation = new __animation[]{
                new __animation(4f, HallwayLitUp), new __animation(0.1f, HallwayNotLitUp),
                new __animation(0.4f, HallwayLitUp), new __animation(0.2f, HallwayNotLitUp)};
        Background = HallwayLitUp;
        bedroomDoorHotspot = new MouseHotspot(192,338,214,516, ()-> {
            HotspotClickedThisFrame = true;
            new FloatingText(Messages.GetRandom(Messages.WrongRoom), 1.5f);
        });
        waitingRoomDoorHotspot = new MouseHotspot(1522, 346, 214, 516, ()-> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        currentTimer -= deltaTime;
        if(currentTimer <= 0f) {
            animationIndex ++;
            animationIndex = Math.floorMod(animationIndex, hallwayAnimation.length);
            currentTimer = hallwayAnimation[animationIndex].duration;
            Background = hallwayAnimation[animationIndex].sprite;
        }

        bedroomDoorHotspot.update(deltaTime);
        waitingRoomDoorHotspot.update(deltaTime);

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
        bedroomDoorHotspot.render();
        waitingRoomDoorHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }

    private static class __animation {
        public float duration;
        public PImage sprite;
        public __animation(float d, PImage s) {
            duration = d;
            sprite = s;
        }
    }
}
