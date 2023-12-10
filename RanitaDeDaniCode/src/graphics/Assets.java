package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage player, tongue;
    public static BufferedImage fly;
    public static BufferedImage wasp;
    public static void init(){
        player = Loader.ImageLoader("/player.png");
        tongue = Loader.ImageLoader("/tongue.png");
    }
}
