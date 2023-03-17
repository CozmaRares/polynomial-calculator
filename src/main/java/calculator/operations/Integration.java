package calculator.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.Polynomial;

public class Integration {
    private Integration() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry : polynomial.getEquation().entrySet()) {
            int power = entry.getKey() + 1;

            if (power < 0)
                continue;

            double coefficient = entry.getValue() / power;

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
