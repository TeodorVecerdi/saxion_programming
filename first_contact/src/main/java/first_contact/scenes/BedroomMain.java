package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
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
    private int correctTime = 13*60+15;

    public BedroomMain () {
        super();
        var a = Entry.Instance;
        BedroomMain = a.Assets.GetSprite("scene/bedroomMain");
        BedroomBedLifted = a.Assets.GetSprite("scene/bedroomBedLifted");
        BedroomOpenDoor = a.Assets.GetSprite("scene/bedroomOpenDoor");
        Background = BedroomMain;

        deskHotspot = new MouseHotspot(189, 555, 416, 282, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Desk";
        });
        clockHotspot = new MouseHotspot(1467, 245, 109, 198, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Clock";
        });
        bedControllerHotspot = new MouseHotspot(1547, 669, 373, 286, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/BedController";
        });
        doorHotspot = new MouseHotspot(605, 300, 234, 444, () -> {
            Scene.HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/DoorOpen")) {
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
        if(bedroomDeskScene.clockTime == correctTime && bedroomClockScene.clockTime == correctTime && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/ClockPuzzleDone")) {
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
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
