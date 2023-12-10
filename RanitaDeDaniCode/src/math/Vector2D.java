package math;

import java.awt.*;

public class Vector2D {
    private double x, y;

    public Vector2D() {
        this(0, 0);
    }
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D(Point p) {
        this(p.x, p.y);
    }
    public Vector2D(Vector2D v) {
        this(v.getX(), v.getY());
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D setDirection(double angle){
        return new Vector2D(Math.cos(angle) * getMagnitude(), Math.sin(angle) * getMagnitude());
    }

    public double getAngle(){
        return Math.acos(getX() / getMagnitude());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Vector2D fromLocations(Point p1, Point p2) {
        Point out = p2.getLocation();
        out.translate(-1 * p1.x, -1 * p1.y); // A(x, y); B(x', y') ; AB(x' - x, y' - y)
        return new Vector2D(out);
    }
    public Vector2D copy() {
        return new Vector2D(this);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector2D add(int velocity) {
        this.x += velocity;
        this.y += velocity;
        return new Vector2D(x, y);
    }
}
