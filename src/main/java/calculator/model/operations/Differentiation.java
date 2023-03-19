package calculator.model.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.model.Polynomial;

public class Differentiation {
    private Differentiation() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry : polynomial.getEquation().entrySet()) {
            int power = entry.getKey() - 1;

            if (power < 0)
                continue;

            double coefficient = entry.getKey() * entry.getValue() + result.getOrDefault(entry.getKey(), 0d);

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }

}
