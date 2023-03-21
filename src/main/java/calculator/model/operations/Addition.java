package calculator.model.operations;

import java.util.HashMap;

import calculator.model.Polynomial;
import calculator.utils.Decimal;

public class Addition {
    private Addition() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        var result = new HashMap<Integer, Decimal>();

        for (int power : first.getPowerSet())
            result.put(power, first.getCoefficient(power));

        for (int power : second.getPowerSet()) {
            Decimal coefficient = result
                    .getOrDefault(power, Decimal.ZERO)
                    .add(second.getCoefficient(power));

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }
}
