package states;

import gameObjects.Constants;
import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends State{

    ArrayList<Button> buttons = new ArrayList<Button>();
    public MenuState(){
        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2 - Assets.greyButtonRec.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButtonRec.getHeight() * 4,
                Constants.PLAY,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new GameState());
                        Player.restartProperties();
                    }
                }
        ));

        buttons.add(new Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2 - Assets.greyButtonRec.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyButtonRec.getHeight() /2 - 300,
                Constants.EXIT,
                new Action() {
                    @Override
                    public void doAction() {
                        System.exit(0);
                    }
                }
        ));

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
    }

    @Override
    public void update() {
        for(Button b : buttons){
            b.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for(Button b : buttons){
            b.draw(g);
        }
    }
}
