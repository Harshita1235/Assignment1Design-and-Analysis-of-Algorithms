static double closestPair(Point[] pts) {
    Point[] ptsSortedByX = pts.clone();
    Arrays.sort(ptsSortedByX, Comparator.comparingDouble(p -> p.x));
    Point[] ptsSortedByY = pts.clone();
    Arrays.sort(ptsSortedByY, Comparator.comparingDouble(p -> p.y));
    return closestPairRec(ptsSortedByX, ptsSortedByY);
}

private static double closestPairRec(Point[] X, Point[] Y) {
    int n = X.length;
    if (n <= 3) return bruteForce(X);  // base case

    int mid = n / 2;
    double midX = X[mid].x;

    Point[] XL = Arrays.copyOfRange(X, 0, mid);
    Point[] XR = Arrays.copyOfRange(X, mid, n);

    // Split Y according to midX
    List<Point> YL = new ArrayList<>();
    List<Point> YR = new ArrayList<>();
    for (Point p : Y) {
        if (p.x <= midX) YL.add(p);
        else YR.add(p);
    }

    double dl = closestPairRec(XL, YL.toArray(Point[]::new));
    double dr = closestPairRec(XR, YR.toArray(Point[]::new));
    double d = Math.min(dl, dr);

    // Build strip
    List<Point> strip = new ArrayList<>();
    for (Point p : Y) if (Math.abs(p.x - midX) < d) strip.add(p);

    // Check up to 7 neighbors in strip
    for (int i = 0; i < strip.size(); i++) {
        for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
            double dist = dist(strip.get(i), strip.get(j));
            if (dist < d) d = dist;
        }
    }
    return d;
}
