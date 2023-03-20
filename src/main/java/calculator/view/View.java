package calculator.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import calculator.model.Polynomial;
import calculator.model.operations.*;

public class View extends JFrame {
    private JTextField firstPolynomialField;
    private JTextField secondPolynomialField;

    private JButton addButton;
    private JButton divideButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton integrateButton;
    private JButton differentiateButton;

    public View() {
        JLabel titleLabel = new JLabel("Polynomial calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 10, 300, 30);
        this.add(titleLabel);

        this.firstPolynomialField = new JTextField();
        this.firstPolynomialField.setBounds(50, 100, 400, 30);
        this.add(this.firstPolynomialField);

        this.secondPolynomialField = new JTextField();
        this.secondPolynomialField.setBounds(50, 200, 400, 30);
        this.add(this.secondPolynomialField);

        this.addButton = new JButton("+");
        this.addButton.setBounds(50, 300, 50, 50);
        this.add(this.addButton);

        this.subtractButton = new JButton("-");
        this.subtractButton.setBounds(167, 300, 50, 50);
        this.add(this.subtractButton);

        this.multiplyButton = new JButton("*");
        this.multiplyButton.setBounds(284, 300, 50, 50);
        this.add(this.multiplyButton);

        this.divideButton = new JButton("/");
        this.divideButton.setBounds(400, 300, 50, 50);
        this.add(this.divideButton);

        this.integrateButton = new JButton("integrate");
        this.integrateButton.setBounds(50, 370, 200, 40);
        this.add(this.integrateButton);

        this.differentiateButton = new JButton("differentiate");
        this.differentiateButton.setBounds(250, 370, 200, 40);
        this.add(this.differentiateButton);

        this.setLayout(null);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addActionListeners();

    }

    private void addActionListeners() {

        this.addButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res = Addition.apply(first, second);

                new Popup("First: " + first, "Second: " + second, "Result: " + res);
            }
        }));

        this.subtractButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res = Subtraction.apply(first, second);

                new Popup("First: " + first, "Second: " + second, "Result: " + res);
            }
        }));

        this.multiplyButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res = Multiplication.apply(first, second);

                new Popup("First: " + first, "Second: " + second, "Result: " + res);
            }
        }));

        this.divideButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res = Division.apply(first, second);

                new Popup("First: " + first, "Second: " + second, "Quotient: " + res.get(0), "Reminder: " + res.get(1));
            }
        }));

        this.integrateButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res1 = Integration.apply(first);
                var res2 = Integration.apply(second);

                new Popup("First: " + first, "Integrated: " + res1, "",
                        "Second: " + second, "Integrated: " + res2);
            }
        }));

        this.differentiateButton.addActionListener(wrapActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial first = getFirstPolynomial();
                Polynomial second = getSecondPolynomial();

                var res1 = Differentiation.apply(first);
                var res2 = Differentiation.apply(second);

                new Popup("First: " + first, "Differentiated: " + res1, "",
                        "Second: " + second, "Differentiated: " + res2);
            }
        }));
    }

    private Polynomial getFirstPolynomial() {
        return new Polynomial(this.firstPolynomialField.getText());
    }

    private Polynomial getSecondPolynomial() {
        return new Polynomial(this.secondPolynomialField.getText());
    }

    private ActionListener wrapActionListener(ActionListener listener) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    listener.actionPerformed(event);
                } catch (IllegalArgumentException exception) {
                    new Popup(exception.getMessage());
                }
            }

        };
    }

}
