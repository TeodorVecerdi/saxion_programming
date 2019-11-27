public class Utils {
    public static float Map (float value, float in_min, float in_max, float out_min, float out_max) {
        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static boolean PointInTriangle (Point2f p, Point2f p0, Point2f p1, Point2f p2) {
        var s = p0.y * p2.x - p0.x * p2.y + (p2.y - p0.y) * p.x + (p0.x - p2.x) * p.y;
        var t = p0.x * p1.y - p0.y * p1.x + (p0.y - p1.y) * p.x + (p1.x - p0.x) * p.y;
        if ((s < 0) != (t < 0)) return false;
        var A = -p1.y * p2.x + p0.y * (p2.x - p1.x) + p0.x * (p1.y - p2.y) + p1.x * p2.y;
        return A < 0 ? (s <= 0 && s + t >= A) : (s >= 0 && s + t <= A);
    }

    public static boolean PointInTriangle (Point2f p, Triangle t) {
        return PointInTriangle(p, t.v1, t.v2, t.v3);
    }

    public static Triangle[] Square2Tri (float x, float y, float w, float h) {
        return new Triangle[] {new Triangle(x, y, x + w, y, x, y + h), new Triangle(x + w, y, x, y + h, x + w, y + h)};
    }

    public static float distanceSq (float x1, float y1, float x2, float y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    public static float distanceSq (Point2f a, Point2f b) {
        return distanceSq(a.x, a.y, b.x, b.y);
    }
    
    public static Point2f TriangleCenter(Triangle t) {
        float cx = (t.v1.x + t.v2.x + t.v3.x)/3f;
        float cy = (t.v1.y + t.v2.y + t.v3.y)/3f;
        return Point2f.From(cx,cy);
    }


    public static class Point2f {
        public float x;
        public float y;

        public Point2f (float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Point2f (Point2f p) {
            this.x = p.x;
            this.y = p.y;
        }

        public static Point2f From (float x, float y) {
            return new Point2f(x, y);
        }

        public void Set (float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void Set (Point2f p) {
            this.x = p.x;
            this.y = p.y;
        }

        public Point2f Copy () {
            return new Point2f(this);
        }

        @Override
        public String toString () {
            return String.format("[%.2f, %.2f]", x, y);
        }
    }

    public static class Triangle {
        public Point2f v1;
        public Point2f v2;
        public Point2f v3;

        public Triangle (float x1, float y1, float x2, float y2, float x3, float y3) {
            this.v1 = Point2f.From(x1, y1);
            this.v2 = Point2f.From(x2, y2);
            this.v3 = Point2f.From(x3, y3);
        }

        public Triangle (Point2f v1, Point2f v2, Point2f v3) {
            this.v1 = v1.Copy();
            this.v2 = v2.Copy();
            this.v3 = v3.Copy();
        }

        @Override
        public String toString () {
            return String.format("[(%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f)]", v1.x, v1.y, v2.x, v2.y, v3.x, v3.y);
        }
    }
}
