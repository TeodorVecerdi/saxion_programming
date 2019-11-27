package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomDesk extends Scene {

    public MouseHotspot clockIncreaseHotspot;
    public MouseHotspot clockDecreaseHotspot;
    public MouseHotspot drawerHotspot;
    public MouseHotspot backHotspot;

    public PImage Background;
    public PImage BedroomDesk;

    public int clockTime = 12 * 60 + 0;
    private int correctTime = 13 * 60 + 15;

    public BedroomDesk () {
        super();
        var a = Entry.Instance;
        BedroomDesk = a.Assets.GetSprite("scene/bedroomDesk");
        Background = BedroomDesk;

        clockDecreaseHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1411, 484, 1462, 484, 1411, 520)).AddCollisionTriangle(new Utils.Triangle(1462, 484, 1411, 520, 1462, 520)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            clockTime -= 5;
            clockTime = Math.floorMod(clockTime, 1440);
        });
        clockIncreaseHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1475, 484, 1526, 484, 1475, 520)).AddCollisionTriangle(new Utils.Triangle(1526, 484, 1475, 520, 1526, 520)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            clockTime += 5;
            clockTime = Math.floorMod(clockTime, 1440);
        });
        drawerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(977, 743, 1684, 743, 977, 941)).AddCollisionTriangle(new Utils.Triangle(1684, 743, 977, 941, 1684, 941)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("lockpick")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/DrawerUnlocked", true);
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("lockpick"));
            } else if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/DrawerUnlocked") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/GotKey")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("bedroomKey"));
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/GotKey", true);
            } else if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/GotKey")) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        backHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;

        clockIncreaseHotspot.update(deltaTime);
        clockDecreaseHotspot.update(deltaTime);
        drawerHotspot.update(deltaTime);
        backHotspot.update(deltaTime);

        var bedroomClockScene = ((BedroomClock) a.Scenes.get("Bedroom/Clock"));
        if (clockTime == correctTime && bedroomClockScene.clockTime == correctTime && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback")) {
//            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("lockpick"));
            System.out.println("TODO: Add sound feedback for finishing the clock puzzle");
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleFeedback", true);
            new FloatingText("I heard something fall from the wall clock.\nMaybe I should check it out", 3f);
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
        int m = clockTime % 60;
        int h = (clockTime - m) / 60;
        a.textSize(48);
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.text(String.format("%02d:%02d", h, m), 1468, 584);
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        clockIncreaseHotspot.render();
        clockDecreaseHotspot.render();
        drawerHotspot.render();
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
