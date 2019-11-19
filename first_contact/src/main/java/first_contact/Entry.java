package first_contact;

import first_contact.misc.Constants;
import first_contact.misc.Input;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import first_contact.objects.TestScene1;
import first_contact.objects.TestScene2;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.AbstractMap;
import java.util.Map;

public class Entry extends PApplet {
    public static Entry Instance;
    public float deltaTime = 0f;

    //@formatter:off
    public Map<String, Scene> Scenes = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, Scene>("TestScene1", new TestScene1()),
            new AbstractMap.SimpleEntry<String, Scene>("TestScene2", new TestScene2())
    );
    //@formatter:on
    public String ActiveScene = "TestScene1";

    public static void main (String[] args) {
        PApplet.main(Entry.class.getName());
    }

    public void settings () {
        Instance = this;
        size(Constants.WIDTH, Constants.HEIGHT);
    }

    public void setup () {
        frameRate(1000);
    }

    public void update () {
        Scenes.get(ActiveScene).update(deltaTime);

        // TODO: IS DEBUG, REMOVE
        if(Input.GetKeyDown(java.awt.event.KeyEvent.VK_SPACE)) {
            MouseHotspot.ShowMouseHotspots = !MouseHotspot.ShowMouseHotspots;
        }
    }

    public void render () {
        background(0x55);

        Scenes.get(ActiveScene).render();

        fill(0, 255, 0);
        textSize(20);
        text(String.format("FPS %.4f\ndT  %.6f", frameRate, deltaTime), Constants.WIDTH - 200, 20);
        fill(255);
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
