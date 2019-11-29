package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class DoctorOfficeHeadOverlay extends Scene {

    public PImage Background;
    public PImage OfficeHeadClosed, OfficeHeadOpenKey, OfficeHeadOpenNoKey;
    public MouseHotspot BackHotspot;
    public MouseHotspot HeadHotspot;
    public MouseHotspot KeyHotspot;

    public DoctorOfficeHeadOverlay () {
        super();
        var a = Entry.Instance;
        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "DoctorOffice/Main";
        });
        HeadHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(885, 220, 689, 532, 842, 924)).AddCollisionTriangle(new Utils.Triangle(884, 221, 1177, 378, 873, 412)).AddCollisionTriangle(new Utils.Triangle(1175, 378, 1173, 780, 874, 412)).AddCollisionTriangle(new Utils.Triangle(1173, 779, 842, 922, 874, 413)).AddCollisionTriangle(new Utils.Triangle(1172, 779, 1150, 926, 843, 922)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/HeadOpened")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("DoctorOffice/HeadOpened", true);
                var main = (DoctorOfficeMain) (a.Scenes.get("DoctorOffice/Main"));
                main.Background = main.DoctorOfficeHeadOpenKey;
                Background = OfficeHeadOpenKey;
                HeadHotspot.SetEnabled(false);
                KeyHotspot.SetEnabled(true);
            }
        });
        KeyHotspot = new MouseHotspot()
                .AddCollisionTriangle(new Utils.Triangle(905, 311, 936, 390, 1001, 378))
                .AddCollisionTriangle(new Utils.Triangle(1032, 244, 1001, 378, 904, 312))
                .AddCollisionTriangle(new Utils.Triangle(1034, 244, 943, 223, 904, 312))
                .AddAction(() -> {
                    Scene.HotspotClickedThisFrame = true;
                    if(!a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/GotHeadKey") && a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/HeadOpened")) {
                        a.InventoryScene.PlayerInventory.InventoryChecks.put("DoctorOffice/GotHeadKey", true);
                        a.Assets.GetSound("pickup_keys").play();
                        a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("officeDrawerKey"));
                        new FloatingText("I found a key. I wonder what it unlocks", 2.5f);
                        var main = (DoctorOfficeMain) (a.Scenes.get("DoctorOffice/Main"));
                        main.Background = main.DoctorOfficeHeadOpenNoKey;
                        Background = OfficeHeadOpenNoKey;
                        KeyHotspot.SetEnabled(false);
                    }
                });
        KeyHotspot.SetEnabled(false);
    }

    @Override public void Load() {
        var a = Entry.Instance;
        OfficeHeadClosed = a.Assets.GetSprite("scene/officeHeadClosedOverlay");
        OfficeHeadOpenKey = a.Assets.GetSprite("scene/officeHeadOpenKeyOverlay");
        OfficeHeadOpenNoKey = a.Assets.GetSprite("scene/officeHeadOpenNoKeyOverlay");
        Background = OfficeHeadClosed;
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
        HeadHotspot.update(deltaTime);
        KeyHotspot.update(deltaTime);
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
        HeadHotspot.render();
        KeyHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
