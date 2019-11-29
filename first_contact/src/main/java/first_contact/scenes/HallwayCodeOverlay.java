package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class HallwayCodeOverlay extends Scene {

    public PImage Background;
    public PImage HallwayCodeLocked, HallwayCodeUnlocked;

    public MouseHotspot B0, B1, B2, B3, B4, B5, B6, B7, B8, B9;
    public MouseHotspot EnterHotspot;
    public MouseHotspot BackHotspot;

    public String CorrectCode = "9271";
    public String CurrentCode = "";

    public HallwayCodeOverlay () {
        super();
        var a = Entry.Instance;


        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Hallway/Main";
        });
        B0 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(923, 587, 926, 643, 1007, 642)).AddCollisionTriangle(new Utils.Triangle(921, 586, 1005, 642, 1003, 584)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("0");
        });
        B1 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(807, 290, 808, 346, 890, 349)).AddCollisionTriangle(new Utils.Triangle(807, 289, 889, 348, 887, 288)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("1");
        });
        B2 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(920, 288, 923, 347, 1004, 346)).AddCollisionTriangle(new Utils.Triangle(1000, 287, 1001, 345, 917, 287)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("2");
        });
        B3 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1032, 285, 1036, 346, 1117, 344)).AddCollisionTriangle(new Utils.Triangle(1031, 285, 1117, 345, 1115, 285)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("3");
        });
        B4 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(807, 383, 810, 446, 891, 446)).AddCollisionTriangle(new Utils.Triangle(806, 383, 891, 447, 890, 382)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("4");
        });
        B5 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(919, 385, 922, 445, 1005, 444)).AddCollisionTriangle(new Utils.Triangle(918, 385, 1004, 444, 1002, 384)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("5");
        });
        B6 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1036, 385, 1038, 442, 1119, 444)).AddCollisionTriangle(new Utils.Triangle(1036, 384, 1117, 443, 1116, 383)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("6");
        });
        B7 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(809, 489, 810, 548, 893, 548)).AddCollisionTriangle(new Utils.Triangle(809, 488, 892, 549, 890, 488)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("7");
        });
        B8 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(924, 488, 928, 546, 1008, 547)).AddCollisionTriangle(new Utils.Triangle(925, 487, 1006, 546, 1004, 488)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("8");
        });
        B9 = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1039, 487, 1041, 543, 1119, 546)).AddCollisionTriangle(new Utils.Triangle(1040, 488, 1117, 545, 1116, 488)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            AddAndShortenCode("9");
        });
        EnterHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1039, 585, 1043, 643, 1122, 642)).AddCollisionTriangle(new Utils.Triangle(1040, 585, 1120, 642, 1119, 582)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (CurrentCode.equals(CorrectCode)) {
                a.Assets.GetSound("unlock_main_door").play(1, 0, 0.3f);
                a.Scheduler.schedule(() -> {
                    Background = HallwayCodeUnlocked;
                    new FloatingText("Finally! I can escape now!", 4f);
                    ((HallwayMain)a.Scenes.get("Hallway/Main")).EscapeHotspot.SetEnabled(true);
                    DisableButtons();
                }, 1, TimeUnit.SECONDS);

            } else {
                CurrentCode = "";
                a.Assets.GetSound("unlock_fail").play();
            }
        });
    }

    @Override public void Load() {
        var a = Entry.Instance;
        HallwayCodeLocked = a.Assets.GetSprite("scene/hallwayCodeLockedOverlay");
        HallwayCodeUnlocked = a.Assets.GetSprite("scene/hallwayCodeUnlockedOverlay");
        Background = HallwayCodeLocked;
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
        B0.update(deltaTime);
        B1.update(deltaTime);
        B2.update(deltaTime);
        B3.update(deltaTime);
        B4.update(deltaTime);
        B5.update(deltaTime);
        B6.update(deltaTime);
        B7.update(deltaTime);
        B8.update(deltaTime);
        B9.update(deltaTime);
        EnterHotspot.update(deltaTime);
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
        B0.render();
        B1.render();
        B2.render();
        B3.render();
        B4.render();
        B5.render();
        B6.render();
        B7.render();
        B8.render();
        B9.render();
        EnterHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

    private void AddAndShortenCode(String s) {
        Entry.Instance.Assets.GetSound("button_beep").play();
        CurrentCode += s;
        if(CurrentCode.length() > 4)
            CurrentCode = CurrentCode.substring(CurrentCode.length()-4);
    }
    
    private void DisableButtons() {
        B0.SetEnabled(false);
        B1.SetEnabled(false);
        B2.SetEnabled(false);
        B3.SetEnabled(false);
        B4.SetEnabled(false);
        B5.SetEnabled(false);
        B6.SetEnabled(false);
        B7.SetEnabled(false);
        B8.SetEnabled(false);
        B9.SetEnabled(false);
        EnterHotspot.SetEnabled(false);
    }
}
