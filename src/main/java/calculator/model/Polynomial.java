package calculator.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.utils.Decimal;

public class Polynomial {
    private static int COEFFICIENT_GROUP = 1;
    private static int POWER_GROUP = 4;
    private static int COEFFICIENT_ONLY_GROUP = 5;

    private final static String unsignedMonomialRegex = "(\\d+(\\.\\d+)?)?x(\\^\\d+)?|(\\d+(\\.\\d+)?)";
    private final static String monomialRegex = "([-+]?(\\d+(\\.\\d+)?)?)x(\\^\\d+)?|([-+]?\\d+(\\.\\d+)?)";
    private final static String polynomialRegex = String
            .format("^-?(%s)([-+](%s))*$", unsignedMonomialRegex, unsignedMonomialRegex);

    private final static Pattern monomialPattern = Pattern.compile(monomialRegex);
    private final static Pattern polynomialPattern = Pattern.compile(polynomialRegex);

    private final Map<Integer, Decimal> monomials;
    private final int degree;

    private static void stripTrailingZeros(final Map<Integer, Decimal> monomials) {
        for (var entry : monomials.entrySet())
            entry.setValue(entry.getValue().stripTrailingZeros());
    }

    private static void remove0s(final Map<Integer, Decimal> monomials) {
        monomials.entrySet().removeIf(e -> e.getValue().equalTo(Decimal.ZERO));
    }

    private static int computeDegree(final Map<Integer, Decimal> monomials) {
        int degree = -1;

        for (var key : monomials.keySet())
            if (key > degree)
                degree = key;

        return degree;
    }

    public Polynomial() {
        this.monomials = new HashMap<>();
        this.degree = Polynomial.computeDegree(this.monomials);
    }

    private Polynomial(final Map<Integer, Decimal> monomials) {
        this.monomials = new HashMap<>(monomials);
        Polynomial.stripTrailingZeros(this.monomials);
        Polynomial.remove0s(this.monomials);
        this.degree = Polynomial.computeDegree(this.monomials);
    }

    public Polynomial(final Polynomial p) {
        this(p.monomials);
    }

    public static Polynomial fromString(final String polynomial) {
        PolynomialBuilder builder = new PolynomialBuilder();

        Matcher polynomialMatcher = polynomialPattern.matcher(polynomial);

        if (!polynomialMatcher.find())
            throw new IllegalArgumentException("'" + polynomial + "' does not represent a valid polynomial");

        Matcher monomialMatcher = monomialPattern.matcher(polynomial);

        while (monomialMatcher.find()) {
            if (monomialMatcher.group(COEFFICIENT_ONLY_GROUP) != null) {
                int power = 0;
                Decimal coefficient = new Decimal(monomialMatcher.group(COEFFICIENT_ONLY_GROUP));

                builder.addMonomial(power, coefficient);

                continue;
            }

            String powerString = monomialMatcher.group(POWER_GROUP) == null
                    ? "1"
                    : monomialMatcher.group(POWER_GROUP).substring(1);

            String coefficientString = monomialMatcher.group(COEFFICIENT_GROUP).length() == 0
                    ? "1"
                    : monomialMatcher.group(COEFFICIENT_GROUP);

            int power = Integer.parseInt(powerString);
            Decimal coefficient;

            if (coefficientString.equals("+"))
                coefficient = Decimal.ONE;
            else if (coefficientString.equals("-"))
                coefficient = Decimal.ONE.negate();
            else
                coefficient = new Decimal(coefficientString);

            builder.addMonomial(power, coefficient);
        }

        return builder.build();
    }

    public Set<Integer> getPowerSet() {
        return this.monomials.keySet();
    }

    public Decimal getCoefficient(final int power) {
        return this.monomials.getOrDefault(power, Decimal.ZERO);
    }

    public int getDegree() {
        return this.degree;
    }

    @Override
    public String toString() {
        if (this.degree == -1)
            return "0";

        StringBuilder sb = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#0.###");

        var sortedMonomials = new ArrayList<>(this.monomials.entrySet());
        sortedMonomials.sort((e1, e2) -> e2.getKey() - e1.getKey());

        for (var entry : sortedMonomials) {
            int power = entry.getKey();
            Decimal coefficient = entry.getValue();
            String formattedCoefficient = format.format(coefficient);

            if (coefficient.greaterThan(Decimal.ZERO) && sb.length() != 0)
                sb.append("+");
            else if (coefficient.equalTo(Decimal.ONE.negate()))
                sb.append("-");

            if (power == 0) {
                sb.append(formattedCoefficient);
                continue;
            }

            if (!coefficient.abs().equalTo(Decimal.ONE))
                sb.append(formattedCoefficient);

            sb.append("x");

            if (power != 1)
                sb.append("^" + power);
        }

        return sb.toString();
    }

    public static class PolynomialBuilder {
        private final Map<Integer, Decimal> monomials;

        public PolynomialBuilder() {
            this.monomials = new HashMap<>();
        }

        public void addMonomial(final int power, final Decimal coefficient) {
            Decimal newCoefficient = this.getCoefficient(power).add(coefficient);

            this.monomials.put(power, newCoefficient);
        }

        private Decimal getCoefficient(final int power) {
            return this.monomials.getOrDefault(power, Decimal.ZERO);
        }

        public Polynomial build() {
            return new Polynomial(this.monomials);
        }

    }
}
