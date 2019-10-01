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

    public static void main (String[] args) {
        PApplet.main("main.MainApp");
    }

    @Override
    public void settings () {
        Instance = this;
        size(800, 600);
        box1 = new Box(50, 50, 300, 50);
        box2 = new Box(50, 100, 50, 300);
        player = new Player(width / 2, height / 2, 100, 100, 5f);
    }

    private void update () {
        for (var l : loopables) {
            l.update();
        }
    }

    private void render () {
        background(0x55);
        for (var l : loopables) {
            l.render();
        }
    }

    @Override
    public void draw () {
        update();
        render();
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
