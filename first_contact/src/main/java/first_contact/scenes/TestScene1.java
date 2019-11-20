package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Constants;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class TestScene1 extends Scene {

    private MouseHotspot changeToScene2;
    private MouseHotspot getEmptyCup;
    private MouseHotspot fillEmptyCup;
    private MouseHotspot removeLastItem;//todo remove this
    private PImage bedroomBackground;

    public TestScene1 () {
        super();
        var a = Entry.Instance;
        changeToScene2 = new MouseHotspot(Constants.WIDTH - 105, Constants.HEIGHT / 2 - 50, 100, 100, () -> {
            a.ActiveScene = "Room2/Main";
        });
        removeLastItem = new MouseHotspot(0, 0, 100, 100, () -> {
           if(!a.InventoryScene.PlayerInventory.Items.isEmpty()) {
               a.InventoryScene.PlayerInventory.Items.remove(a.InventoryScene.PlayerInventory.Items.size()-1);
           }
        });
        getEmptyCup = new MouseHotspot(190, 600, 400, 250, () -> {
            if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("GotEmptyCup")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("GotEmptyCup", true);
            }
        });

        fillEmptyCup = new MouseHotspot(1485, 250, 100, 170, () -> {
            if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup")
                    && !a.InventoryScene.PlayerInventory.InventoryChecks.get("GotWaterCup")
            ) {
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.WaterCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("GotWaterCup", true);
            }
        });
        bedroomBackground = a.loadImage("bedroom.jpg");
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        changeToScene2.update(deltaTime);
        getEmptyCup.update(deltaTime);
        fillEmptyCup.update(deltaTime);
        removeLastItem.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(bedroomBackground, 0, 0);
        changeToScene2.render();
        getEmptyCup.render();
        fillEmptyCup.render();
        removeLastItem.render();

        a.fill(255, 0, 0);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
