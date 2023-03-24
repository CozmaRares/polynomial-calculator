package calculator.model.operations;

import calculator.model.Polynomial;
import calculator.model.Polynomial.PolynomialBuilder;
import calculator.utils.Decimal;

public class Differentiation {
    private Differentiation() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        PolynomialBuilder builder = new PolynomialBuilder();

        for (int power : polynomial.getPowerSet()) {
            if (power < 1)
                continue;

            Decimal coefficient = polynomial
                    .getCoefficient(power)
                    .multiply(Decimal.valueOf(power));

            builder.addMonomial(power - 1, coefficient);
        }

        return builder.build();
    }
}
