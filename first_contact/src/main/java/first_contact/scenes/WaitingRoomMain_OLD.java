package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomMain_OLD extends Scene {

    public MouseHotspot coffeeHotspot;
    public MouseHotspot lockHotspot;
    public MouseHotspot sinkHotspot;
    public MouseHotspot changeChannelHotspot;
    public MouseHotspot togglePlayRadioHotspot;
    public MouseHotspot xylo1Hotspot;
    public MouseHotspot xylo2Hotspot;
    public MouseHotspot xylo3Hotspot;
    public MouseHotspot xylo4Hotspot;
    public MouseHotspot xylo5Hotspot;

    public PImage Background;
    public PImage WaitingRoomWithoutCup, WaitingRoomWithCoffee, WaitingRoomWithCode, WaitingRoomUnlocked;

    private int channel = 0;
    private int xylophoneChannel = 5;
    private boolean resetPlay = true;
    private boolean xylophoneHotspotClickedThisFrame = false;
    private boolean shouldPlayRadio = false;
    private String activeSound = "tvStatic";

    public WaitingRoomMain_OLD () {
        super();
        var a = Entry.Instance;
        Background = a.Assets.GetSprite("scene/waitingRoomWithCup");
        WaitingRoomWithoutCup = a.Assets.GetSprite("scene/waitingRoomWithoutCup");
        WaitingRoomWithCoffee = a.Assets.GetSprite("scene/waitingRoomWithCoffee");
        WaitingRoomWithCode = a.Assets.GetSprite("scene/waitingRoomWithCode");
        WaitingRoomUnlocked = a.Assets.GetSprite("scene/waitingRoomUnlocked");
        coffeeHotspot = new MouseHotspot()
                .AddCollisionTriangle(new Utils.Triangle(1238, 568, 1152, 440, 994, 446))
                .AddCollisionTriangle(new Utils.Triangle(994, 448, 946, 565, 1238, 567))
                .AddCollisionTriangle(new Utils.Triangle(980, 719, 1240, 714, 1236, 567))
                .AddCollisionTriangle(new Utils.Triangle(980, 719, 945, 565, 1237, 568))
                .AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomCoffee";
        });
        lockHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(600, 530, 850, 530, 600, 750)).AddCollisionTriangle(new Utils.Triangle(850, 530, 600, 750, 850, 750)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomLock";
        });
        sinkHotspot = new MouseHotspot()
                .AddCollisionTriangle(new Utils.Triangle(1387, 527, 1336, 567, 1333, 595))
                .AddCollisionTriangle(new Utils.Triangle(1333, 595, 1441, 628, 1389, 525))
                .AddCollisionTriangle(new Utils.Triangle(1441, 626, 1606, 623, 1609, 549))
                .AddCollisionTriangle(new Utils.Triangle(1389, 524, 1448, 526, 1441, 627))
                .AddCollisionTriangle(new Utils.Triangle(1449, 527, 1609, 550, 1440, 628))
                .AddCollisionTriangle(new Utils.Triangle(1406, 617, 1438, 716, 1482, 626))
                .AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            if (a.InventoryScene.PlayerInventory.SelectedItem != -1 && a.InventoryScene.PlayerInventory.Items.get(a.InventoryScene.PlayerInventory.SelectedItem).ItemName.equals("Empty Cup") && !a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotWaterCup")) {
                a.InventoryScene.PlayerInventory.SelectedItem = -1;
                a.InventoryScene.PlayerInventory.RemoveItem(a.Items.GetItem("emptyCup"));
                a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("waterCup"));
                a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/GotWaterCup", true);
            } else if (a.InventoryScene.PlayerInventory.SelectedItem != -1) {
                new FloatingText(Messages.GetRandom(Messages.WrongItem), 1.5f);
            } else {
                new FloatingText(Messages.GetRandom(Messages.NoItem), 1.5f);
            }
        });
        changeChannelHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(759, 398, 785, 398, 759, 422)).AddCollisionTriangle(new Utils.Triangle(785, 398, 759, 422, 785, 422)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            channel++;
            channel %= 8;
            resetPlay = true;
        });
        togglePlayRadioHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(663, 367, 755, 367, 663, 447)).AddCollisionTriangle(new Utils.Triangle(755, 367, 663, 447, 755, 447)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            resetPlay = true;
            shouldPlayRadio = !shouldPlayRadio;
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
        coffeeHotspot.update(deltaTime);
        lockHotspot.update(deltaTime);
        sinkHotspot.update(deltaTime);
        changeChannelHotspot.update(deltaTime);
        togglePlayRadioHotspot.update(deltaTime);
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
        coffeeHotspot.render();
        lockHotspot.render();
        sinkHotspot.render();
        changeChannelHotspot.render();
        togglePlayRadioHotspot.render();
        xylo1Hotspot.render();
        xylo2Hotspot.render();
        xylo3Hotspot.render();
        xylo4Hotspot.render();
        xylo5Hotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

}
