package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class Scene_Room2ZoomLock extends Scene {

    public MouseHotspot backHotspot;
    public MouseHotspot ld1dHotspot;
    public MouseHotspot ld1iHotspot;
    public MouseHotspot ld2dHotspot;
    public MouseHotspot ld2iHotspot;
    public MouseHotspot ld3dHotspot;
    public MouseHotspot ld3iHotspot;

    private int[] lockNumbers = {0, 0, 0};
    private int[] correctCode = {1, 2, 3};


    private PImage Background;

    public Scene_Room2ZoomLock () {
        super();
        var a = Entry.Instance;

        Background = a.loadImage("Room2/lockZoom.png");
        backHotspot = new MouseHotspot(660, 890, 550, 190, () -> {
            a.ActiveScene = "Room2/Main";
        });
        ld1dHotspot = new MouseHotspot(1008, 471, 26, 40, () -> {
            lockNumbers[0] = Math.floorMod(lockNumbers[0] - 1, 10);
        });
        ld2dHotspot = new MouseHotspot(1008, 471 + 71, 26, 40, () -> {
            lockNumbers[1] = Math.floorMod(lockNumbers[1] - 1, 10);
        });
        ld3dHotspot = new MouseHotspot(1008, 471 + 71 + 74, 26, 40, () -> {
            lockNumbers[2] = Math.floorMod(lockNumbers[2] - 1, 10);
        });
        ld1iHotspot = new MouseHotspot(1008 + 113, 471, 26, 40, () -> {
            lockNumbers[0] = Math.floorMod(lockNumbers[0] + 1, 10);
        });
        ld2iHotspot = new MouseHotspot(1008 + 113, 471 + 71, 26, 40, () -> {
            lockNumbers[1] = Math.floorMod(lockNumbers[1] + 1, 10);
        });
        ld3iHotspot = new MouseHotspot(1008 + 113, 471 + 71 + 74, 26, 40, () -> {
            lockNumbers[2] = Math.floorMod(lockNumbers[2] + 1, 10);
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

        if (a.InventoryScene.PlayerInventory.InventoryChecks.get("Room2/GotCoffee") && lockNumbers[0] == correctCode[0] && lockNumbers[1] == correctCode[1] && lockNumbers[2] == correctCode[2]) {
            System.out.println("YES YOU WON BYE");
            ((Scene_Room2Main) a.Scenes.get("Room2/Main")).lockHotspot.SetEnabled(false);
            ((Scene_Room2Main) a.Scenes.get("Room2/Main")).coffeeHotspot.SetEnabled(false);
            a.InventoryScene.PlayerInventory.AddItem(a.Items.Crowbar);
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Room2/Unlocked", true);
            a.ActiveScene = "Room2/Main";
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
        a.fill(0, 0, 255);
        a.textSize(35);
        a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        a.popMatrix();
    }
}
