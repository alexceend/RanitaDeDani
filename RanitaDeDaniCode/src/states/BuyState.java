package states;

import gameObjects.Constants;
import gameObjects.Message;
import gameObjects.Player;
import graphics.Assets;
import graphics.Text;
import io.JSONParser;
import io.MoneyData;
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
    private boolean cantBuy = false;

    public BuyState(int index) {
        this.index = index;
        cancelButton = new ui.Button(
                Assets.redcrossButt,
                Assets.bluecrossButt,
                Constants.WIDTH - Assets.greyButtonRec.getWidth() * 2 - 200,
                Constants.HEIGHT / 2 + 100,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        try {
                            State.changeState(new SkinState());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        acceptButton = new ui.Button(
                Assets.greenCheckButt,
                Assets.bluecheckButt,
                Constants.WIDTH - Assets.greyButtonRec.getWidth() * 2 + 100,
                Constants.HEIGHT / 2 + 100,
                "",
                new Action() {
                    @Override
                    public void doAction() {
                        if (Player.money > 100) {
                            try {
                                ArrayList<SkinData> beforeData = JSONParser.readSkinFile();
                                beforeData.get(index).setComprada(true);
                                Player.skinIndex = index;
                                File fich = new File(Constants.SKIN_PATH);
                                fich.delete();
                                JSONParser.writeSkinFile(beforeData);


                                ArrayList<MoneyData> beforeMoneyData = JSONParser.readMoneyData();
                                beforeMoneyData.get(0).setAmount(Player.money - 100);
                                File f = new File(Constants.MONEY_PATH);
                                f.delete();
                                JSONParser.writeMoneyFile(beforeMoneyData);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                State.changeState(new SkinState());
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }else {
                            cantBuy = true;
                        }
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
        if(cantBuy){
            Text.drawText(
                    g,
                    "NO TIENES SUFICIENTE DINERO PARA COMPRAR LA SKIN CROAK",
                    new Point(250,400),
                    true,
                    Color.red,
                    Assets.titleFontMedium
                    );
            cantBuy = false;
        }
        Text.drawText(g, "COMPRAR", new Point(Constants.WIDTH / 2 - 50, 100), true, Color.WHITE, Assets.titleFontBig);
        g.drawImage(Assets.player[index], Constants.WIDTH / 2 - Assets.player[index].getWidth() / 2, Constants.HEIGHT / 2 - 100, null);
        acceptButton.draw(g);
        cancelButton.draw(g);
        MenuState.drawMoney(g);
        if(Assets.player[index] == Assets.player[1]){ //RoboRana
            g.drawImage(Assets.coinSpr, Constants.WIDTH /2 - 60, Constants.HEIGHT/2 + 100, null);
            Text.drawText(g, "100", new Point(Constants.WIDTH / 2 - 10, Constants.HEIGHT / 2 + 125), true, Color.WHITE, Assets.normalFontBig);
        }
    }
}
