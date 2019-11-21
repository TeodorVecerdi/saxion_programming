package first_contact.scenes;

import first_contact.Entry;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PImage;

public class BedroomZoomBed extends Scene {

    public MouseHotspot B1PHotspot;
    public MouseHotspot B1MHotspot;
    public MouseHotspot B2PHotspot;
    public MouseHotspot B2MHotspot;
    public MouseHotspot B3PHotspot;
    public MouseHotspot B3MHotspot;
    public MouseHotspot backHotspot;

    private PImage Background;
    private int b1 = 0;
    private int b2 = 0;
    private int b3 = 0;

    public BedroomZoomBed () {
        var a = Entry.Instance;
        Background = a.loadImage("Bedroom/bedController.png");
        B1PHotspot = new MouseHotspot(736, 327, 75, 48, () -> {
            b1 = 1;
        });
        B1MHotspot = new MouseHotspot(736, 327 + 87, 75, 48, () -> {
            b1 = -1;
        });
        B2PHotspot = new MouseHotspot(736 + 162, 327, 75, 48, () -> {
            b2 = 1;
        });
        B2MHotspot = new MouseHotspot(736 + 162, 327 + 87, 75, 48, () -> {
            b2 = -1;
        });
        B3PHotspot = new MouseHotspot(736 + 162 + 158, 327, 75, 48, () -> {
            b3 = 1;
        });
        B3MHotspot = new MouseHotspot(736 + 162 + 158, 327 + 87, 75, 48, () -> {
            b3 = -1;
        });
        backHotspot = new MouseHotspot(721, 937, 474, 143, () -> {
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
        if(b1 == 1 && b2 == -1 && b3 == -1 && !a.InventoryScene.PlayerInventory.InventoryChecks.get("Bedroom/PuzzleDone")) {
            B1PHotspot.SetEnabled(false);
            B1MHotspot.SetEnabled(false);
            B2PHotspot.SetEnabled(false);
            B2MHotspot.SetEnabled(false);
            B3PHotspot.SetEnabled(false);
            B3MHotspot.SetEnabled(false);
            Background = a.loadImage("Bedroom/bedControllerSecond.png");
            ((BedroomMain) a.Scenes.get("Bedroom/Main")).bedControllerHotspot.SetEnabled(false);
            ((BedroomMain) a.Scenes.get("Bedroom/Main")).stuffedAnimalsHotspot.SetEnabled(false);
            ((BedroomMain) a.Scenes.get("Bedroom/Main")).keyHotspot.SetEnabled(true);
            ((BedroomMain) a.Scenes.get("Bedroom/Main")).Background = a.loadImage("Bedroom/mainSecond.png");
            a.InventoryScene.PlayerInventory.InventoryChecks.put("Bedroom/PuzzleDone", true);
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.background(0xff);
        a.noStroke();
        //b1
        a.fill(0xff2fbf12); //g0
        if (b1 == 1) a.fill(0xff37e015); //g1
        a.rect(736, 327, 75, 48);
        a.fill(0xffcb3322); //r1
        if (b1 == -1) a.fill(0xffe63724); //r0
        a.rect(736, 327 + 87, 75, 48);
        //b2
        a.fill(0xff2fbf12); //g0
        if (b2 == 1) a.fill(0xff37e015); //g1
        a.rect(736 + 162, 327, 75, 48);
        a.fill(0xffcb3322); //r1
        if (b2 == -1) a.fill(0xffe63724); //r0
        a.rect(736 + 162, 327 + 87, 75, 48);
        //b3
        a.fill(0xff2fbf12); //g0
        if (b3 == 1) a.fill(0xff37e015); //g1
        a.rect(736 + 162 + 158, 327, 75, 48);
        a.fill(0xffcb3322); //r1
        if (b3 == -1) a.fill(0xffe63724); //r0
        a.rect(736 + 162 + 158, 327 + 87, 75, 48);
        //todo draw color buttons
        a.image(Background, 0, 0);

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
