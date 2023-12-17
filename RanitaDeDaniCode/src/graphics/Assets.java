package graphics;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] exp = new BufferedImage[7];
    public static BufferedImage[] death = new BufferedImage[8];
    //public static BufferedImage[] hit = new BufferedImage[5];

    public static BufferedImage[] numbersImg = new BufferedImage[10];
    public static BufferedImage[] player = new BufferedImage[12];
    public static BufferedImage[] unlockedSkin = new BufferedImage[12];
    public static BufferedImage[] lockedSkin = new BufferedImage[12];
    public static BufferedImage ball, fly, wasp, xImg, lifeIco, activeSA1, inactiveSA1, bg, coinSpr;
    public static BufferedImage orangeButtSQ, blueButtRec, greyButtonRec, greenCheckButt, redcrossButt, bluecrossButt, bluecheckButt;

    public static Font titleFontBig, titleFontMedium, titleFontTiny;
    public static Font normalFontBig, normalFontMedium, normalFontTiny;

    public static Clip explosion, playerShoot;

    public static void init(){
        ball = Loader.ImageLoader("/ball.png");
        fly = Loader.ImageLoader("/fly.png");
        wasp = Loader.ImageLoader("/wasp.png");
        xImg = Loader.ImageLoader("/numbers/10.png");
        lifeIco = Loader.ImageLoader("/lifesico.png");
        activeSA1 = Loader.ImageLoader("/activeSA1.png");
        inactiveSA1 = Loader.ImageLoader("/notActiveSA1.png");
        orangeButtSQ = Loader.ImageLoader("/ui/orangeb1.png");
        blueButtRec = Loader.ImageLoader("/ui/blueb1.png");
        greyButtonRec = Loader.ImageLoader("/ui/greyb1.png");
        greenCheckButt = Loader.ImageLoader("/ui/green_boxCheckmark.png");
        redcrossButt = Loader.ImageLoader("/ui/red_boxCross.png");
        bluecrossButt = Loader.ImageLoader("/ui/blue_boxCross.png");
        bluecheckButt = Loader.ImageLoader("/ui/blue_boxCheckmark.png");
        coinSpr = Loader.ImageLoader("/coin.png");

        explosion = Loader.loadSound("/sounds/explosion.wav");
        playerShoot = Loader.loadSound("/sounds/playerShoot.wav");

        titleFontBig = Loader.loadFont("/fonts/TOADFROG.ttf", 42);
        titleFontMedium = Loader.loadFont("/fonts/TOADFROG.ttf", 20);
        titleFontTiny = Loader.loadFont("/fonts/TOADFROG.ttf", 13);
        normalFontBig = Loader.loadFont("/fonts/BubblyFrog.ttf", 42);
        normalFontMedium = Loader.loadFont("/fonts/BubblyFrog.ttf", 20);
        normalFontTiny = Loader.loadFont("/fonts/BubblyFrog.ttf", 13);


        for(int i = 0; i < exp.length; i++){
            exp[i] = Loader.ImageLoader("/waterDrop/" + i + ".png");
        }
        for(int i = 0; i < player.length; i++){
            player[i] = Loader.ImageLoader("/skins/" + i + ".png");
        }
        for(int i = 0; i < unlockedSkin.length; i++){
            unlockedSkin[i] = Loader.ImageLoader("/unlocked/" + i + ".png");
        }
        for(int i = 0; i < lockedSkin.length; i++){
            lockedSkin[i] = Loader.ImageLoader("/locked/" + i + ".png");
        }
        for(int i = 0; i < death.length; i++){
            death[i] = Loader.ImageLoader("/playerDeath/" + i + ".png");
        }
        for(int i = 0; i < numbersImg.length; i++){
            numbersImg[i] = Loader.ImageLoader("/numbers/" + i + ".png");
        }
    }
}
