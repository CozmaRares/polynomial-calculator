package calculator.model.operations;

import java.util.HashMap;

import calculator.model.Polynomial;
import calculator.utils.Decimal;

public class Multiplication {
    private Multiplication() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        var result = new HashMap<Integer, Decimal>();

        for (int power1 : first.getPowerSet())
            for (int power2 : second.getPowerSet()) {
                int power = power1 + power2;
                Decimal coefficient = first
                        .getCoefficient(power1)
                        .multiply(second.getCoefficient(power2))
                        .add(result.getOrDefault(power, Decimal.ZERO));

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }
}
