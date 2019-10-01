package main;

import main.misc.GameObject;
import main.misc.Input;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainApp extends PApplet {
    public static MainApp Instance;

    public ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    public List<GameObject> gameObjects = new ArrayList<>();
    public List<RectCollider2D> colliders = new ArrayList<>();
    public Camera camera;
    private Wall wall1, wall2;
    private Player player;

    public static void main(String[] args) {
        PApplet.main("main.MainApp");
    }

    @Override
    public void settings() {
        Instance = this;
        size(800, 600);
        wall1 = new Wall(50, 50, 300, 50);
        wall2 = new Wall(50, 100, 50, 300);
        player = new Player(width / 2f, height / 2f, 100, 100, 5f);
        camera = new Camera(player);
    }

    private void update() {
        int length = gameObjects.size();
        for (int i = 0; i < length; i++) {
            if (i >= gameObjects.size()) break;
            var l = gameObjects.get(i);
            if (l.ShouldDestroy) {
                gameObjects.remove(l);
                continue;
            }
            l.update();
        }
//        ListIterator<Loopable> it = loopables.listIterator();
//        while (it.hasNext()) {
//
//        }
        Input.Refresh();
    }

    private void render() {
        int length = gameObjects.size();
        for (int i = 0; i < length; i++) {
            if (i >= gameObjects.size()) break;
            var l = gameObjects.get(i);
            l.render();
        }
    }

    @Override
    public void draw() {
        update();

        background(0x55);
        stroke(0xff);
        line(width / 2, 0, width / 2, height);
        line(0, height / 2, width, height / 2);
        pushMatrix();
        translate(-camera.Position.x, -camera.Position.y);
        scale(camera.Zoom);
        render();
        popMatrix();
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
    public void mouseWheel(processing.event.MouseEvent event) {
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
