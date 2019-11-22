package first_contact.scenes;

import com.jogamp.newt.event.MouseEvent;
import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomMain extends Scene {

    public MouseHotspot coffeeHotspot;
    public MouseHotspot lockHotspot;
    public MouseHotspot sinkHotspot;

    public PImage Background;
    public PImage WaitingRoomWithoutCup, WaitingRoomWithCoffee, WaitingRoomWithCode, WaitingRoomUnlocked;

    public WaitingRoomMain () {
        super();
        var a = Entry.Instance;
        Background = a.loadImage("WaitingRoom/waitingRoomWithCup.png");
        WaitingRoomWithoutCup = a.loadImage("WaitingRoom/waitingRoomWithoutCup.png");
        WaitingRoomWithCoffee = a.loadImage("WaitingRoom/waitingRoomWithCoffee.png");
        WaitingRoomWithCode = a.loadImage("WaitingRoom/waitingRoomWithCode.png");
        WaitingRoomUnlocked = a.loadImage("WaitingRoom/waitingRoomUnlocked.png");
        coffeeHotspot = new MouseHotspot(930, 400, 350, 300, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomCoffee";
        });
        lockHotspot = new MouseHotspot(600, 530, 250, 220, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomLock";
        });
        sinkHotspot = new MouseHotspot(1320, 465, 315, 300, () -> {
            HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup")
                    && !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotWaterCup")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.WaterCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotWaterCup", true);
            } else if(a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        coffeeHotspot.update(deltaTime);
        lockHotspot.update(deltaTime);
        sinkHotspot.update(deltaTime);

        if(Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if(!HotspotClickedThisFrame) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            }
        }
        HotspotClickedThisFrame = false;
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        coffeeHotspot.render();
        lockHotspot.render();
        sinkHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }

}
