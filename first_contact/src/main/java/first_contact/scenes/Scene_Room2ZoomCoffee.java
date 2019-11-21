package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class Scene_Room2ZoomCoffee extends Scene {

    public MouseHotspot getCupHotspot;
    public MouseHotspot coffeeMachineHotspot;
    public MouseHotspot backHotspot;

    private PImage Background;

    public Scene_Room2ZoomCoffee() {
        super();
        var a = Entry.Instance;

        Background = a.loadImage("Room2/coffeeZoom.png");
        backHotspot = new MouseHotspot(660, 890, 550, 190, () -> {
            a.ActiveScene = "Room2/Main";
        });
        getCupHotspot = new MouseHotspot(960, 580, 180, 190, () -> {
            if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("Room2/GotEmptyCup")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.EmptyCup);
//                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/GotEmptyCup", true);
            }
        });
        coffeeMachineHotspot = new MouseHotspot(625, 280, 335, 500, () -> {
            if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Water Cup")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.WaterCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/CoffeeMachineHasWater", true);
            }
            else if(a.InventoryScene.PlayerInventory.InventoryChecks.get("Room2/CoffeeMachineHasWater") &&
                    !a.InventoryScene.PlayerInventory.InventoryChecks.get("Room2/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/CoffeeMachineHasCoffee", true);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/CoffeeMachineHasWater", false);
                Background = a.loadImage("Room2/coffeeZoomMadeCoffee.png");
            }
            else if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup")
                    && a.InventoryScene.PlayerInventory.InventoryChecks.get("Room2/CoffeeMachineHasCoffee")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.CoffeeCup);
                Background = a.loadImage("Room2/coffeeZoomGotCoffee.png");
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/CoffeeMachineHasWater", false);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/GotCoffee", true);
            } else {
                System.out.println("You clicked on the coffee machine. You get 3 free wishes.");
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        backHotspot.update(deltaTime);
        getCupHotspot.update(deltaTime);
        coffeeMachineHotspot.update(deltaTime);
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        backHotspot.render();
        getCupHotspot.render();
        coffeeMachineHotspot.render();
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
