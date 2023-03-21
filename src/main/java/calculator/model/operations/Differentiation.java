package calculator.model.operations;

import java.util.HashMap;

import calculator.model.Polynomial;
import calculator.utils.Decimal;

public class Differentiation {
    private Differentiation() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        var result = new HashMap<Integer, Decimal>();

        for (int power : polynomial.getPowerSet()) {
            if (power < 1)
                continue;

            Decimal coefficient = polynomial
                    .getCoefficient(power)
                    .multiply(Decimal.valueOf(power))
                    .add(result.getOrDefault(power, Decimal.ZERO));

            result.put(power - 1, coefficient);
        }

        return new Polynomial(result);
    }
}
