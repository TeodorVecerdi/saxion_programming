package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class DoctorOfficeMain extends Scene {

    public PImage Background;
    public PImage DoctorOfficeHeadClosed, DoctorOfficeHeadOpenKey, DoctorOfficeHeadOpenNoKey;

    public MouseHotspot ComputerHotspot;
    public MouseHotspot PasswordNoteHotspot;
    public MouseHotspot TopDrawerHotspot;
    public MouseHotspot MiddleDrawerHotspot;
    public MouseHotspot BottomDrawerHotspot;
    public MouseHotspot HeadHotspot;
    public MouseHotspot BackHotspot;

    public DoctorOfficeMain () {
        super();
        var a = Entry.Instance;


        HeadHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1324, 411, 1290, 459, 1315, 540)).AddCollisionTriangle(new Utils.Triangle(1324, 411, 1391, 430, 1385, 509)).AddCollisionTriangle(new Utils.Triangle(1385, 509, 1324, 411, 1315, 543)).AddCollisionTriangle(new Utils.Triangle(1315, 542, 1365, 550, 1384, 535)).AddCollisionTriangle(new Utils.Triangle(1384, 536, 1385, 509, 1315, 543)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "DoctorOffice/HeadOverlay";
        });

        ComputerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(546, 394, 521, 564, 726, 557)).AddCollisionTriangle(new Utils.Triangle(727, 559, 723, 399, 546, 394)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "DoctorOffice/ComputerOverlay";
        });

        PasswordNoteHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(822, 555, 786, 557, 792, 596)).AddCollisionTriangle(new Utils.Triangle(791, 597, 829, 594, 821, 555)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "DoctorOffice/PasswordNoteOverlay";
        });
        TopDrawerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(870, 607, 871, 649, 1047, 650)).AddCollisionTriangle(new Utils.Triangle(1047, 607, 1048, 651, 869, 607)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText("There is nothing in this drawer", 1.5f);
        });
        MiddleDrawerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(871, 650, 872, 693, 1047, 694)).AddCollisionTriangle(new Utils.Triangle(1047, 650, 1048, 694, 870, 650)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("officeDrawerKey")) {
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("officeDrawerKey"));
                a.InventoryScene.PlayerInventory.InventoryChecks.put("DoctorOffice/DrawerUnlocked", true);
                a.Assets.GetSound("unlock_key").play();
                new FloatingText("The key fits", 2f);
            } else if (a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/DrawerUnlocked")) {
                a.ActiveScene = "DoctorOffice/DrawerOverlay";
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        BottomDrawerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1047, 695, 871, 694, 872, 790)).AddCollisionTriangle(new Utils.Triangle(1047, 694, 872, 790, 1049, 793)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText("There is nothing in this drawer", 1.5f);
        });
        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(686, 1023, 686, 1079, 1233, 1079)).AddCollisionTriangle(new Utils.Triangle(687, 1023, 1234, 1079, 1234, 1024)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/Finished")) {
                var hallway = ((HallwayMain)a.Scenes.get("Hallway/Main"));
                hallway.Background = hallway.HallwayMax;
                hallway.doctorOfficeDoorHotspot.SetEnabled(false);
            }
            a.ActiveScene = "Hallway/Main";
        });
    }

    @Override public void Load() {
        var a = Entry.Instance;
        DoctorOfficeHeadClosed = a.Assets.GetSprite("scene/officeHeadClosed");
        DoctorOfficeHeadOpenKey = a.Assets.GetSprite("scene/officeHeadOpenKey");
        DoctorOfficeHeadOpenNoKey = a.Assets.GetSprite("scene/officeHeadOpenNoKey");
        Background = DoctorOfficeHeadClosed;
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
        ComputerHotspot.update(deltaTime);
        PasswordNoteHotspot.update(deltaTime);
        TopDrawerHotspot.update(deltaTime);
        MiddleDrawerHotspot.update(deltaTime);
        BottomDrawerHotspot.update(deltaTime);
        HeadHotspot.update(deltaTime);
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

        a.fill(0xffcb3322);
        a.textSize(65);
        a.textAlign(PConstants.CENTER, PConstants.BOTTOM);
        a.text("BACK TO HALLWAY", Globals.WIDTH/2, Globals.HEIGHT);
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }

        ComputerHotspot.render();
        PasswordNoteHotspot.render();
        TopDrawerHotspot.render();
        MiddleDrawerHotspot.render();
        BottomDrawerHotspot.render();
        HeadHotspot.render();
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
