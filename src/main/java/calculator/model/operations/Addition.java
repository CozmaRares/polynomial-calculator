package calculator.model.operations;

import java.math.BigDecimal;
import java.util.HashMap;

import calculator.model.Polynomial;

public class Addition {
    private Addition() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        var result = new HashMap<Integer, BigDecimal>();

        for (int power : first.getPowerSet())
            result.put(power, first.getCoefficient(power));

        for (int power : second.getPowerSet()) {
            BigDecimal coefficient = result
                    .getOrDefault(power, BigDecimal.ZERO)
                    .add(second.getCoefficient(power));

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
