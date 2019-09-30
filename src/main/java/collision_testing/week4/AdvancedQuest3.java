package collision_testing.week4;

public class AdvancedQuest3 {
    public static void main (String[] args) {
        System.out.println(triangularNumber(3));
        System.out.println(factorial(6));
        System.out.println(gcd(8, 6));
    }

    private static int triangularNumber (int n) {
        int tn = 0;
        for (int i = n; i > 0; i--) tn += i;
        return tn;
    }

    private static long factorial (int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }

    private static int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static boolean isPointInsideCircle (float px, float py, float cx, float cy, float cr) {
        return ((cx - px) * (cx - px) + (cy - py) * (cy - py)) <= cr * cr;
    }

    private static boolean isPointInsideRect (float px, float py, float rx, float ry, float rw, float rh) {
        return px >= rx && px <= rx + rw && py >= ry && py <= py + rh;
    }
}
