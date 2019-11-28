package first_contact;

import first_contact.inventory.InventoryScene;
import first_contact.inventory.Items;
import first_contact.misc.Assets;
import first_contact.misc.Globals;
import first_contact.misc.FloatingText;
import first_contact.misc.Input;
import first_contact.objects.Scene;
import first_contact.scenes.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Entry extends PApplet {
    public static Entry Instance;
    public float deltaTime = 0f;
    public Map<String, Scene> Scenes;
    public String ActiveScene;
    public InventoryScene InventoryScene;
    public Items Items;
    public HashMap<UUID, FloatingText> FloatingTexts;
    public SecureRandom SecureRandom;
    public Assets Assets;

    public static void main (String[] args) {
        PApplet.main(Entry.class.getName());
    }

    public void settings () {
        Instance = this;
        size(Globals.WIDTH, Globals.HEIGHT);
        fullScreen(2);
    }

    public void setup () {
        frameRate(1000);
        InventoryScene = new InventoryScene();
        FloatingTexts = new HashMap<>();
        SecureRandom = new SecureRandom();
        Assets = new Assets();
        Items = new Items();
        //        @formatter:off
        ActiveScene = "WaitingRoom/LockOverlay";
        Scenes = Map.ofEntries(
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/Main", new WaitingRoomMain()),
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/SignOverlay", new WaitingRoomSignOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("WaitingRoom/LockOverlay", new WaitingRoomLockOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/Main", new BedroomMain()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/Desk", new BedroomDesk()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/Clock", new BedroomClock()),
                new AbstractMap.SimpleEntry<String, Scene>("Bedroom/BedController", new BedroomBedController()),
                new AbstractMap.SimpleEntry<String, Scene>("Hallway/Main", new HallwayMain()),
                new AbstractMap.SimpleEntry<String, Scene>("Hallway/CodeOverlay", new HallwayCodeOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("DoctorOffice/Main", new DoctorOfficeMain()),
                new AbstractMap.SimpleEntry<String, Scene>("DoctorOffice/DrawerOverlay", new DoctorOfficeDrawerOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("DoctorOffice/PasswordNoteOverlay", new DoctorOfficePasswordNoteOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("DoctorOffice/ComputerOverlay", new DoctorOfficeComputerOverlay()),
                new AbstractMap.SimpleEntry<String, Scene>("DoctorOffice/HeadOverlay", new DoctorOfficeHeadOverlay())
        );

        //@formatter:on
    }

    public void update () {
        InventoryScene.update(deltaTime);
        Scenes.get(ActiveScene).update(deltaTime);
        Scene.HotspotClickedThisFrame = false;
        FloatingTexts.entrySet().removeIf(uuidFloatingTextEntry -> uuidFloatingTextEntry.getValue().done);
        FloatingTexts.forEach(((uuid, floatingText) -> floatingText.update(deltaTime)));

        if (Input.GetKeyDown(KeyEvent.VK_SPACE)) {
            Globals.SHOW_DEBUG = !Globals.SHOW_DEBUG;
        }
        if (Input.GetKeyDown(KeyEvent.VK_Z)) {
            Globals.SHOW_HOTSPOTS = !Globals.SHOW_HOTSPOTS;
        }
    }

    public void render () {
        background(0);
        Scenes.get(ActiveScene).render();
        InventoryScene.render();
        FloatingTexts.forEach(((uuid, floatingText) -> floatingText.render()));

        if (Globals.SHOW_DEBUG) {
            fill(0, 255, 0);
            textSize(20);
            text(String.format("FPS %.4f\ndT  %.6f", frameRate, deltaTime), Globals.WIDTH - 200, 20);
            fill(0);
            text(String.format("%s", Input.MousePosition), Globals.WIDTH - 250, 90);
        }
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
        Input.MousePosition.x = Input.MouseX;
        Input.MousePosition.y = Input.MouseY;
    }

    @Override
    public void mouseDragged (MouseEvent event) {
        super.mouseDragged(event);
        Input.MouseX = event.getX();
        Input.MouseY = event.getY();
        Input.MousePosition.x = Input.MouseX;
        Input.MousePosition.y = Input.MouseY;
    }

    //</editor-fold>
}
