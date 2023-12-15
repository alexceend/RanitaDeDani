package graphics;
import java.awt.*;

import math.Vector2D;

public class Text {
    public static void drawText(Graphics g, String text, Point p, boolean center, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);

        if(center) {
            FontMetrics fm = g.getFontMetrics();
            p.setLocation(p.getX() - fm.stringWidth(text)/4 , p.getY());
        }

        g.drawString(text, (int)p.getX(), (int)p.getY());

    }
}
