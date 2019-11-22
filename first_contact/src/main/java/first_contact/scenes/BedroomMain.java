package first_contact.scenes;

import com.jogamp.newt.event.MouseEvent;
import first_contact.Entry;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;
import java.util.UUID;

public class BedroomMain extends Scene {

    public MouseHotspot stuffedAnimalsHotspot;
    public MouseHotspot bedControllerHotspot;
    public MouseHotspot drawerHotspot;
    public MouseHotspot keyHotspot;
    public MouseHotspot doorHotspot;

    public PImage Background;


    public BedroomMain () {
        super();
        var a = Entry.Instance;
        Background = a.loadImage("Bedroom/main.png");
        stuffedAnimalsHotspot = new MouseHotspot(347, 591, 142, 130, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/ZoomStuffedAnimals";
        });
        bedControllerHotspot = new MouseHotspot(1595, 685, 325, 252, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/ZoomBed";
        });
        drawerHotspot = new MouseHotspot(500, 660, 100, 92, () -> {
            HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/DrawerUnlocked")
            && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/GotLockpick")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.Lockpick);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/GotLockpick", true);
            }else if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Bedroom Drawer Key")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.BedroomDrawerKey);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/DrawerUnlocked", true);
            }else if(a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        keyHotspot = new MouseHotspot(1027, 753, 100, 92, () -> {
            HotspotClickedThisFrame = true;
            if(!a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/GotKey")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.BedroomDrawerKey);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/GotKey", true);
            }
        });
        keyHotspot.SetEnabled(false);
        doorHotspot = new MouseHotspot(621, 317, 204, 430, () -> {
            HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Lockpick")) {
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.Lockpick);
                a.ActiveScene = "Hallway/Main";
            }else if(a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;

        stuffedAnimalsHotspot.update(deltaTime);
        bedControllerHotspot.update(deltaTime);
        keyHotspot.update(deltaTime);
        drawerHotspot.update(deltaTime);
        doorHotspot.update(deltaTime);

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
        stuffedAnimalsHotspot.render();
        bedControllerHotspot.render();
        keyHotspot.render();
        drawerHotspot.render();
        doorHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }

    private void mouseClicked() {

    }
}
