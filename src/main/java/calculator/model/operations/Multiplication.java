package calculator.model.operations;

import calculator.model.Polynomial;
import calculator.model.Polynomial.PolynomialBuilder;
import calculator.utils.Decimal;

public class Multiplication {
    private Multiplication() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        PolynomialBuilder builder = new PolynomialBuilder();

        for (int power1 : first.getPowerSet())
            for (int power2 : second.getPowerSet()) {
                int power = power1 + power2;
                Decimal coefficient = first
                        .getCoefficient(power1)
                        .multiply(second.getCoefficient(power2));

                builder.addMonomial(power, coefficient);
            }

        return builder.build();
    }
}
