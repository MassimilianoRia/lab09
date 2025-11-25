package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame("SimpleGUI");
    private final Controller controller = new SimpleController();

    public SimpleGUI() {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JPanel buttons = new JPanel();
        canvas.add(buttons, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setString(textField.getText());
                controller.printCurrentString();
                textField.setText("");
            }
        });
        buttons.add(print);
        final JButton showHistory = new JButton("Show History");
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                final var historyList = controller.getPrintedStringsHistory();
                for (final var tmp : historyList) {
                    textArea.append(tmp + "\n");
                }
            }
        });
        buttons.add(showHistory);
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
        new SimpleGUI().display();
    }

}
