package calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

import calculator.model.Polynomial;
import calculator.model.operations.*;
import calculator.view.Popup;
import calculator.view.View;

public class Controller {
    View view;

    public Controller() {
        view = new View();
        view.setVisible(true);

        view.setAddButtonListener(wrapInActionListener((first, second) -> {
            var res = Addition.apply(first, second);
            new Popup("First: " + first, "Second: " + second, "Result: " + res);
        }));

        view.setSubtractButtonListener(wrapInActionListener((first, second) -> {
            var res = Subtraction.apply(first, second);
            new Popup("First: " + first, "Second: " + second, "Result: " + res);
        }));

        view.setMultiplyButtonListener(wrapInActionListener((first, second) -> {
            var res = Multiplication.apply(first, second);
            new Popup("First: " + first, "Second: " + second, "Result: " + res);
        }));

        view.setDivideButtonListener(wrapInActionListener((first, second) -> {
            var res = Division.apply(first, second);
            new Popup("First: " + first, "Second: " + second, "Quotient: " + res.get(0), "Reminder: " + res.get(1));
        }));

        view.setIntegrateButtonListener(wrapInActionListener((first, second) -> {
            var res1 = Integration.apply(first);
            var res2 = Integration.apply(second);
            new Popup("First: " + first, "Integrated: " + res1, "",
                    "Second: " + second, "Integrated: " + res2);
        }));

        view.setDifferentiateButtonListener(wrapInActionListener((first, second) -> {
            var res1 = Differentiation.apply(first);
            var res2 = Differentiation.apply(second);
            new Popup("First: " + first, "Differentiated: " + res1, "",
                    "Second: " + second, "Differentiated: " + res2);
        }));
    }

    private ActionListener wrapInActionListener(BiConsumer<Polynomial, Polynomial> function) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    Polynomial first = new Polynomial(view.getFirstPolynomialString());
                    Polynomial second = new Polynomial(view.getSecondPolynomialString());

                    function.accept(first, second);
                } catch (IllegalArgumentException exception) {
                    new Popup(exception.getMessage());
                }
            }

        };
    }
}
