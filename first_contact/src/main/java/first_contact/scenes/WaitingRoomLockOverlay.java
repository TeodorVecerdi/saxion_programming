package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomLockOverlay extends Scene {

    public PImage Background;
    public MouseHotspot BackHotspot;
    public MouseHotspot ld1dHotspot;
    public MouseHotspot ld1iHotspot;
    public MouseHotspot ld2dHotspot;
    public MouseHotspot ld2iHotspot;
    public MouseHotspot ld3dHotspot;
    public MouseHotspot ld3iHotspot;

    private int[] lockNumbers = {0, 0, 0};
    private int[] correctCode = {2, 4, 8};

    public WaitingRoomLockOverlay() {
        super();
        var a = Entry.Instance;
        Background = a.Assets.GetSprite("scene/wrLockOverlay");
        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "WaitingRoom/Main";
        });
        ld1dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1038, 493, 997, 518, 1038, 541)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[0] = Math.floorMod(lockNumbers[0] - 1, 10);
        });
        ld1iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1139, 493, 1180, 520, 1139, 543)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[0] = Math.floorMod(lockNumbers[0] + 1, 10);
        });
        ld2dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(996, 602, 1038, 578, 1040, 626)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[1] = Math.floorMod(lockNumbers[1] - 1, 10);
        });
        ld2iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1139, 578, 1180, 602, 1139, 626)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[1] = Math.floorMod(lockNumbers[1] + 1, 10);
        });
        ld3dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(998, 688, 1038, 666, 1039, 714)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[2] = Math.floorMod(lockNumbers[2] - 1, 10);
        });
        ld3iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1139, 664, 1181, 691, 1138, 714)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[2] = Math.floorMod(lockNumbers[2] + 1, 10);
        });
    }


    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        BackHotspot.update(deltaTime);
        ld1dHotspot.update(deltaTime);
        ld2dHotspot.update(deltaTime);
        ld3dHotspot.update(deltaTime);
        ld1iHotspot.update(deltaTime);
        ld2iHotspot.update(deltaTime);
        ld3iHotspot.update(deltaTime);

        if (a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotCoffee") && lockNumbers[0] == correctCode[0] && lockNumbers[1] == correctCode[1] && lockNumbers[2] == correctCode[2]) {
            System.out.println("YES YOU WON BYE");
            var main = ((WaitingRoomMain) a.Scenes.get("WaitingRoom/Main"));
            main.LockHotspot.SetEnabled(false);
            main.CoffeeMachineHotspot.SetEnabled(false);
            main.Cabinet = main.CabinetUnlocked;
//            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("crowbar"));
            a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/Unlocked", true);
            a.ActiveScene = "WaitingRoom/Main";
        }

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
        a.fill(0xffffffff);
        a.textSize(34);
        a.text(lockNumbers[0] + "", 1080, 533);
        a.text(lockNumbers[1] + "", 1080, 616);
        a.text(lockNumbers[2] + "", 1080, 701);
        //UI

        BackHotspot.render();
        ld1dHotspot.render();
        ld2dHotspot.render();
        ld3dHotspot.render();
        ld1iHotspot.render();
        ld2iHotspot.render();
        ld3iHotspot.render();
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }
}
