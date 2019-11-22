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

public class WaitingRoomZoomCoffee extends Scene {

    public MouseHotspot getCupHotspot;
    public MouseHotspot coffeeMachineHotspot;
    public MouseHotspot backHotspot;

    private PImage Background;
    public PImage CoffeeCornerWithoutCup, CoffeeCornerWithCoffee, CoffeeCornerWithCode;

    public WaitingRoomZoomCoffee () {
        super();
        var a = Entry.Instance;

        Background = a.loadImage("WaitingRoom/coffeeCornerWithCup.png");
        CoffeeCornerWithoutCup = a.loadImage("WaitingRoom/coffeeCornerWithoutCup.png");
        CoffeeCornerWithCoffee = a.loadImage("WaitingRoom/coffeeCornerWithCoffee.png");
        CoffeeCornerWithCode = a.loadImage("WaitingRoom/coffeeCornerWithCode.png");
        backHotspot = new MouseHotspot(660, 890, 550, 190, () -> {
            HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
        getCupHotspot = new MouseHotspot(960, 580, 180, 190, () -> {
            HotspotClickedThisFrame = true;
            if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotEmptyCup")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.EmptyCup);
                Background = CoffeeCornerWithoutCup;
                var main = ((WaitingRoomMain) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithoutCup;
                getCupHotspot.SetEnabled(false);
            }
        });
        coffeeMachineHotspot = new MouseHotspot(625, 280, 335, 500, () -> {
            HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Water Cup")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.WaterCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", true);
            }
            else if(a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasWater") &&
                    !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasCoffee", true);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                Background = CoffeeCornerWithCoffee;
                var main = ((WaitingRoomMain) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithCoffee;
            }
            else if(a.InventoryScene.PlayerInventory.SelectedItem != -1
                    && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup")
                    && a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")
            ) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.EmptyCup);
                a.InventoryScene.PlayerInventory.AddItem(a.Items.CoffeeCup);
                Background = CoffeeCornerWithCode;
                var main = ((WaitingRoomMain) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithCode;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotCoffee", true);
                coffeeMachineHotspot.SetEnabled(false);
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
        backHotspot.update(deltaTime);
        getCupHotspot.update(deltaTime);
        coffeeMachineHotspot.update(deltaTime);

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
