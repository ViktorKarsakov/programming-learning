import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double X = scanner.nextDouble();
        double Y = scanner.nextDouble();
        double ax = scanner.nextDouble();
        double ay = scanner.nextDouble();
        double bx = scanner.nextDouble();
        double by = scanner.nextDouble();
        double cx = scanner.nextDouble();
        double cy = scanner.nextDouble();
        double dx = scanner.nextDouble();
        double dy = scanner.nextDouble();

        double vX = bx - ax;
        double vY = by - ay;

        double lenAB = Math.sqrt((vX * vX) + (vY * vY));

        double k = lenAB / X;
        double cos = vX / lenAB;
        double sin = vY / lenAB;

        double a = 1.0 - k * cos;
        double b = -k * sin;
        double d2 = k * sin;
        double e = 1.0 - k * cos;

        double coefficient = a * e - b * d2;

        double p = (ax * e - b * ay) / coefficient;
        double q = (a * ay - ax * d2) / coefficient;

        System.out.printf("%.4f %.4f%n", p, q);
    }
}