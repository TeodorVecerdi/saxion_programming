package first_contact;

import first_contact.misc.Constants;
import first_contact.misc.Input;
import first_contact.objects.TestScene2;
import first_contact.objects.GameObject;
import first_contact.objects.TestScene1;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Entry extends PApplet {
    public static Entry Instance;
    public float deltaTime = 0f;
    public float lastTime = 0f;


    public TestScene1 TestScene1 = new TestScene1();
    public TestScene2 TestScene2 = new TestScene2();
    public GameObject ActiveScene = TestScene1;

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
        ActiveScene.update(deltaTime);
    }

    public void render () {
        background(0x55);

        ActiveScene.render();

        fill(0, 255, 0);
        textSize(20);
        text(String.format("FPS %.4f\ndT  %.6f", frameRate, deltaTime), Constants.WIDTH - 200, 20);
        fill(255);
        text(String.format("[%d, %d]", mouseX, mouseY), Constants.WIDTH - 200, 90);
    }

    public void draw () {
        deltaTime = 1f/frameRate;
        update();
        render();
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
    //</editor-fold>
}
