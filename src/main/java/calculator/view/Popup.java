package calculator.view;

import java.awt.Font;

import javax.swing.*;

public class Popup extends JFrame {
    public Popup(String... lines) {
        int idx = 0;

        for (String line : lines) {
            JLabel textLabel = new JLabel(line);
            textLabel.setBounds(0, 35 * idx, 600, 30);
            textLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            this.add(textLabel);
            idx++;
        }

        this.setLayout(null);
        this.setSize(600, 300);
        this.setVisible(true);
    }
}
