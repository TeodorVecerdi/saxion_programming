package saxion_programming.week3;

import processing.core.PApplet;
import processing.event.MouseEvent;
import saxion_programming.week3.misc.Input;

public class App extends PApplet {
    public final int _width = 1280;
    public final int _height = 720;
    private Robot character;
    private Ball ball;

    public static void main(String[] args) {
        PApplet.main("saxion_programming.week3.App");
    }

    public void settings() {
        size(_width, _height);
        smooth(8);
        character = new Robot(this, width / 2f, height / 2f, 150f, 200f, 1f, 5f);
        ball = new Ball(this, width/2f, height/2f, 25f, 25f, 1f, 5f);
    }

    public void update() {
        character.update();
        ball.update();
        Input.Refresh();
    }

    public void render() {
        background(0x55);
        character.render();
        ball.render();
    }

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
    public void mouseWheel(processing.event.MouseEvent event) {
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
