package calculator.model.operations;

import calculator.model.Polynomial;
import calculator.model.Polynomial.PolynomialBuilder;

public class Addition {
    private Addition() {
    }

    public static Polynomial apply(Polynomial first, Polynomial second) {
        PolynomialBuilder builder = new PolynomialBuilder();

        for (int power : first.getPowerSet())
            builder.addMonomial(power, first.getCoefficient(power));

        for (int power : second.getPowerSet())
            builder.addMonomial(power, second.getCoefficient(power));

        return builder.build();
    }
}
