package calculator.model.operations;

import calculator.model.Polynomial;
import calculator.model.Polynomial.PolynomialBuilder;

public class Subtraction {
    private Subtraction() {
    }

    public static Polynomial apply(final Polynomial first, final Polynomial second) {
        PolynomialBuilder builder = new PolynomialBuilder();

        for (int power : first.getPowerSet())
            builder.addMonomial(power, first.getCoefficient(power));

        for (int power : second.getPowerSet())
            builder.addMonomial(power, second.getCoefficient(power).negate());

        return builder.build();
    }
}
