package collision_testing.test3_rect_line;

import collision_testing.test3_rect_line.misc.Constants;
import collision_testing.test3_rect_line.misc.Input;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {

    public static App Instance;
    public List<Loopable> loopableList = new ArrayList<>();
    List<PVector> points = new ArrayList<>();
    private Line l1, l2;
    private RectCollider2D rect;

    public static void main(String[] args) {
        PApplet.main("collision_testing.test3_rect_line.App");
    }

    public void settings() {
        Instance = this;
        size(800, 800);
        smooth(8);

        l1 = new Line(100, 100, 200, 100, 0xffff0000);
        rect = new RectCollider2D(width / 2f, height / 2f, 150, 100, 2.5f);
    }

    private void update() {
        points.clear();
        if (Input.IsKeyDown(KeyEvent.VK_1)) l1.A = new PVector(mouseX, mouseY);
        if (Input.IsKeyDown(KeyEvent.VK_2)) l1.B = new PVector(mouseX, mouseY);
        for (Loopable l : loopableList) {
            l.update();
            if (!l.equals(l1) && l instanceof Line) {
                PVector collisionPoint = Intersects(l1, (Line) l);
                if (collisionPoint.x != 0 && collisionPoint.y != 0) {
                    points.add(collisionPoint);
                }
            }
        }
    }

    private void render() {
        background(0x44);
        for (Loopable l : loopableList) {
            l.render();
        }
        stroke(0xff0000ff);
        strokeWeight(3f);
        for (PVector p : points) {
            point(p.x, p.y);
        }
    }

    public void draw() {
        update();
        render();
    }

    private PVector Intersects(Line A, Line B) {
        PVector a1 = A.A;
        PVector a2 = A.B;
        PVector b1 = B.A;
        PVector b2 = B.B;

        PVector b = PVector.sub(a2, a1);
        PVector d = PVector.sub(b2, b1);
        float bDotDPerp = b.x * d.y - b.y * d.x;

        // if b dot d == 0, it means the lines are parallel so have infinite intersection points
        if (bDotDPerp == 0)
            return Constants.VECTOR2F_ZERO.copy();

        PVector c = PVector.sub(b1, a1);
        float t = (c.x * d.y - c.y * d.x) / bDotDPerp;
        if (t < 0 || t > 1)
            return Constants.VECTOR2F_ZERO.copy();

        float u = (c.x * b.y - c.y * b.x) / bDotDPerp;
        if (u < 0 || u > 1)
            return Constants.VECTOR2F_ZERO.copy();

        return PVector.add(a1, PVector.mult(b, t));
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
    public void mousePressed(processing.event.MouseEvent event) {
        super.mousePressed(event);
        Input.PressButton(event.getButton());
    }

    @Override
    public void mouseReleased(processing.event.MouseEvent event) {
        super.mouseReleased(event);
        Input.ReleaseButton(event.getButton());
    }
}
