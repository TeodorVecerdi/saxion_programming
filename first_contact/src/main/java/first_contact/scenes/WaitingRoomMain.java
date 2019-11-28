package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomMain extends Scene {

    public PImage Background, CoffeeMachine, Table, Chairs, Cup, Horse, Cabinet;
    public PImage BackgroundCableBroken, BackgroundCableFixed;
    public PImage CMEmptyPower, CMEmptyNoPower, CMWater, CMCoffee, CMCode;
    public PImage CabinetLocked, CabinetUnlocked, CabinetCrowbarTaken;
    public PImage TableClosed, TableOpen, TableEmpty;

    public MouseHotspot SinkHotspot, RadioPowerToggleHotspot, RadioChannelToggleHotspot, LockHotspot, CrowbarHotspot, CoffeeMachineHotspot, SignHotspot, CoffeeCupHotspot, PowerCableHotspot, BoxHotspot;
    public MouseHotspot xylo1Hotspot;
    public MouseHotspot xylo2Hotspot;
    public MouseHotspot xylo3Hotspot;
    public MouseHotspot xylo4Hotspot;
    public MouseHotspot xylo5Hotspot;

    private int channel = 0;
    private int xylophoneChannel = 5;
    private boolean resetPlay = true;
    private boolean xylophoneHotspotClickedThisFrame = false;
    private boolean shouldPlayRadio = false;
    private String activeSound = "tvStatic";

    public WaitingRoomMain () {
        super();
        var a = Entry.Instance;
        BackgroundCableBroken = a.Assets.GetSprite("scene/wrBgCableBroken");
        BackgroundCableFixed = a.Assets.GetSprite("scene/wrBgCableFixed");
        Background = BackgroundCableBroken;

        CMEmptyPower = a.Assets.GetSprite("scene/cmEmptyPower");
        CMEmptyNoPower = a.Assets.GetSprite("scene/cmEmptyNoPower");
        CMWater = a.Assets.GetSprite("scene/cmWater");
        CMCoffee = a.Assets.GetSprite("scene/cmCoffee");
        CMCode = a.Assets.GetSprite("scene/cmCode");
        CoffeeMachine = CMEmptyNoPower;

        CabinetLocked = a.Assets.GetSprite("scene/cabinetLocked");
        CabinetUnlocked = a.Assets.GetSprite("scene/cabinetUnlocked");
        CabinetCrowbarTaken = a.Assets.GetSprite("scene/cabinetCrowbarTaken");
        Cabinet = CabinetLocked;

        TableClosed = a.Assets.GetSprite("scene/tableClosed");
        TableOpen = a.Assets.GetSprite("scene/tableOpen");
        TableEmpty = a.Assets.GetSprite("scene/tableEmpty");
        Table = TableClosed;

        Chairs = a.Assets.GetSprite("scene/wrChairs");
        Cup = a.Assets.GetSprite("scene/wrCup");
        Horse = a.Assets.GetSprite("scene/wrHorse");

        SinkHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1496, 528, 1344, 583, 1357, 660)).AddCollisionTriangle(new Utils.Triangle(1496, 527, 1670, 564, 1664, 675)).AddCollisionTriangle(new Utils.Triangle(1355, 660, 1667, 676, 1497, 523)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("emptyCup") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotWaterCup")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotWaterCup", true);
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("waterCup"));
                SinkHotspot.SetEnabled(false);
                new FloatingText("I can use this water to fill up the coffee machine", 4f);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        RadioPowerToggleHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(748, 392, 675, 391, 675, 425)).AddCollisionTriangle(new Utils.Triangle(675, 425, 747, 393, 748, 426)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            resetPlay = true;
            shouldPlayRadio = !shouldPlayRadio;
        });
        RadioChannelToggleHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(753, 394, 788, 395, 788, 420)).AddCollisionTriangle(new Utils.Triangle(754, 394, 753, 419, 788, 420)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            channel++;
            channel %= 8;
            resetPlay = true;
        });
        LockHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(756, 664, 716, 658, 710, 711)).AddCollisionTriangle(new Utils.Triangle(709, 711, 756, 704, 753, 667)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/LockOverlay";
        });
        CrowbarHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(842, 606, 720, 739, 844, 765)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("crowbar"));
            Cabinet = CabinetCrowbarTaken;
            new FloatingText("I can finally open the door to the doctor office\nand get out of here!", 4f);
            CrowbarHotspot.SetEnabled(false);
        });
        CrowbarHotspot.SetEnabled(false);
        CoffeeMachineHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1029, 453, 992, 480, 993, 612)).AddCollisionTriangle(new Utils.Triangle(993, 610, 1098, 608, 1030, 452)).AddCollisionTriangle(new Utils.Triangle(1030, 452, 1100, 460, 1098, 610)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Water Cup")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("waterCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", true);
                CoffeeMachine = CMWater;
                if(!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CableFixed"))
                    new FloatingText("Now I just need to fix the power cable", 4f);
                else
                    new FloatingText("Now I can finally make coffee", 4f);
            } else if (a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CableFixed") && a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasWater") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasCoffee", true);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                new FloatingText("I feel sleepy. I could drink the coffee", 4f);
                CoffeeMachine = CMCoffee;
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup") && a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasCoffee")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("coffeeCup"));
                CoffeeMachine = CMCode;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotCoffee", true);
                CoffeeMachineHotspot.SetEnabled(false);
                new FloatingText("What is this code used for? Maybe the lock on the cabinet...", 4f);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        SignHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1108, 489, 1108, 543, 1240, 544)).AddCollisionTriangle(new Utils.Triangle(1108, 489, 1240, 545, 1241, 487)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/SignOverlay";
        });
        CoffeeCupHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1130, 567, 1130, 601, 1161, 602)).AddCollisionTriangle(new Utils.Triangle(1130, 566, 1164, 568, 1159, 604)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("emptyCup"));
            a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotEmptyCup", true);
            new FloatingText("I should make some coffee..", 3f);
            CoffeeCupHotspot.SetEnabled(false);
        });
        PowerCableHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1101, 644, 1044, 645, 1047, 715)).AddCollisionTriangle(new Utils.Triangle(1048, 713, 1100, 644, 1101, 712)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemID.equals("tape")) {
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("tape"));
                Background = BackgroundCableFixed;
                if(!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/CoffeeMachineHasWater")) {
                    CoffeeMachine = CMEmptyPower;
                }
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/CableFixed", true);
                PowerCableHotspot.SetEnabled(false);
                new FloatingText("The coffee machine finally has power", 4f);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        BoxHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(775, 796, 761, 833, 839, 841)).AddCollisionTriangle(new Utils.Triangle(837, 842, 773, 797, 839, 806)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if(a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/BoxOpen")) {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("tape"));
                Table = TableEmpty;
                new FloatingText("I can finally fix the coffee machine power cable", 4f);
                BoxHotspot.SetEnabled(false);
            } else {
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("tape"));
                new FloatingText("I might need to do something to open this box", 4f);
            }
        });

        xylo1Hotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(673, 923, 717, 870, 700, 928)).AddCollisionTriangle(new Utils.Triangle(717, 870, 700, 928, 740, 871)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone1").play();
        });
        xylo2Hotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(708, 923, 744, 870, 740, 923)).AddCollisionTriangle(new Utils.Triangle(744, 870, 740, 923, 770, 870)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone2").play();
        });
        xylo3Hotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(749, 919, 778, 867, 778, 921)).AddCollisionTriangle(new Utils.Triangle(778, 867, 778, 921, 805, 867)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone3").play();
        });
        xylo4Hotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(790, 918, 812, 869, 819, 921)).AddCollisionTriangle(new Utils.Triangle(812, 869, 819, 921, 839, 871)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone4").play();
        });
        xylo5Hotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(830, 921, 843, 874, 857, 922)).AddCollisionTriangle(new Utils.Triangle(843, 874, 857, 922, 871, 879)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone5").play();
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;

        SinkHotspot.update(deltaTime);
        RadioPowerToggleHotspot.update(deltaTime);
        RadioChannelToggleHotspot.update(deltaTime);
        LockHotspot.update(deltaTime);
        CrowbarHotspot.update(deltaTime);
        CoffeeMachineHotspot.update(deltaTime);
        SignHotspot.update(deltaTime);
        CoffeeCupHotspot.update(deltaTime);
        PowerCableHotspot.update(deltaTime);
        BoxHotspot.update(deltaTime);

        xylo1Hotspot.update(deltaTime);
        xylo2Hotspot.update(deltaTime);
        xylo3Hotspot.update(deltaTime);
        xylo4Hotspot.update(deltaTime);
        xylo5Hotspot.update(deltaTime);

        if (resetPlay) {
            a.Assets.GetSound(activeSound).stop();
            if (shouldPlayRadio) {
                if (channel == xylophoneChannel) {
                    a.Assets.GetSound("xylophone").loop(1, 0, 1f);
                    activeSound = "xylophone";
                } else {
                    a.Assets.GetSound("tvStatic").loop(1, 0, 0.5f);
                    activeSound = "tvStatic";
                }
            }
            resetPlay = false;
        }


        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if (!Scene.HotspotClickedThisFrame) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            }
        }
        xylophoneHotspotClickedThisFrame = false;
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();

        a.image(Background, 0, 0);
        a.image(Cabinet, 581, 276);
        a.image(CoffeeMachine, 997, 460);
        a.image(Horse, 452, 591);
        a.image(Chairs, -1, 0);
        a.image(Table, 591, 683);
        if (!a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotEmptyCup")) a.image(Cup, 1129, 568);

        //UI
        SinkHotspot.render();
        RadioPowerToggleHotspot.render();
        RadioChannelToggleHotspot.render();
        LockHotspot.render();
        CrowbarHotspot.render();
        CoffeeMachineHotspot.render();
        SignHotspot.render();
        CoffeeCupHotspot.render();
        PowerCableHotspot.render();
        BoxHotspot.render();
        xylo1Hotspot.render();
        xylo2Hotspot.render();
        xylo3Hotspot.render();
        xylo4Hotspot.render();
        xylo5Hotspot.render();

        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
