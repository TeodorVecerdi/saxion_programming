package collision_testing.test3_rect_line;

import processing.core.PVector;

public class CollisionPoint {
    public PVector origin;
    public PVector response;

    public CollisionPoint (PVector origin, PVector response) {
        this.origin = origin;
        this.response = response;
    }
}
