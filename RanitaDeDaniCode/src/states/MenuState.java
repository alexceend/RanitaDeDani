package states;

import gameObjects.Constants;
import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuState extends State{

    ArrayList<Button> buttons = new ArrayList<Button>();
    public MenuState(){
        //GameState
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2 - Assets.greyButtonRec.getWidth() / 2,
                Constants.HEIGHT / 2 - 100,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new GameState());
                        Player.restartProperties();
                    }
                }
        ));

        //EXIT
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH - 250,
                Constants.HEIGHT - 100,
                Constants.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
                    }
                }
        ));

        //HighScore
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2 - Assets.greyButtonRec.getWidth() / 2,
                Constants.HEIGHT / 2,
                Constants.HIGH_SCORE,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new ScoreState());
                    }
                }
        ));

        //Skins
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2 - Assets.greyButtonRec.getWidth() / 2,
                Constants.HEIGHT / 2 + 100,
                Constants.SKINS,
                new Action() {
                    @Override
                    public void doAction() throws FileNotFoundException {
                        State.changeState(new SkinState());
                    }
                }
        ));
    }

    @Override
    public void update() throws FileNotFoundException {
        for(Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Button b : buttons){
            b.draw(g);
        }

        g.drawImage(Assets.player[0], 400 - Assets.player[0].getWidth()/2, 100, null);
    }

    public static void drawMoney(Graphics g) {
        Point p = new Point(650, 25);
        String scoreToString = Integer.toString(Player.money);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int) p.getX(), (int) p.getY(), null);
            p.setLocation(p.getX() + 20, p.getY());
        }
        g.drawImage(Assets.coinSpr,610, 20, null);
    }
}
