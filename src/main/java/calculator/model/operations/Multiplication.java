package calculator.model.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.model.Polynomial;

public class Multiplication {
    private Multiplication() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        Map<Integer, Double> result = new HashMap<>();

        for (int power1 : first.getPowerSet())
            for (int power2 : second.getPowerSet()) {
                int power = power1 + power2;
                double coefficient = first.getCoefficient(power1) * second.getCoefficient(power2)
                        + result.getOrDefault(power, 0d);

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }
}
