package calculator.model.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.model.Polynomial;

public class Differentiation {
    private Differentiation() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        Map<Integer, Double> result = new HashMap<>();

        for (int power : polynomial.getPowerSet()) {
            if (power < 1)
                continue;

            double coefficient = power * polynomial.getCoefficient(power) + result.getOrDefault(power, 0d);

            result.put(power - 1, coefficient);
        }

        return new Polynomial(result);
    }

}
