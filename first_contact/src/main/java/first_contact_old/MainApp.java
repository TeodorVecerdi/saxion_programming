package first_contact_old;

import processing.core.PApplet;
import processing.event.MouseEvent;
import first_contact_old.misc.Input;
import first_contact_old.misc.RenderLayer;
import first_contact_old.objects.Camera;
import first_contact_old.objects.GameObject;
import first_contact_old.objects.Player;
import first_contact_old.objects.Wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainApp extends PApplet {
    public static MainApp Instance;
    private static Object inst;
    public ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    public HashMap<Integer, ArrayList<GameObject>> gameObjects = new HashMap<>();
    public ArrayList<GameObject> gameObjectsToAdd = new ArrayList<>();
    public List<RectCollider2D> colliders = new ArrayList<>();
    public Camera camera;
    private Wall wall1, wall2;
    private Player player;

    private float opacity = 0f;
    private float opacityIncrease = 2.5f;

    public static void main(String[] args) {
        PApplet.main("first_contact.MainApp");
    }

    @Override
    public void settings() {
        Instance = this;
        size(800, 600);
        wall1 = new Wall(50, 50, 300, 50);
        wall2 = new Wall(50, 100, 50, 300);
        player = new Player(width / 2f, height / 2f, 80, 100, 5f);
        camera = new Camera(player);

        gameObjects.put(RenderLayer.UI, new ArrayList<>());
        gameObjects.put(RenderLayer.NoCameraWorld, new ArrayList<>());
        gameObjects.put(RenderLayer.World, new ArrayList<>());
    }

    private void update() {
        int length = gameObjectsToAdd.size();
        for (int i = 0; i < length; i++) {
            if (i >= gameObjectsToAdd.size()) break;
            var obj = gameObjectsToAdd.get(i);
            gameObjects.get(obj.renderLayer).add(obj);
            gameObjectsToAdd.remove(obj);

        }
        for (int layer : gameObjects.keySet()) {
            int layerLength = gameObjects.get(layer).size();
            for (int i = 0; i < layerLength; i++) {
                if (i >= gameObjects.get(layer).size()) break;
                var l = gameObjects.get(layer).get(i);
                if (l.ShouldDestroy) {
                    gameObjects.get(layer).remove(l);
                    continue;
                }
                l.update();
            }
        }
//        ListIterator<Loopable> it = loopables.listIterator();
//        while (it.hasNext()) {
//
//        }
        Input.Refresh();
    }

    private void render() {
        background(0x55);
        var worldLayer = gameObjects.get(RenderLayer.World);
        int worldLayerLength = worldLayer.size();
        pushMatrix();
        translate(-camera.Position.x, -camera.Position.y);
        scale(camera.Zoom);
        for (int i = 0; i < worldLayerLength; i++) {
            if (i >= worldLayer.size()) break;
            var l = worldLayer.get(i);
            l.render();
        }
        popMatrix();


        var NCWorldLayer = gameObjects.get(RenderLayer.NoCameraWorld);
        var NCWorldLayerLength = NCWorldLayer.size();
        for (int i = 0; i < NCWorldLayerLength; i++) {
            if (i >= NCWorldLayer.size()) break;
            var l = NCWorldLayer.get(i);
            l.render();
        }

        var UILayer = gameObjects.get(RenderLayer.UI);
        var UILayerLength = UILayer.size();
        for (int i = 0; i < UILayerLength; i++) {
            if (i >= UILayer.size()) break;
            var l = UILayer.get(i);
            l.render();
        }
        opacity += opacityIncrease;
        if(opacity >= 255.0f || opacity <= 0.0f) opacityIncrease = -opacityIncrease;
        fill(0x00, opacity);
        rect(0, 0, width, height);
        System.out.println(opacity);
    }

    @Override
    public void draw() {
        update();
        render();
    }

    @Override
    public void keyPressed(processing.event.KeyEvent event) {
        super.keyPressed(event);
        Input.PressKey(event.getKeyCode());
    }

    @Override
    public void keyReleased(processing.event.KeyEvent event) {
        super.keyReleased(event);
        Input.ReleaseKey(event.getKeyCode());
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        super.mouseWheel(event);
        Input.Scroll(event.getCount());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        Input.PressButton(event.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        Input.ReleaseButton(event.getButton());
    }
}
