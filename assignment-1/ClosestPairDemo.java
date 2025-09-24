import java.util.*;

public class ClosestPairDemo {

    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
        @Override public String toString() { return "(" + x + "," + y + ")"; }
    }

    public static double closestPair(Point[] pts) {
        double best = Double.MAX_VALUE;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double d = dist(pts[i], pts[j]);
                if (d < best) best = d;
            }
        }
        return best;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static void main(String[] args) {
        Point[] pts = {
            new Point(2, 3), new Point(12, 30),
            new Point(40, 50), new Point(5, 1),
            new Point(12, 10), new Point(3, 4)
        };
        System.out.println("Points: " + Arrays.toString(pts));
        double result = closestPair(pts);
        System.out.println("Closest distance = " + result);
    }
}
