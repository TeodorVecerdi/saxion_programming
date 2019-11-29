package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomClock extends Scene {

    public MouseHotspot backHotspot;
    public MouseHotspot clockIncreaseHotspot, clockDecreaseHotspot;
    public MouseHotspot clockLockpickHotspot;
    public PImage Background;
    public PImage BedroomClock, BedroomClockFallen, BedroomClockEmpty;

    public int clockTime = 11 * 60 + 35;
    private int correctTime = 13 * 60 + 15;

    public BedroomClock () {
        super();
        var a = Entry.Instance;
        clockIncreaseHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1591, 426, 1589, 646, 1845, 647)).AddCollisionTriangle(new Utils.Triangle(1846, 647, 1589, 427, 1851, 428)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            clockTime += 5;
            clockTime = Math.floorMod(clockTime, 1440);
        });
        clockDecreaseHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(110, 516, 250, 517, 254, 621)).AddCollisionTriangle(new Utils.Triangle(109, 516, 111, 622, 257, 623)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            clockTime -= 5;
            clockTime = Math.floorMod(clockTime, 1440);
        });
        clockLockpickHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(847, 669, 634, 909, 735, 947)).AddCollisionTriangle(new Utils.Triangle(848, 668, 1364, 832, 1224, 931)).AddCollisionTriangle(new Utils.Triangle(1224, 930, 991, 869, 846, 668)).AddCollisionTriangle(new Utils.Triangle(846, 668, 991, 869, 735, 947)).AddAction(() -> {
            if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleDone")) {
                Scene.HotspotClickedThisFrame = true;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleDone", true);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("lockpick"));
                Background = BedroomClockEmpty;
                a.Assets.GetSound("pickup_keys").play();
            }
        });

        backHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(747, 940, 746, 1079, 1194, 1079)).AddCollisionTriangle(new Utils.Triangle(1191, 1079, 749, 940, 1190, 937)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void Load () {
        var a = Entry.Instance;
        BedroomClock = a.Assets.GetSprite("scene/bedroomClock");
        BedroomClockFallen = a.Assets.GetSprite("scene/bedroomClockFallen");
        BedroomClockEmpty = a.Assets.GetSprite("scene/bedroomClockEmpty");
        Background = BedroomClock;
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        if (FirstLoad) {
            Load();
            FirstLoad = false;
        }
        if(ShouldFade && FadeInTimeLeft >= 0) {
            FadeInTimeLeft -= deltaTime;
        }
        if(ShouldFade && FadeInTimeLeft <= 0) {
            ShouldFade = false;
        }
        clockIncreaseHotspot.update(deltaTime);
        clockDecreaseHotspot.update(deltaTime);
        clockLockpickHotspot.update(deltaTime);
        backHotspot.update(deltaTime);

        var bedroomDeskScene = ((BedroomDesk) a.Scenes.get("Bedroom/Desk"));
        if (bedroomDeskScene.clockTime == correctTime && clockTime == correctTime && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback")) {
            a.Assets.GetSound("open_chest").play();
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleFeedback", true);
            clockIncreaseHotspot.SetEnabled(false);
            clockDecreaseHotspot.SetEnabled(false);
            bedroomDeskScene.clockIncreaseHotspot.SetEnabled(false);
            bedroomDeskScene.clockDecreaseHotspot.SetEnabled(false);
            Background = BedroomClockFallen;
            new FloatingText("The hands of the clock fell.\nThese might be useful later. I should pick them up", 3f);
        }

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

        if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback")) {
            int m = clockTime % 60;
            int h = (clockTime - m) / 60;
            int hourAngle = h * 30 - 90;
            int minAngle = m * 6 - 90;

            float hourPositionX = (float) (250f * Math.cos(Math.toRadians(hourAngle)));
            float hourPositionY = (float) (250f * Math.sin(Math.toRadians(hourAngle)));
            float minPositionX = (float) (450f * Math.cos(Math.toRadians(minAngle)));
            float minPositionY = (float) (450f * Math.sin(Math.toRadians(minAngle)));
            a.stroke(0x00);
            a.strokeWeight(20);
            a.line(Globals.WIDTH / 2f, Globals.HEIGHT / 2f, Globals.WIDTH / 2f + hourPositionX, Globals.HEIGHT / 2f + hourPositionY);
            a.strokeWeight(15);
            a.line(Globals.WIDTH / 2f, Globals.HEIGHT / 2f, Globals.WIDTH / 2f + minPositionX, Globals.HEIGHT / 2f + minPositionY);
        }

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }

        clockIncreaseHotspot.render();
        clockDecreaseHotspot.render();
        clockLockpickHotspot.render();
        backHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }
        a.popMatrix();
    }
}
