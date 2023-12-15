package states;

import gameObjects.Constants;
import graphics.Assets;
import graphics.Text;
import io.JSONParser;
import io.ScoreData;
import ui.Action;
import ui.Button;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ScoreState extends State {
    private Button returnButton;

    private PriorityQueue<ScoreData> highScore;
    private Comparator<ScoreData> scoreComparator;

    private ScoreData[] auxArray;

    public ScoreState() {
        returnButton = new Button(
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
        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData o1, ScoreData o2) {
                return o1.getScore() < o2.getScore() ? -1 : o1.getScore() > o2.getScore() ? 1 : 0;
            }
        };

        highScore = new PriorityQueue<ScoreData>(10, scoreComparator);

        try {
            ArrayList<ScoreData> dataList = JSONParser.readFile();
            for(ScoreData d: dataList){
                highScore.add(d);
            }
            while (highScore.size() > 10){
                highScore.poll();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() throws FileNotFoundException {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        auxArray = highScore.toArray(new ScoreData[highScore.size()]);

        Arrays.sort(auxArray, scoreComparator);

        Point scorePos = new Point(Constants.WIDTH / 2 - 300, 100);
        Point datePos = new Point(Constants.WIDTH / 2 + 100, 100);

        Text.drawText(g, Constants.SCORE, scorePos, false, Color.white, Assets.titleFontBig);
        Text.drawText(g, Constants.DATE, datePos, false, Color.white, Assets.titleFontBig);

        scorePos.setLocation(scorePos.getX(), scorePos.getY() + 40);
        datePos.setLocation(datePos.getX(), datePos.getY() + 40);

        for (int i = auxArray.length - 1; i > -1; i--) {
            ScoreData d = auxArray[i];

            Text.drawText(g, Integer.toString(d.getScore()), scorePos, false, Color.white, Assets.normalFontMedium);
            Text.drawText(g, d.getDate(), datePos, false, Color.white, Assets.normalFontMedium);

            scorePos.setLocation(scorePos.getX(), scorePos.getY() + 40);
            datePos.setLocation(datePos.getX(), datePos.getY() + 40);
        }
    }
}
