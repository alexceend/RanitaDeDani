package graphics;
import java.awt.*;

import math.Vector2D;

public class Text {
    public static void drawText(Graphics g, String text, Point p, boolean center, Color color) {
        g.setColor(color);
        Vector2D position = new Vector2D(p.getX(), p.getY());

        if(center) {
            FontMetrics fm = g.getFontMetrics();
            p.setLocation(p.getX(), p.getY());
        }

        g.drawString(text, (int)position.getX(), (int)position.getY());

    }
}
