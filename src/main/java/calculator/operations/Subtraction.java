package calculator.operations;

import java.util.HashMap;

import calculator.Polynomial;

public class Subtraction {
    private Subtraction() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        var result = new HashMap<>(first.getEquation());

        for (var entry : second.getEquation().entrySet()) {
            double value = result.getOrDefault(entry.getKey(), 0d) - entry.getValue();

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

}
