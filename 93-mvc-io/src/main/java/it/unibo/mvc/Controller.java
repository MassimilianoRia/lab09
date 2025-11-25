package it.unibo.mvc;

import java.util.List;

/**
 * A very simple controller interface that considers only the standard output.
 * 
 */
public interface Controller {

    /**
     * Sets the next string to print.
     *
     * @param n the string
     */
    void setString(String s);

    /**
     * Gets the next string to print.
     */
    String getString();

    /**
     * Gets the history of the printed strings in form of a 'List' of 'Strings'.
     */
    List<String> getPrintedStringsHistory();

    /**
     * Prints the current string.
     * 
     * @throws IllegalStateException if the current string is unset.
     */
    void printCurrentString();

}
