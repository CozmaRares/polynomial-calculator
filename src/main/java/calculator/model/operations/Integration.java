package calculator.model.operations;

import calculator.model.Polynomial;
import calculator.model.Polynomial.PolynomialBuilder;
import calculator.utils.Decimal;

public class Integration {
    private Integration() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        PolynomialBuilder builder = new PolynomialBuilder();

        for (int power : polynomial.getPowerSet()) {
            power++;

            Decimal coefficient = polynomial
                    .getCoefficient(power - 1)
                    .divide(Decimal.valueOf(power));

            builder.addMonomial(power, coefficient);
        }

        return builder.build();
    }
}
