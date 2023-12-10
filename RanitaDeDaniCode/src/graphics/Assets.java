package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage player, tongue, fly, wasp;
    public static void init(){
        player = Loader.ImageLoader("/player.png");
        tongue = Loader.ImageLoader("/tongue.png");
    }
}
