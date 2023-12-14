package gameObjects;

import javax.swing.filechooser.FileSystemView;
import java.util.Random;

public class Constants {
    // frame dimensions
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    //
    public static final long GAME_OVER_TIME = 3000;

    //math
    public static final Random RANDOM = new Random();

    //player cosas
    public static final int FIRERATE = 100;
    public static final int SA1FIRERATE = 10000;
    public static final double DELTAANGLE = 0.1;
    public static final long FLICKER_TIME = 200;
    public static final long INVINCIBLE_TIME = 3000;
    public static final int VIDAS_INICIO = 3;
    public static final int BALAS_INICIO = 5;

    //BALL PROP
    public static final double BALL_VEL = 10.0;

    //FLY PROPS
    public static final double FLY_VEL = 2.0;
    public static final int FLY_SCORE = 1;

    //WASP PROPS
    public static final double WASP_VEL = 3.0;
    public static final int WASP_SCORE = 5;

    //GUI
    public static final String PLAY = "PLAY";
    public static final String EXIT = "EXIT";
    public static final String SETTINGS = "SETTINGS";
    public static final String RETURN = "RETURN";
    public static final String HIGH_SCORE = "HIGHSCORE";
    public static final String SCORE = "SCORE";
    public static final String DATE = "DATE";

    public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
            "\\Frog_Game\\data.json";

}
