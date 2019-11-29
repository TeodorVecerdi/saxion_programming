package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;


public class HallwayMain extends Scene {
    //    private PImage HallwayLitUp, HallwayNotLitUp;
    public PImage Background;
    public PImage HallwayMin, HallwayMed, HallwayMax;
    //    private __animation[] hallwayAnimation;
    //    private float currentTimer = 0f;
    //    private int animationIndex = -1;

    public MouseHotspot bedroomDoorHotspot;
    public MouseHotspot bedroom2DoorHotspot;
    public MouseHotspot waitingRoomDoorHotspot;
    public MouseHotspot doctorOfficeDoorHotspot;
    public MouseHotspot codeHotspot;
    public MouseHotspot EscapeHotspot;

    public HallwayMain () {
        super();
        var a = Entry.Instance;

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
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotCrowbar")) {
                new FloatingText(Messages.GetRandom(Messages.WrongRoom), 1.5f);
            } else {
                a.ActiveScene = "WaitingRoom/Main";
                a.AmbientSong = "ambient_med";
                a.ChangeAmbientSong = true;
            }
        });
        doctorOfficeDoorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(555, 431, 555, 763, 663, 703)).AddCollisionTriangle(new Utils.Triangle(555, 432, 662, 703, 663, 457)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if(!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotCrowbar")) {
                new FloatingText(Messages.GetRandom(Messages.DoorLocked) + "\n" + Messages.GetRandom(Messages.NoItem), 1.5f);
            }else if(a.InventoryScene.PlayerInventory.InventoryChecks.get("DoctorOffice/Finished")) {
                new FloatingText(Messages.GetRandom(Messages.WrongRoom), 1.5f);
            } else if(a.InventoryScene.PlayerInventory.InventoryChecks.get("Hallway/UnlockedDoctorOffice")) {
                a.AmbientSong = "ambient_max";
                a.ChangeAmbientSong = true;
                a.ActiveScene = "DoctorOffice/Main";
            } else if(a.InventoryScene.PlayerInventory.SelectedItem != -1 &&
                    a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("crowbar")){
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("crowbar"));
                a.Assets.GetSound("open_chest").play(1, 0, 1f);
                new FloatingText("I can now access the office! I can finally escape", 4f);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Hallway/UnlockedDoctorOffice", true);
            } else if(a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        codeHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(741, 522, 743, 578, 778, 571)).AddCollisionTriangle(new Utils.Triangle(740, 522, 775, 522, 778, 572)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Hallway/CodeOverlay";
        });

        EscapeHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(793, 447, 1125, 447, 1127, 631)).AddCollisionTriangle(new Utils.Triangle(793, 447, 792, 634, 1127, 631)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            new FloatingText("No... They're catching up to me.\nI don't want to go back there.", 8f);
            a.ActiveScene = "Ending";
        });
        EscapeHotspot.SetEnabled(false);
    }

    @Override public void Load() {
        var a = Entry.Instance;
        HallwayMin = a.Assets.GetSprite("scene/hallwayMin");
        HallwayMed = a.Assets.GetSprite("scene/hallwayMed");
        HallwayMax = a.Assets.GetSprite("scene/hallwayMax");
        Background = HallwayMin;
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
        bedroomDoorHotspot.update(deltaTime);
        bedroom2DoorHotspot.update(deltaTime);
        waitingRoomDoorHotspot.update(deltaTime);
        doctorOfficeDoorHotspot.update(deltaTime);
        codeHotspot.update(deltaTime);
        EscapeHotspot.update(deltaTime);

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
        bedroomDoorHotspot.render();
        bedroom2DoorHotspot.render();
        waitingRoomDoorHotspot.render();
        doctorOfficeDoorHotspot.render();
        codeHotspot.render();
        EscapeHotspot.render();
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
