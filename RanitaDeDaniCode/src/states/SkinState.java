package states;

import gameObjects.Constants;
import gameObjects.Player;
import graphics.Assets;
import io.JSONParser;
import io.SkinData;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class SkinState extends State {
    private Button returnButton;

    private Button roboFrogbutton, defaultFrogButton;

    private BufferedImage[] skinsButton = new BufferedImage[Player.skins.length];

    public SkinState() throws FileNotFoundException {
        checkUnlocked();

        File f = new File(Constants.SKIN_PATH);
        if(!f.exists()){
            try {
                f.createNewFile();
                FileWriter fileWriter = new FileWriter(f);
                PrintWriter out = new PrintWriter(fileWriter);
                out.println(Constants.JSONINIT);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //return
        returnButton = new ui.Button(
                Assets.greyButtonRec,
                Assets.blueButtRec,
                50,
                Constants.HEIGHT - 100,
                Constants.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                }
        );

        //defaultSkin
        defaultFrogButton = new ui.Button(
                skinsButton[0],
                skinsButton[0],
                100,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la robo a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[0] = arrList.get(0).getComprada();
                            if (Player.skins[0]) {
                                Player.skinIndex = 0;
                            }else{
                                State.changeState(new BuyState(0));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        //roboFrogButton
        roboFrogbutton = new ui.Button(
                skinsButton[1],
                skinsButton[1],
                200,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la robo a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

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
        checkUnlocked();
        returnButton.update();
        defaultFrogButton.update();
        roboFrogbutton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player[Player.skinIndex], 400, 100, null);
        returnButton.draw(g);
        defaultFrogButton.draw(g);
        roboFrogbutton.draw(g);
        MenuState.drawMoney(g);
    }

    public void checkUnlocked(){
        try {
            JSONParser.readSkinFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < Assets.player.length; i++){
            if(Player.unlocked_skins[i]){
                skinsButton[i] = Assets.unlockedSkin[i];
            }else {
                skinsButton[i] = Assets.lockedSkin[i];
            }
        }
    }
}
