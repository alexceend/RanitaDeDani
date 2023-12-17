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

    private Button roboFrogbutton, defaultFrogButton, xmasdomButton, samuraiDanyButtton, rayfrogButtton, angelaButton;
    private Button luvyButton, snoopFrogbutton, trefrog, johnnyJoFrog, alexButton, whiskasButton;

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

        //X-MAS Dom button
        xmasdomButton = new ui.Button(
                skinsButton[2],
                skinsButton[2],
                300,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la xmas a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[2] = arrList.get(2).getComprada();
                            if (Player.skins[2]) {
                                Player.skinIndex = 2;
                            }else{
                                State.changeState(new BuyState(2));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //Samurai dany
        samuraiDanyButtton = new ui.Button(
                skinsButton[3],
                skinsButton[3],
                400,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[3] = arrList.get(3).getComprada();
                            if (Player.skins[3]) {
                                Player.skinIndex = 3;
                            }else{
                                State.changeState(new BuyState(3));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //Rayfrog
        rayfrogButtton = new ui.Button(
                skinsButton[4],
                skinsButton[4],
                500,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[4] = arrList.get(4).getComprada();
                            if (Player.skins[4]) {
                                Player.skinIndex = 4;
                            }else{
                                State.changeState(new BuyState(4));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //angela
        angelaButton = new ui.Button(
                skinsButton[5],
                skinsButton[5],
                600,
                200,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[5] = arrList.get(5).getComprada();
                            if (Player.skins[5]) {
                                Player.skinIndex = 5;
                            }else{
                                State.changeState(new BuyState(5));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //luvy
        luvyButton = new ui.Button(
                skinsButton[6],
                skinsButton[6],
                100,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[6] = arrList.get(6).getComprada();
                            if (Player.skins[6]) {
                                Player.skinIndex = 6;
                            }else{
                                State.changeState(new BuyState(6));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //snoopfrog
        snoopFrogbutton = new ui.Button(
                skinsButton[7],
                skinsButton[7],
                200,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[7] = arrList.get(7).getComprada();
                            if (Player.skins[7]) {
                                Player.skinIndex = 7;
                            }else{
                                State.changeState(new BuyState(7));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //terffrog
        trefrog = new ui.Button(
                skinsButton[8],
                skinsButton[8],
                300,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[8] = arrList.get(8).getComprada();
                            if (Player.skins[8]) {
                                Player.skinIndex = 8;
                            }else{
                                State.changeState(new BuyState(8));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //jhonnyjoestar
        johnnyJoFrog = new ui.Button(
                skinsButton[9],
                skinsButton[9],
                400,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[9] = arrList.get(9).getComprada();
                            if (Player.skins[9]) {
                                Player.skinIndex = 9;
                            }else{
                                State.changeState(new BuyState(9));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //alex
        alexButton = new ui.Button(
                skinsButton[10],
                skinsButton[10],
                500,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[10] = arrList.get(10).getComprada();
                            if (Player.skins[10]) {
                                Player.skinIndex = 10;
                            }else{
                                State.changeState(new BuyState(10));
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        //whiskas
        whiskasButton = new ui.Button(
                skinsButton[11],
                skinsButton[11],
                600,
                300,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        //Pone en el boolean todas a false y la samurai a true
                        try {
                            ArrayList<SkinData> arrList = JSONParser.readSkinFile();

                            for (int i = 0; i < Player.skins.length; i++){
                                Player.skins[i] = false;
                            }

                            Player.skins[11] = arrList.get(11).getComprada();
                            if (Player.skins[11]) {
                                Player.skinIndex = 11;
                            }else{
                                State.changeState(new BuyState(11));
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
        xmasdomButton.update();
        samuraiDanyButtton.update();
        rayfrogButtton.update();
        angelaButton.update();
        luvyButton.update();
        snoopFrogbutton.update();
        trefrog.update();
        johnnyJoFrog.update();
        alexButton.update();
        whiskasButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player[Player.skinIndex], 400, 100, null);
        returnButton.draw(g);
        defaultFrogButton.draw(g);
        roboFrogbutton.draw(g);
        xmasdomButton.draw(g);
        samuraiDanyButtton.draw(g);
        rayfrogButtton.draw(g);
        angelaButton.draw(g);
        luvyButton.draw(g);
        snoopFrogbutton.draw(g);
        trefrog.draw(g);
        johnnyJoFrog.draw(g);
        alexButton.draw(g);
        whiskasButton.draw(g);
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
