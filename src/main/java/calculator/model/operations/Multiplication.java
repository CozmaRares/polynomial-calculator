package calculator.model.operations;

import java.util.HashMap;
import java.util.Map;

import calculator.model.Polynomial;

public class Multiplication {
    private Multiplication() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry1 : first.getEquation().entrySet())
            for (var entry2 : second.getEquation().entrySet()) {
                int power = entry1.getKey() + entry2.getKey();
                double coefficient = entry1.getValue() * entry2.getValue() + result.getOrDefault(power, 0d);

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }
}
