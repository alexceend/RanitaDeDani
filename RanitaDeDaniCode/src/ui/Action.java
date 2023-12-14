package ui;

import java.io.FileNotFoundException;

public interface Action {
    public abstract void doAction() throws FileNotFoundException;
}
