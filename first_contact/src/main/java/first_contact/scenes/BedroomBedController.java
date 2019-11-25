package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.misc.Messages;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class BedroomBedController extends Scene {
    public MouseHotspot B1PHotspot;
    public MouseHotspot B1MHotspot;
    public MouseHotspot B2PHotspot;
    public MouseHotspot B2MHotspot;
    public MouseHotspot B3PHotspot;
    public MouseHotspot B3MHotspot;
    public MouseHotspot backHotspot;

    public PImage Background;
    public PImage BedroomBedController, BedroomBedControllerBedLifted;
    private int b1 = 0;
    private int b2 = 0;
    private int b3 = 0;

    public BedroomBedController () {
        var a = Entry.Instance;
        BedroomBedController = a.Assets.GetSprite("scene/bedroomBedController");
        BedroomBedControllerBedLifted = a.Assets.GetSprite("scene/bedroomBedControllerBedLifted");
        Background = BedroomBedController;
        B1PHotspot = new MouseHotspot(785, 315, 80, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b1 = 1;
        });
        B1MHotspot = new MouseHotspot(787, 408, 78, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b1 = -1;
        });
        B2PHotspot = new MouseHotspot(960, 315, 80, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b2 = 1;
        });
        B2MHotspot = new MouseHotspot(961, 408, 78, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b2 = -1;
        });
        B3PHotspot = new MouseHotspot(1128, 315, 80, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b3 = 1;
        });
        B3MHotspot = new MouseHotspot(1127, 408, 78, 48, () -> {
            Scene.HotspotClickedThisFrame = true;
            b3 = -1;
        });
        backHotspot = new MouseHotspot(0, 941, 1920, 139, () -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "Bedroom/Main";
        });
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        B1PHotspot.update(deltaTime);
        B1MHotspot.update(deltaTime);
        B2PHotspot.update(deltaTime);
        B2MHotspot.update(deltaTime);
        B3PHotspot.update(deltaTime);
        B3MHotspot.update(deltaTime);
        backHotspot.update(deltaTime);
        if (b1 == 1 && b2 == -1 && b3 == -1 && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/PuzzleDone")) {
            B1PHotspot.SetEnabled(false);
            B1MHotspot.SetEnabled(false);
            B2PHotspot.SetEnabled(false);
            B2MHotspot.SetEnabled(false);
            B3PHotspot.SetEnabled(false);
            B3MHotspot.SetEnabled(false);
            Background = BedroomBedControllerBedLifted;
            ((BedroomMain) a.Scenes.get("Bedroom/Main")).Background = ((BedroomMain) a.Scenes.get("Bedroom/Main")).BedroomBedLifted;
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/PuzzleDone", true);
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
        a.noStroke();
        //b1
        a.fill(0x5539f913);
        if (b1 == 1) a.rect(785, 315, 80, 48);
        if (b2 == 1) a.rect(960, 315, 80, 48);
        if (b3 == 1) a.rect(1128, 315, 80, 48);
        a.fill(0x55fd1900);
        if (b1 == -1) a.rect(787, 408, 78, 48);
        if (b2 == -1) a.rect(961, 408, 78, 48);
        if (b3 == -1) a.rect(1127, 408, 78, 48);


        B1PHotspot.render();
        B1MHotspot.render();
        B2PHotspot.render();
        B2MHotspot.render();
        B3PHotspot.render();
        B3MHotspot.render();
        backHotspot.render();

        a.popMatrix();
    }
}
