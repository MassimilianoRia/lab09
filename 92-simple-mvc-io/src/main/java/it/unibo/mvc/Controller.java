package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currFile;

    public Controller() {
        this.currFile = new File(System.getProperty("user.home") + File.separator + "output.txt");
    }

    public void setCurrentFile(final File file) {
        this.currFile = Objects.requireNonNull(file);
    }   

    public File getCurrentFile() {
        return this.currFile;
    }

    public String getCurrentFilePathString() {
        return this.currFile.getPath();
    }

    public void writeStringOnCurrentFile(final String string) throws IOException {
        Files.writeString(currFile.toPath(), string);
    }

}
