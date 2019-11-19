package collision_testing.test2_line_line;

import collision_testing.test2_line_line.misc.Constants;
import processing.core.PVector;

public class Line {
    public PVector A = Constants.VECTOR2F_ZERO.copy();
    public PVector B = Constants.VECTOR2F_ONE.copy();
    public int Color;

    public Line (float xA, float yA, float xB, float yB, int color) {
        A.x = xA;
        A.y = yA;
        B.x = xB;
        B.y = yB;
        Color = color;
    }

    public Line (PVector A, PVector B, int color) {
        this(A.x, A.y, B.x, B.y, color);
    }

    public Line (Line L, int color) {
        this(L.A.x, L.A.y, L.B.x, L.B.y, color);
    }

    public void render () {
        App.Instance.stroke(Color);
        App.Instance.strokeWeight(2);
        App.Instance.line(A.x, A.y, B.x, B.y);
        App.Instance.strokeWeight(8);
        App.Instance.point(A.x, A.y);
        App.Instance.point(B.x, B.y);
    }
}
