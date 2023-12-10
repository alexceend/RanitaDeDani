package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] exp = new BufferedImage[7];
    public static BufferedImage player, ball, fly, wasp;

    public static void init(){
        player = Loader.ImageLoader("/player.png");
        ball = Loader.ImageLoader("/ball.png");
        fly = Loader.ImageLoader("/fly.png");
        wasp = Loader.ImageLoader("/wasp.png");

        for(int i = 0; i < exp.length; i++){
            exp[i] = Loader.ImageLoader("/waterDrop/" + i + ".png");
        }
    }
}
