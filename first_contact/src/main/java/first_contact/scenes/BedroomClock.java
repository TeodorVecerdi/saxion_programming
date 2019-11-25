package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomClock extends Scene {

    public MouseHotspot backHotspot;
    public MouseHotspot clockHotspot;
    public PImage Background;
    public PImage BedroomClock;

    public int clockTime = 11 * 60 + 35;
    private int correctTime = 13 * 60 + 15;

    public BedroomClock () {
        var a = Entry.Instance;

        BedroomClock = a.Assets.GetSprite("scene/bedroomClock");
        Background = BedroomClock;

        clockHotspot = new MouseHotspot(810, 390, 300, 300, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleDone")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleDone", true);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("lockpick"));
            } else {
                clockTime += 5;
                clockTime = Math.floorMod(clockTime, 1440);
            }
        });
        backHotspot = new MouseHotspot(0, 941, 1920, 139, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;

        clockHotspot.update(deltaTime);
        backHotspot.update(deltaTime);

        var bedroomDeskScene = ((BedroomDesk) a.Scenes.get("Bedroom/Desk"));
        if (bedroomDeskScene.clockTime == correctTime && clockTime == correctTime && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleFeedback")) {
            //            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("lockpick"));
            System.out.println("TODO: Add sound feedback for finishing the clock puzzle");
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleFeedback", true);
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
        a.fill(0, 255, 0);
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.textSize(32);
        a.text("TEMPORARY RENDER.\nWILL RENDER WITH CLOCK HANDS LATER", Constants.WIDTH / 2, Constants.HEIGHT / 2 - 100);
        a.textSize(128);
        a.text(String.format("%02d:%02d", h, m), Constants.WIDTH / 2, Constants.HEIGHT / 2);
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        clockHotspot.render();
        backHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
