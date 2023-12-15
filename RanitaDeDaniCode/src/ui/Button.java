package ui;

import graphics.Assets;
import graphics.Text;
import input.Mouse;
import input.MouseGUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Button {
    private BufferedImage mouseOutImg, mouseInImage;
    private boolean mouseIn;
    private Rectangle boundingBox;
    String text;
    private Action action;

    public Button(
            BufferedImage mouseOutImg,
            BufferedImage mouseInImage,
            int x, int y,
            String text,
            Action action
    ) {
        this.mouseOutImg = mouseOutImg;
        this.mouseInImage = mouseInImage;
        this.text = text;
        this.boundingBox = new Rectangle(x, y, mouseInImage.getWidth(), mouseInImage.getHeight());
        this.action = action;
    }

    public void update() throws FileNotFoundException {
        if (boundingBox.contains(MouseGUI.X, MouseGUI.Y)) {
            mouseIn = true;
        } else mouseIn = false;

        if(mouseIn && MouseGUI.MLB){
            action.doAction();
        }
    }

    public void draw(Graphics g) {
        if(mouseIn){
            g.drawImage(mouseInImage, boundingBox.x, boundingBox.y, null);
        }else {
            g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }

        Text.drawText(
                g,
                text,
                new Point( (int) (boundingBox.getX() + boundingBox.getWidth()/2 - 20),
                        (int) (boundingBox.getY() + boundingBox.getHeight()/2)),
                true,
                Color.black,
                Assets.titleFontMedium
        );
    }
}
