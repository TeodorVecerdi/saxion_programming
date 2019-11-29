package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class Prologue extends Scene {
    public PImage Background;
    public PImage ProloguePills, PrologueMonologue;
    public boolean ShouldEnd = false;

    public Prologue() {
        super();
        var a = Entry.Instance;
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
        if(Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if(ShouldEnd) {
                a.ActiveScene = "Bedroom/Main";
                Entry.Instance.GameStarted = true;
                a.ChangeAmbientSong = true;
            } else {
                ShouldEnd = true;
                Background = PrologueMonologue;
            }
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        a.fill(0xff);
        a.textSize(32);
        a.textAlign(PConstants.RIGHT, PConstants.BOTTOM);
        a.text("CLICK ANYWHERE TO CONTINUE", Globals.WIDTH - 50, Globals.HEIGHT - 50);
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

    @Override
    public void Load () {
        var a = Entry.Instance;
        ProloguePills = a.Assets.GetSprite("scene/prologuePills");
        PrologueMonologue = a.Assets.GetSprite("scene/prologueMonologue");
        Background = ProloguePills;
    }
}
