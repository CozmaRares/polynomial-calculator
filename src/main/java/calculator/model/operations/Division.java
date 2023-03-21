package calculator.model.operations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import calculator.model.Polynomial;

public class Division {
    private Division() {
    }

    public static List<Polynomial> apply(Polynomial first, Polynomial second) {
        if (second.getDegree() == -1)
            throw new IllegalArgumentException("Cannot divide by 0");

        Polynomial reminder = new Polynomial(first);
        Polynomial quotient = new Polynomial();

        while (reminder.getDegree() != 0 && reminder.getDegree() >= second.getDegree()) {
            int power = reminder.getDegree() - second.getDegree();
            BigDecimal coefficient = reminder
                    .getCoefficient(reminder.getDegree())
                    .divide(second.getCoefficient(second.getDegree()));

            Polynomial t = new Polynomial(coefficient + "x^" + power);
            quotient = Addition.apply(quotient, t);
            t = Multiplication.apply(t, second);
            reminder = Subtraction.apply(reminder, t);
        }

        var c = Arrays.asList(quotient, reminder);
        return c;
    }
}
