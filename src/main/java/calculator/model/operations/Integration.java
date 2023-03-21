package calculator.model.operations;

import java.util.HashMap;

import calculator.model.Polynomial;
import calculator.utils.Decimal;

public class Integration {
    private Integration() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        var result = new HashMap<Integer, Decimal>();

        for (int power : polynomial.getPowerSet()) {
            power++;

            Decimal coefficient = polynomial
                    .getCoefficient(power - 1)
                    .divide(Decimal.valueOf(power));

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
