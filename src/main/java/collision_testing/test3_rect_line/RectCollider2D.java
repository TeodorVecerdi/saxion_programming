package collision_testing.test3_rect_line;

import collision_testing.test3_rect_line.misc.Input;
import processing.core.PVector;

import java.util.ArrayList;

public class RectCollider2D extends Loopable {
    public float x, y;
    public float width, height;
    public Line top, right, bottom, left;
    public float speed;

    public RectCollider2D (float x, float y, float width, float height, float speed) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        top = new Line(x, y, x + width, y, 0xff00ff00);
        right = new Line(x + width, y, x + width, y + height, 0xff00ff00);
        bottom = new Line(x, y + height, x + width, y + height, 0xff00ff00);
        left = new Line(x, y, x, y + height, 0xff00ff00);
    }

    @Override
    public void render () {
        //        App.Instance.noFill();
        //        App.Instance.stroke(0xffff0000);
        //        App.Instance.strokeWeight(5f);
        //        App.Instance.rect(x, y, width, height);
    }

    @Override
    public void update () {
        float dx = Input.GetAxisHorizontal() * speed;
        float dy = Input.GetAxisVertical() * speed;
        Move(dx, dy);
    }

    public ArrayList<CollisionPoint> GetIntersectionsWith (Line l) {
        var intersections = new ArrayList<CollisionPoint>();
        var tI = App.Intersects(top, l);
        var rI = App.Intersects(right, l);
        var bI = App.Intersects(bottom, l);
        var lI = App.Intersects(left, l);
        if (tI.x != 0 && tI.y != 0) intersections.add(getResponseFromCollisionOrigin(tI, top));
        if (rI.x != 0 && rI.y != 0) intersections.add(getResponseFromCollisionOrigin(rI, right));
        if (bI.x != 0 && bI.y != 0) intersections.add(getResponseFromCollisionOrigin(bI, bottom));
        if (lI.x != 0 && lI.y != 0) intersections.add(getResponseFromCollisionOrigin(lI, left));
        return intersections;
    }

    private CollisionPoint getResponseFromCollisionOrigin (PVector origin, Line side) {
        PVector response;
        float da = PVector.dist(side.A, origin);
        float db = PVector.dist(side.B, origin);
        if (da < db) {
            response = PVector.sub(origin, side.A);
        } else {
            response = PVector.sub(origin, side.B);
        }
        return new CollisionPoint(origin, response);
    }

    public void Move (float dx, float dy) {
        x += dx;
        top.A.x += dx;
        right.A.x += dx;
        bottom.A.x += dx;
        left.A.x += dx;
        top.B.x += dx;
        right.B.x += dx;
        bottom.B.x += dx;
        left.B.x += dx;
        y += dy;
        top.A.y += dy;
        right.A.y += dy;
        bottom.A.y += dy;
        left.A.y += dy;
        top.B.y += dy;
        right.B.y += dy;
        bottom.B.y += dy;
        left.B.y += dy;
    }
}
