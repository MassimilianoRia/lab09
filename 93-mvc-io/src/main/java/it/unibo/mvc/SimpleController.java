package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * A very simple controller implementation.
 * 
 */
public final class SimpleController implements Controller {

    private String currString;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setString(String s) {
        this.currString = s;
    }

    @Override
    public String getString() {
        return this.currString;
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return history;
    }

    @Override
    public void printCurrentString() throws IllegalStateException {
        if (this.currString == null) {
            throw new IllegalStateException("Current string is unset");
        }
        System.out.println(this.currString);
        history.add(this.currString);
    }

}
