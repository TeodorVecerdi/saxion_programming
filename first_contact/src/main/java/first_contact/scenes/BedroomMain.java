package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomMain extends Scene {

    public MouseHotspot deskHotspot;
    public MouseHotspot clockHotspot;
    public MouseHotspot bedControllerHotspot;
    public MouseHotspot doorHotspot;

    public PImage Background;
    public PImage BedroomMain, BedroomBedLifted, BedroomOpenDoor;
    private int correctTime = 13 * 60 + 15;

    public BedroomMain () {
        super();
        var a = Entry.Instance;
        BedroomMain = a.Assets.GetSprite("scene/bedroomMain");
        BedroomBedLifted = a.Assets.GetSprite("scene/bedroomBedLifted");
        BedroomOpenDoor = a.Assets.GetSprite("scene/bedroomOpenDoor");
        Background = BedroomMain;

        deskHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(189, 555, 605, 555, 189, 837)).AddCollisionTriangle(new Utils.Triangle(605, 555, 189, 837, 605, 837)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Desk";
        });
        clockHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1467, 245, 1576, 245, 1467, 443)).AddCollisionTriangle(new Utils.Triangle(1576, 245, 1467, 443, 1576, 443)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Clock";
        });
        bedControllerHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1547, 669, 1920, 669, 1547, 955)).AddCollisionTriangle(new Utils.Triangle(1920, 669, 1547, 955, 1920, 955)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/BedController";
        });
        doorHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(605, 300, 839, 300, 605, 744)).AddCollisionTriangle(new Utils.Triangle(839, 300, 605, 744, 839, 744)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/DoorOpen")) {
                a.ActiveScene = "Hallway/Main";
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("bedroomKey")) {
                Background = BedroomOpenDoor;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/DoorOpen", true);
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("bedroomKey"));
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        deskHotspot.update(deltaTime);
        clockHotspot.update(deltaTime);
        bedControllerHotspot.update(deltaTime);
        doorHotspot.update(deltaTime);

        var bedroomDeskScene = ((BedroomDesk) a.Scenes.get("Bedroom/Desk"));
        var bedroomClockScene = ((BedroomClock) a.Scenes.get("Bedroom/Clock"));
        if (bedroomDeskScene.clockTime == correctTime && bedroomClockScene.clockTime == correctTime && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleDone")) {
            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("lockpick"));
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/ClockPuzzleDone", true);
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

        deskHotspot.render();
        clockHotspot.render();
        bedControllerHotspot.render();
        doorHotspot.render();
        //UI
        if (Constants.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
