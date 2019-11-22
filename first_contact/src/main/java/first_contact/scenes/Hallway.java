package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.Scene;
import processing.core.PImage;

class __animation {
    public float duration;
    public PImage sprite;
    public __animation(float d, PImage s) {
        duration = d;
        sprite = s;
    }
}

public class Hallway extends Scene {

    private PImage HallwayLitUp, HallwayNotLitUp;
    private PImage Background;
    private __animation[] hallwayAnimation;
    private float currentTimer = 0f;
    private int animationIndex = -1;

    public Hallway() {
        super();
        var a = Entry.Instance;
        HallwayLitUp = a.loadImage("Hallway/hallwayLitUp.png");
        HallwayNotLitUp = a.loadImage("Hallway/hallwayNotLitUp.png");
        hallwayAnimation = new __animation[]{new __animation(1f, HallwayLitUp), new __animation(0.2f, HallwayNotLitUp), new __animation(0.2f, HallwayLitUp), new __animation(0.2f, HallwayNotLitUp)};
        Background = HallwayLitUp;
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
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
