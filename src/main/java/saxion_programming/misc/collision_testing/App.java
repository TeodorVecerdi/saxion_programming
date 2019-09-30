package saxion_programming.misc.collision_testing;

import processing.core.PApplet;
import processing.event.KeyEvent;
import saxion_programming.misc.collision_testing.collider.Collider2D;
import saxion_programming.misc.collision_testing.collider.RectCollider2D;

import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {
    public List<Collider2D> Colliders = new ArrayList<>();
    private Player player;
    private RectCollider2D testCollider1;
    private RectCollider2D testCollider2;
    private int w = 800;
    private int h = 800;

    public static void main (String[] args) {
        PApplet.main("saxion_programming.misc.collision_testing.App");
    }

    public void settings () {
        size(w, h);
        player = new Player(this, 1.5f, w / 2, h / 2);
        testCollider1 = new RectCollider2D(this, null, 200, 200, 100, 100, 0, 0);
        testCollider2 = new RectCollider2D(this, null, 350, 200, 20, 100, 0, 0);
        Colliders.add(player.Collider);
        Colliders.add(testCollider1);
        Colliders.add(testCollider2);
    }

    private void update () {
        player.update();
        testCollider1.update();
        //        testCollider2.update();

        //        if(player.Collider2D.CheckCollisionWith(testCollider)) {
        //            player.Collider2D.RespondToCollisionWith(testCollider);
        //        }
    }

    private void render () {
        background(0x77);
        player.render();
        testCollider1.render();
        //        testCollider2.render();
    }

    public void draw () {
        update();
        render();
    }

    @Override
    public void keyPressed (KeyEvent event) {
        super.keyPressed(event);
        Input.PressKey(event.getKeyCode());
    }

    @Override
    public void keyReleased (KeyEvent event) {
        super.keyReleased();
        Input.ReleaseKey(event.getKeyCode());
    }
}
