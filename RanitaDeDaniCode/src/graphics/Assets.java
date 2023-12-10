package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage player, ball, fly, wasp;
    public static void init(){
        player = Loader.ImageLoader("/player.png");
        ball = Loader.ImageLoader("/ball.png");
        fly = Loader.ImageLoader("/fly.png");
    }
}
