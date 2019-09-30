package collision_testing.test3_rect_line;

import collision_testing.test3_rect_line.misc.Input;

public class RectCollider2D extends Loopable {
    public float x, y;
    public float width, height;
    public Line top, right, bottom, left;
    public float speed;

    public RectCollider2D(float x, float y, float width, float height, float speed) {
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
    public void render() {
//        App.Instance.noFill();
//        App.Instance.stroke(0xffff0000);
//        App.Instance.strokeWeight(5f);
//        App.Instance.rect(x, y, width, height);
    }

    @Override
    public void update() {
        float dx = Input.GetAxisHorizontal() * speed;
        float dy = Input.GetAxisVertical() * speed;
        Move(dx, dy);
    }

    private void Move(float dx, float dy) {
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
