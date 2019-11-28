package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
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

        Background = a.Assets.GetSprite("scene/coffeeCornerWithCup");
        CoffeeCornerWithoutCup = a.Assets.GetSprite("scene/coffeeCornerWithoutCup");
        CoffeeCornerWithCoffee = a.Assets.GetSprite("scene/coffeeCornerWithCoffee");
        CoffeeCornerWithCode = a.Assets.GetSprite("scene/coffeeCornerWithCode");
        getCupHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(960, 580, 1140, 580, 960, 770)).AddCollisionTriangle(new Utils.Triangle(1140, 580, 960, 770, 1140, 770)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotEmptyCup")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("emptyCup"));
                Background = CoffeeCornerWithoutCup;
                var main = ((WaitingRoomMain_OLD) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithoutCup;
                getCupHotspot.SetEnabled(false);
            }
        });
        coffeeMachineHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(625, 280, 960, 280, 625, 780)).AddCollisionTriangle(new Utils.Triangle(960, 280, 625, 780, 960, 780)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Water Cup")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("waterCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", true);
            } else if (a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasWater") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasCoffee", true);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                Background = CoffeeCornerWithCoffee;
                var main = ((WaitingRoomMain_OLD) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithCoffee;
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup") && a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("coffeeCup"));
                Background = CoffeeCornerWithCode;
                var main = ((WaitingRoomMain_OLD) a.Scenes.get("WaitingRoom/Main"));
                main.Background = main.WaitingRoomWithCode;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotCoffee", true);
                coffeeMachineHotspot.SetEnabled(false);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        backHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        backHotspot.update(deltaTime);
        getCupHotspot.update(deltaTime);
        coffeeMachineHotspot.update(deltaTime);

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
        backHotspot.render();
        getCupHotspot.render();
        coffeeMachineHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

}
