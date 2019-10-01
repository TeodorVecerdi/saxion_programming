package main;

import main.misc.Input;
import main.misc.Loopable;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends PApplet {
    public static MainApp Instance;

    public List<Loopable> loopables = new ArrayList<>();
    public List<RectCollider2D> colliders = new ArrayList<>();
    private Box box1, box2;
    private Player player;
    private Camera camera;

    public static void main (String[] args) {
        PApplet.main("main.MainApp");
    }

    @Override
    public void settings () {
        Instance = this;
        size(800, 600, P3D);
        box1 = new Box(50, 50, 300, 50);
        box2 = new Box(50, 100, 50, 300);
        player = new Player(width / 2f, height / 2f, 100, 100, 5f);
        camera = new Camera(player);
    }

    private void update () {
        for (var l : loopables) {
            l.update();
        }
        Input.Refresh();
    }

    private void render () {
        for (var l : loopables) {
            l.render();
        }
    }

    @Override
    public void draw () {
        update();

        background(0x55);
        stroke(0xff);
        line(width / 2, 0, width / 2, height);
        line(0, height / 2, width, height / 2);
        pushMatrix();
        translate(-camera.Position.x, -camera.Position.y);
        scale(camera.Zoom);
        //left, right, bottom, top
//        ortho(-width/2f, width/2f, -height/2f, height/2f);
//        ortho(-camera.Position.x, camera.Position.x, -camera.Position.y, camera.Position.y);
        render();
        popMatrix();
    }

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
    public void mouseWheel (processing.event.MouseEvent event) {
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
}
