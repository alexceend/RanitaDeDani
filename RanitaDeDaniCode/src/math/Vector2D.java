package math;

import java.awt.*;

public class Vector2D {
    private static final double RAD_90 = Math.PI/2;
    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D(Point p) {
        this(p.x, p.y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }
    public double getAngle(){
        return Math.acos(getX() / getMagnitude());
    }
    public double getActualAngle(Point mousePoint) {
        return ((mousePoint.getY() > 400 ? 1 : -1) * getAngle()) + RAD_90;
    }

    public Vector2D multiply(double number) {
        this.x *= number;
        this.y *= number;
        return this;
    }
    public Vector2D toUnitary() {
        double mag = getMagnitude();
        return new Vector2D(x/mag, y/mag);
    }

    public static Vector2D fromLocations(Point p1, Point p2) {
        Point out = p2.getLocation();
        out.translate(-1 * p1.x, -1 * p1.y); // A(x, y); B(x', y') ; AB(x' - x, y' - y)
        return new Vector2D(out);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
