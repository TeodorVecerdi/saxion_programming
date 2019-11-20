package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class Scene_Room2Main extends Scene {

    private MouseHotspot changeToScene1;
    private MouseHotspot coffeeHotspot;
    private MouseHotspot lockHotspot;
    private MouseHotspot sinkHotspot;

    private PImage Background;

    public Scene_Room2Main () {
        super();
        var a = Entry.Instance;
        Background = a.loadImage("Room2/mainRoom.png");
        changeToScene1 = new MouseHotspot(5, Constants.HEIGHT / 2 - 50, 100, 100, () -> {
            a.ActiveScene = "TestScene1";
        });
        coffeeHotspot = new MouseHotspot(930, 400, 350, 300, () -> {
            a.ActiveScene = "Room2/ZoomCoffee";
        });
        lockHotspot = new MouseHotspot(600, 530, 250, 220, () -> {
            a.ActiveScene = "Room2/ZoomLock";
        });
        sinkHotspot = new MouseHotspot(1320, 465, 315, 300, () -> {
            if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup")
                    && !a.InventoryScene.PlayerInventory.InventoryChecks.get("GotWaterCup")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.WaterCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("GotWaterCup", true);
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        changeToScene1.update(deltaTime);
        coffeeHotspot.update(deltaTime);
        lockHotspot.update(deltaTime);
        sinkHotspot.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        changeToScene1.render();
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
