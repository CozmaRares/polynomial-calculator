package calculator.model.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.model.Polynomial;

public class Integration {
    private Integration() {
    }

    public static Polynomial apply(Polynomial polynomial) {
        Map<Integer, Double> result = new HashMap<>();

        for (int power : polynomial.getPowerSet()) {
            power++;

            double coefficient = polynomial.getCoefficient(power - 1) / power;

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
