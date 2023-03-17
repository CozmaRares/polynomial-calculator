package calculator.operations;

import java.util.ArrayList;
import java.util.Collection;

import calculator.Polynomial;

public class Division {
    private Division() {
    }

    public static Collection<Polynomial> apply(Polynomial first, Polynomial second) {
        Polynomial reminder = new Polynomial(first.getEquation());
        Polynomial quotient = new Polynomial();
        Polynomial t;

        while (reminder.getDegree() != 0 && reminder.getDegree() >= second.getDegree()) {
            int power = reminder.getDegree() - second.getDegree();
            double coefficient = reminder.getEquation().get(reminder.getDegree()) /
                    second.getEquation().get(second.getDegree());

            t = new Polynomial(coefficient + "x^" + power);
            quotient = Addition.apply(quotient, t);
            t = Multiplication.apply(t, second);
            reminder = Subtraction.apply(reminder, t);
        }

        Collection<Polynomial> c = new ArrayList<>();
        c.add(quotient);
        c.add(reminder);
        return c;
    }
}
