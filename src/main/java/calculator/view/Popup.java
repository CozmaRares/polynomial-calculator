package calculator.view;

import javax.swing.*;

public class Popup extends JFrame {
    public Popup(String... lines) {
        int idx = 0;

        for (String line : lines) {
            JLabel textLabel = new JLabel(line);
            textLabel.setBounds(0, 35 * idx, 300, 30);
            this.add(textLabel);
            idx++;
        }

        this.setLayout(null);
        this.setSize(300, 300);
        this.setVisible(true);
    }
}
