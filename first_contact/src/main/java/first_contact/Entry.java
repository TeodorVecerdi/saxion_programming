package first_contact;

import first_contact.inventory.InventoryScene;
import first_contact.misc.Constants;
import first_contact.misc.Input;
import first_contact.inventory.Items;
import first_contact.objects.*;
import first_contact.scenes.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.AbstractMap;
import java.util.Map;

public class Entry extends PApplet {
    public static Entry Instance;
    public float deltaTime = 0f;
    public Map<String, Scene> Scenes;
    public String ActiveScene;
    public first_contact.inventory.InventoryScene InventoryScene;
    public Items Items;

    public static void main (String[] args) {
        PApplet.main(Entry.class.getName());
    }

    public void settings () {
        Instance = this;
        size(Constants.WIDTH, Constants.HEIGHT);
        fullScreen();
    }

    public void setup () {
        frameRate(1000);
        ActiveScene = "Bedroom/Main";
        Items = new Items();
        //@formatter:off
        Scenes = Map.ofEntries(
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/Main", new WaitingRoomMain()),
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/ZoomCoffee", new WaitingRoomZoomCoffee()),
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/ZoomLock", new WaitingRoomZoomLock()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/Main", new BedroomMain()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/ZoomBed", new BedroomZoomBed()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/ZoomStuffedAnimals", new BedroomZoomStuffedAnimals())
        );
        InventoryScene = new InventoryScene();
        //@formatter:on
    }

    public void update () {
        Scenes.get(ActiveScene).update(deltaTime);
        InventoryScene.update(deltaTime);

        // TODO: IS DEBUG, REMOVE
        if (Input.GetKeyDown(java.awt.event.KeyEvent.VK_SPACE)) {
            MouseHotspot.ShowMouseHotspots = !MouseHotspot.ShowMouseHotspots;
        }
    }

    public void render () {
        background(0);
        Scenes.get(ActiveScene).render();
        InventoryScene.render();

        fill(0, 255, 0);
        textSize(20);
        text(String.format("FPS %.4f\ndT  %.6f", frameRate, deltaTime), Constants.WIDTH - 200, 20);
        fill(0);
        text(String.format("%s", Input.MousePosition), Constants.WIDTH - 250, 90);
    }

    public void draw () {
        deltaTime = 1f / frameRate;
        update();
        render();

        Input.Refresh();
    }

    //<editor-fold desc="Input Handling">
    @Override
    public void keyPressed (processing.event.KeyEvent event) {
        super.keyPressed(event);
        Input.PressKey(event.getKeyCode());
    }

    @Override
    public void keyReleased (processing.event.KeyEvent event) {
        super.keyReleased(event);
        Input.ReleaseKey(event.getKeyCode());
    }

    @Override
    public void mouseWheel (MouseEvent event) {
        super.mouseWheel(event);
        Input.Scroll(event.getCount());
    }

    @Override
    public void mousePressed (MouseEvent event) {
        super.mousePressed(event);
        Input.PressButton(event.getButton());
    }

    @Override
    public void mouseReleased (MouseEvent event) {
        super.mouseReleased(event);
        Input.ReleaseButton(event.getButton());
    }

    @Override
    public void mouseMoved (MouseEvent event) {
        super.mouseMoved(event);
        Input.MouseX = event.getX();
        Input.MouseY = event.getY();
        Input.MousePosition.set(Input.MouseX, Input.MouseY);
    }

    //</editor-fold>
}
