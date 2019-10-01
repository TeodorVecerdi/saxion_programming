package collision_testing.test2_line_line;

import collision_testing.test2_line_line.misc.Constants;
import collision_testing.test2_line_line.misc.Input;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.awt.event.KeyEvent;

public class App extends PApplet {

    public static App Instance;

    private Line l1;
    private Line l2;
    private float collides = 0f;
    private PVector intersection;
    private PVector intersection2;

    public static void main (String[] args) {
        PApplet.main("collision_testing.test2_line_line.App");
    }

    public void settings () {
        Instance = this;
        size(800, 800);
        smooth(8);
        l1 = new Line(50, 50, 150, 50, 0xff00ff00);
        l2 = new Line(170, 80, 220, 200, 0xff0000ff);
    }

    private void update () {
        if (Input.IsKeyDown(KeyEvent.VK_1)) {
            l1.A.x = mouseX;
            l1.A.y = mouseY;
        }
        if (Input.IsKeyDown(KeyEvent.VK_2)) {
            l1.B.x = mouseX;
            l1.B.y = mouseY;
        }
        if (Input.IsKeyDown(KeyEvent.VK_3)) {
            l2.A.x = mouseX;
            l2.A.y = mouseY;
        }
        if (Input.IsKeyDown(KeyEvent.VK_4)) {
            l2.B.x = mouseX;
            l2.B.y = mouseY;
        }
        //        collides = PVector.sub(l1.B, l1.A).dot(PVector.sub(l2.A, l1.A).cross(PVector.sub(l2.B, l1.A)));
        intersection = Intersects(l1.A, l1.B, l2.A, l2.B);
        intersection2 = Intersection2(l1, l2);
    }

    private void render () {
        background(0x44);
        l1.render();
        l2.render();
        strokeWeight(8);
        if (isBetween(l1.A, l1.B, intersection)) stroke(0xffff0000);
        else stroke(0xff00ff00);
        point(intersection.x, intersection.y);
        stroke(0xff);
        text("collision: " + intersection, 0, 25);

    }

    public void draw () {
        update();
        render();
    }

    private PVector Intersection2 (Line A, Line B) {
        float x1 = A.A.x, x2 = A.B.x, x3 = B.A.x, x4 = B.B.y;
        float y1 = A.A.y, y2 = A.B.y, y3 = B.A.y, y4 = B.B.y;
        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
        float u = -(((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)));
        if ((0.0f <= t && t <= 1.0f)) {
            float x = x1 + t * (x2 - x1);
            float y = y1 + t * (y2 - y1);
            return new PVector(x, y);
        } else if (0.0f <= u && u <= 1.0f) {
            float x = x3 + u * (x4 - x3);
            float y = y3 + u * (y4 - y3);
            return new PVector(x, y);
        }
        return Constants.VECTOR2F_ZERO.copy();
    }

    private PVector Intersection (Line A, Line B) {
        return new PVector(((A.A.x * A.B.y - A.A.y * A.B.x) * (B.A.x - B.B.x) - (A.A.x - A.B.x) * (B.A.x * B.B.y - B.A.y * B.B.x)) / (((A.A.x - A.B.x) * (B.A.y - B.B.y)) - ((A.A.y - A.B.y) * (B.A.x - B.B.x))), ((A.A.x * A.B.y - A.A.y * A.B.x) * (B.A.y - B.B.y) - ((A.A.y - A.B.y) * (B.A.x * B.B.y - B.A.y * B.B.x))) / (((A.A.x - A.B.x) * (B.A.y - B.B.y)) - ((A.A.y - A.B.y) * (B.A.x - B.B.x))));
    }

    private PVector Intersects (PVector a1, PVector a2, PVector b1, PVector b2) {
        PVector b = PVector.sub(a2, a1);
        PVector d = PVector.sub(b2, b1);
        float bDotDPerp = b.x * d.y - b.y * d.x;

        // if b dot d == 0, it means the lines are parallel so have infinite intersection points
        if (bDotDPerp == 0) return Constants.VECTOR2F_ZERO.copy();

        PVector c = PVector.sub(b1, a1);
        float t = (c.x * d.y - c.y * d.x) / bDotDPerp;
        if (t < 0 || t > 1) return Constants.VECTOR2F_ZERO.copy();

        float u = (c.x * b.y - c.y * b.x) / bDotDPerp;
        if (u < 0 || u > 1) return Constants.VECTOR2F_ZERO.copy();

        return PVector.add(a1, PVector.mult(b, t));
    }

    private boolean isBetween (PVector a, PVector b, PVector c) {
        var cross = (c.y - a.y) * (b.x - a.x) - (c.x - a.x) * (b.y - a.y);
        if (abs(cross) > 0.00001f) return false;

        var dotproduct = (c.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
        if (dotproduct < 0.0f) return false;

        var squaredlengthba = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
        return !(dotproduct > squaredlengthba);
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
