package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final Controller controller = new Controller();
    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame("SimpleGUIWithFileChooser");

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeStringOnCurrentFile(textArea.getText());
                } catch (final IOException exc) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        canvas.add(save, BorderLayout.SOUTH);
        final JPanel browser = new JPanel();
        browser.setLayout(new BorderLayout());
        final JTextField pathShowArea = new JTextField();
        pathShowArea.setText(controller.getCurrentFilePathString());
        pathShowArea.setEditable(false);
        pathShowArea.setBackground(UIManager.getColor("TextField.inactiveBackground"));
        browser.add(pathShowArea, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();/////
                final int result = chooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File newFile = chooser.getSelectedFile();
                    controller.setCurrentFile(newFile);
                    pathShowArea.setText(controller.getCurrentFilePathString());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Error selecting file");
                } 
            }
        });
        browser.add(browse, BorderLayout.LINE_END);
        canvas.add(browser, BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
