package calculator.model.operations;

import java.math.BigDecimal;
import java.util.HashMap;

import calculator.model.Polynomial;

public class Differentiation {
    private Differentiation() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        var result = new HashMap<Integer, BigDecimal>();

        for (int power : polynomial.getPowerSet()) {
            if (power < 1)
                continue;

            BigDecimal coefficient = polynomial
                    .getCoefficient(power)
                    .multiply(BigDecimal.valueOf(power))
                    .add(result.getOrDefault(power, BigDecimal.ZERO));

            result.put(power - 1, coefficient);
        }

        return new Polynomial(result);
    }
}
