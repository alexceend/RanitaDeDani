package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] exp = new BufferedImage[7];
    public static BufferedImage[] death = new BufferedImage[8];
    //public static BufferedImage[] hit = new BufferedImage[5];

    public static BufferedImage[] numbersImg = new BufferedImage[10];
    public static BufferedImage player, ball, fly, wasp, xImg, lifeIco;

    public static void init(){
        player = Loader.ImageLoader("/player.png");
        ball = Loader.ImageLoader("/ball.png");
        fly = Loader.ImageLoader("/fly.png");
        wasp = Loader.ImageLoader("/wasp.png");
        xImg = Loader.ImageLoader("/numbers/10.png");
        lifeIco = Loader.ImageLoader("/lifesico.png");

        for(int i = 0; i < exp.length; i++){
            exp[i] = Loader.ImageLoader("/waterDrop/" + i + ".png");
        }
        for(int i = 0; i < death.length; i++){
            death[i] = Loader.ImageLoader("/playerDeath/" + i + ".png");
        }
        for(int i = 0; i < numbersImg.length; i++){
            numbersImg[i] = Loader.ImageLoader("/numbers/" + i + ".png");
        }
    }
}
