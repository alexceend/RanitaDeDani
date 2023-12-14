package states;

import gameObjects.Constants;
import gameObjects.Player;
import graphics.Assets;
import io.JSONParser;
import io.SkinData;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SkinState extends State {
    private Button returnButton;
    private Button roboFrogbutton;

    private SkinData[] auxArray;

    public SkinState() throws FileNotFoundException {
        //return
        returnButton = new ui.Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH - Assets.greyButtonRec.getWidth() * 2,
                Constants.HEIGHT / 2 + 100,
                Constants.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        );

        //roboFrogButton
        roboFrogbutton = new ui.Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                Constants.WIDTH / 2,
                Constants.HEIGHT / 4,
                Constants.ROBO_FROG,
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la robo a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            Player.skins[1] = arrList.get(1).getComprada();
                            if (Player.skins[1]) {
                                    Player.skinIndex = 1;
                            }else{
                                State.changeState(new BuyState(1));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        ArrayList<SkinData> dataList = JSONParser.readSkinFile();
        for (SkinData d : dataList) {
            if (Player.skins[d.getIndex()]) { //&& d.getValor() == true
                Player.skinIndex = d.getIndex();
            }
        }
    }

    @Override
    public void update() throws FileNotFoundException {
        returnButton.update();
        roboFrogbutton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);
        roboFrogbutton.draw(g);
    }
}
