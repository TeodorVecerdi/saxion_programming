package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

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
            a.ActiveScene = "Bedroom/ZoomStuffedAnimals";
        });
        bedControllerHotspot = new MouseHotspot(1595, 685, 325, 252, () -> {
            a.ActiveScene = "Bedroom/ZoomBed";
        });
        drawerHotspot = new MouseHotspot(500, 660, 100, 92, () -> {
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/DrawerUnlocked")
            && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/GotLockpick")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.Lockpick);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/GotLockpick", true);
            }else if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Bedroom Drawer Key")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.BedroomDrawerKey);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/DrawerUnlocked", true);
            }
        });
        keyHotspot = new MouseHotspot(1027, 753, 100, 92, () -> {
            a.InventoryScene.PlayerInventory.AddItem(a.Items.BedroomDrawerKey);
        });
        keyHotspot.SetEnabled(false);
        doorHotspot = new MouseHotspot(621, 317, 204, 430, () -> {
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Lockpick")) {
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.Lockpick);
                a.ActiveScene = "WaitingRoom/Main";
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
}
