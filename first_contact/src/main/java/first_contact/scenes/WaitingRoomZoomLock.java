package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class WaitingRoomZoomLock extends Scene {

    public MouseHotspot backHotspot;
    public MouseHotspot ld1dHotspot;
    public MouseHotspot ld1iHotspot;
    public MouseHotspot ld2dHotspot;
    public MouseHotspot ld2iHotspot;
    public MouseHotspot ld3dHotspot;
    public MouseHotspot ld3iHotspot;

    private int[] lockNumbers = {0, 0, 0};
    private int[] correctCode = {2, 4, 8};


    private PImage Background;

    public WaitingRoomZoomLock () {
        super();
        var a = Entry.Instance;

        Background = a.Assets.GetSprite("scene/lockZoom");
        ld1dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1008, 471, 1034, 471, 1008, 511)).AddCollisionTriangle(new Utils.Triangle(1034, 471, 1008, 511, 1034, 511)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[0] = Math.floorMod(lockNumbers[0] - 1, 10);
        });
        ld1iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1121, 471, 1147, 471, 1121, 511)).AddCollisionTriangle(new Utils.Triangle(1147, 471, 1121, 511, 1147, 511)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[0] = Math.floorMod(lockNumbers[0] + 1, 10);
        });
        ld2dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1008, 542, 1034, 542, 1008, 582)).AddCollisionTriangle(new Utils.Triangle(1034, 542, 1008, 582, 1034, 582)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[1] = Math.floorMod(lockNumbers[1] - 1, 10);
        });
        ld2iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1121, 542, 1147, 542, 1121, 582)).AddCollisionTriangle(new Utils.Triangle(1147, 542, 1121, 582, 1147, 582)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[1] = Math.floorMod(lockNumbers[1] + 1, 10);
        });
        ld3dHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1008, 616, 1034, 616, 1008, 656)).AddCollisionTriangle(new Utils.Triangle(1034, 616, 1008, 656, 1034, 656)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[2] = Math.floorMod(lockNumbers[2] - 1, 10);
        });
        ld3iHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(1121, 616, 1147, 616, 1121, 656)).AddCollisionTriangle(new Utils.Triangle(1147, 616, 1121, 656, 1147, 656)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            lockNumbers[2] = Math.floorMod(lockNumbers[2] + 1, 10);
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
        ld1dHotspot.update(deltaTime);
        ld2dHotspot.update(deltaTime);
        ld3dHotspot.update(deltaTime);
        ld1iHotspot.update(deltaTime);
        ld2iHotspot.update(deltaTime);
        ld3iHotspot.update(deltaTime);

        if (a.InventoryScene.PlayerInventory.InventoryChecks.get("WaitingRoom/GotCoffee") && lockNumbers[0] == correctCode[0] && lockNumbers[1] == correctCode[1] && lockNumbers[2] == correctCode[2]) {
            System.out.println("YES YOU WON BYE");
            var main = ((WaitingRoomMain) a.Scenes.get("WaitingRoom/Main"));
            main.lockHotspot.SetEnabled(false);
            main.coffeeHotspot.SetEnabled(false);
            a.InventoryScene.PlayerInventory.AddItem(a.Items.GetItem("crowbar"));
            a.InventoryScene.PlayerInventory.InventoryChecks.put("WaitingRoom/Unlocked", true);
            main.Background = main.WaitingRoomUnlocked;
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
        backHotspot.render();
        ld1dHotspot.render();
        ld2dHotspot.render();
        ld3dHotspot.render();
        ld1iHotspot.render();
        ld2iHotspot.render();
        ld3iHotspot.render();
        a.fill(0xff333333);
        a.textSize(34);
        a.text(lockNumbers[0] + "", 1065, 470 + 35);
        a.text(lockNumbers[1] + "", 1065, 470 + 35 + 71);
        a.text(lockNumbers[2] + "", 1065, 470 + 35 + 71 + 74);
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

}
