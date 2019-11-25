package first_contact.scenes;

import first_contact.Entry;
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
    public MouseHotspot changeChannelHotspot;
    public MouseHotspot togglePlayRadioHotspot;
    public MouseHotspot xylo11Hotspot, xylo12Hotspot, xylo13Hotspot;
    public MouseHotspot xylo21Hotspot, xylo22Hotspot, xylo23Hotspot;
    public MouseHotspot xylo31Hotspot, xylo32Hotspot;
    public MouseHotspot xylo41Hotspot, xylo42Hotspot;
    public MouseHotspot xylo51Hotspot, xylo52Hotspot;

    public PImage Background;
    public PImage WaitingRoomWithoutCup, WaitingRoomWithCoffee, WaitingRoomWithCode, WaitingRoomUnlocked;

    private int channel = 0;
    private int xylophoneChannel = 5;
    private boolean resetPlay = true;
    private boolean xylophoneHotspotClickedThisFrame = false;
    private boolean shouldPlayRadio = false;
    private String activeSound = "tvStatic";

    public WaitingRoomMain () {
        super();
        var a = Entry.Instance;
        Background = a.Assets.GetSprite("scene/waitingRoomWithCup");
        WaitingRoomWithoutCup = a.Assets.GetSprite("scene/waitingRoomWithoutCup");
        WaitingRoomWithCoffee = a.Assets.GetSprite("scene/waitingRoomWithCoffee");
        WaitingRoomWithCode = a.Assets.GetSprite("scene/waitingRoomWithCode");
        WaitingRoomUnlocked = a.Assets.GetSprite("scene/waitingRoomUnlocked");
        coffeeHotspot = new MouseHotspot(930, 400, 350, 300, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomCoffee";
        });
        lockHotspot = new MouseHotspot(600, 530, 250, 220, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/ZoomLock";
        });
        sinkHotspot = new MouseHotspot(1320, 465, 315, 300, () -> {
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
        changeChannelHotspot = new MouseHotspot(759, 398, 26, 24, () -> {
            Scene.HotspotClickedThisFrame = true;
            channel++;
            channel %= 8;
            resetPlay = true;
        });
        togglePlayRadioHotspot = new MouseHotspot(663, 367, 92, 80, () -> {
            Scene.HotspotClickedThisFrame = true;
            resetPlay = true;
            shouldPlayRadio = !shouldPlayRadio;
        });
        //region xylo hotspot init
        xylo11Hotspot = new MouseHotspot(673, 890, 42, 39, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone1").play();
        });
        xylo12Hotspot = new MouseHotspot(694, 881, 28, 20, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone1").play();
        });
        xylo13Hotspot = new MouseHotspot(705, 867, 28, 20, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone1").play();
        });
        xylo21Hotspot = new MouseHotspot(709, 888, 42, 39, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone2").play();
        });
        xylo22Hotspot = new MouseHotspot(730, 881, 28, 20, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone2").play();
        });
        xylo23Hotspot = new MouseHotspot(737, 867, 28, 20, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone2").play();
        });
        xylo31Hotspot = new MouseHotspot(751, 881, 38, 44, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone3").play();
        });
        xylo32Hotspot = new MouseHotspot(769, 864, 31, 26, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone3").play();
        });
        xylo41Hotspot = new MouseHotspot(791, 881, 34, 44, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone4").play();
        });
        xylo42Hotspot = new MouseHotspot(805, 868, 31, 19, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone4").play();
        });
        xylo51Hotspot = new MouseHotspot(831, 881, 31, 44, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone5").play();
        });
        xylo52Hotspot = new MouseHotspot(838, 875, 31, 19, () -> {
            Scene.HotspotClickedThisFrame = true;
            if (xylophoneHotspotClickedThisFrame) return;
            xylophoneHotspotClickedThisFrame = true;
            a.Assets.GetSound("xylophone5").play();
        });
        //endregion
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        coffeeHotspot.update(deltaTime);
        lockHotspot.update(deltaTime);
        sinkHotspot.update(deltaTime);
        changeChannelHotspot.update(deltaTime);
        togglePlayRadioHotspot.update(deltaTime);
        //region xylo hotspot update
        xylo11Hotspot.update(deltaTime);
        xylo12Hotspot.update(deltaTime);
        xylo13Hotspot.update(deltaTime);
        xylo21Hotspot.update(deltaTime);
        xylo22Hotspot.update(deltaTime);
        xylo23Hotspot.update(deltaTime);
        xylo31Hotspot.update(deltaTime);
        xylo32Hotspot.update(deltaTime);
        xylo41Hotspot.update(deltaTime);
        xylo42Hotspot.update(deltaTime);
        xylo51Hotspot.update(deltaTime);
        xylo52Hotspot.update(deltaTime);
        //endregion

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
        //region xylo hotspot render
        xylo11Hotspot.render();
        xylo12Hotspot.render();
        xylo13Hotspot.render();
        xylo21Hotspot.render();
        xylo22Hotspot.render();
        xylo23Hotspot.render();
        xylo31Hotspot.render();
        xylo32Hotspot.render();
        xylo41Hotspot.render();
        xylo42Hotspot.render();
        xylo51Hotspot.render();
        xylo52Hotspot.render();
        //endregion
        //UI
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }

}
