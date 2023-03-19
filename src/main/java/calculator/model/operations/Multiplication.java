package calculator.model.operations;

import java.math.BigDecimal;
import java.util.HashMap;

import calculator.model.Polynomial;

public class Multiplication {
    private Multiplication() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        var result = new HashMap<Integer, BigDecimal>();

        for (int power1 : first.getPowerSet())
            for (int power2 : second.getPowerSet()) {
                int power = power1 + power2;
                BigDecimal coefficient = first
                        .getCoefficient(power1)
                        .multiply(second.getCoefficient(power2))
                        .add(result.getOrDefault(power, BigDecimal.ZERO));

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }
}
