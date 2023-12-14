package states;

import java.awt.*;
import java.io.FileNotFoundException;

public abstract class State {

    private static State currentState = null;

    public static State getCurrentState() {
        return currentState;
    }

    public static void changeState(State newState){
        currentState = newState;
    }

    public abstract void update() throws FileNotFoundException;
    public abstract void draw(Graphics g);
}
