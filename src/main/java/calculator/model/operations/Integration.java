package calculator.model.operations;

import java.math.BigDecimal;
import java.util.HashMap;

import calculator.model.Polynomial;

public class Integration {
    private Integration() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        var result = new HashMap<Integer, BigDecimal>();

        for (int power : polynomial.getPowerSet()) {
            power++;

            BigDecimal coefficient = polynomial
                    .getCoefficient(power - 1)
                    .divide(BigDecimal.valueOf(power));

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
