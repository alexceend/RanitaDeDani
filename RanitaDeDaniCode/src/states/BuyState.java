package states;

import gameObjects.Constants;
import graphics.Assets;
import io.JSONParser;
import io.SkinData;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BuyState extends State {

    private Button cancelButton;
    private Button acceptButton;
    private int index;

    public BuyState(int index){
        this.index = index;
        cancelButton = new ui.Button(
                Assets.redcrossButt,
                Assets.redcrossButt,
                Constants.WIDTH - Assets.greyButtonRec.getWidth() * 2 - 300,
                Constants.HEIGHT / 2 + 100,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        );
        acceptButton = new ui.Button(
                Assets.greenCheckButt,
                Assets.greenCheckButt,
                Constants.WIDTH - Assets.greyButtonRec.getWidth() * 2,
                Constants.HEIGHT / 2 + 100,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        try {
                            ArrayList<SkinData > beforeData = JSONParser.readSkinFile();
                            beforeData.get(index).setComprada(true);
                            File fich = new File(Constants.SKIN_SCORE_PATH);
                            fich.delete();
                            JSONParser.writeSkinFile(beforeData);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        State.changeState(new MenuState());
                    }
                }
        );
    }
    @Override
    public void update() throws FileNotFoundException {
        acceptButton.update();
        cancelButton.update();

    }

    @Override
    public void draw(Graphics g) {
        acceptButton.draw(g);
        cancelButton.draw(g);
    }
}
