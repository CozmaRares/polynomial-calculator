package calculator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.utils.Decimal;

public class Polynomial {
    private final static String unsignedMonomialRegex = "(\\d+(\\.\\d+)?)?x(\\^\\d+)?|\\d+(\\.\\d+)?";

    private final static Pattern monomialPattern = Pattern
            .compile(String.format("[-+]?(%s)", unsignedMonomialRegex));

    private final static Pattern polynomialPattern = Pattern
            .compile(String.format("^-?(%s)([-+](%s))*$", unsignedMonomialRegex, unsignedMonomialRegex));

    private final Map<Integer, Decimal> monomials;
    private final int degree;

    private static void stripTrailingZeros(Map<Integer, Decimal> monomials) {
        for (var entry : monomials.entrySet())
            entry.setValue(entry.getValue().stripTrailingZeros());
    }

    private static void remove0s(Map<Integer, Decimal> monomials) {
        monomials.entrySet().removeIf(e -> e.getValue().equalTo(Decimal.ZERO));
    }

    private static int computeDegree(Map<Integer, Decimal> monomials) {
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

    public Polynomial(final Map<Integer, Decimal> monomials) {
        this.monomials = new HashMap<>(monomials);
        Polynomial.stripTrailingZeros(this.monomials);
        Polynomial.remove0s(this.monomials);
        this.degree = Polynomial.computeDegree(this.monomials);
    }

    public Polynomial(final Polynomial p) {
        this(p.monomials);
    }

    public Polynomial(final String polynomial) {
        this.monomials = new HashMap<>();

        Matcher polynomialMatcher = polynomialPattern.matcher(polynomial);

        if (!polynomialMatcher.find())
            throw new IllegalArgumentException("'" + polynomial + "' does not represent a valid polynomial");

        Matcher monomialMatcher = monomialPattern.matcher(polynomial);

        while (monomialMatcher.find()) {
            String monomial = monomialMatcher.group();

            int positionOfX = monomial.indexOf("x");

            int power;
            Decimal coefficient;

            if (positionOfX == -1) {
                power = 0;
                coefficient = new Decimal(monomial);

                this.addMonomial(power, coefficient);
                continue;
            }

            String[] numbers = monomial.split("x\\^?");

            if (numbers.length == 0) {
                power = 1;
                coefficient = Decimal.ONE;

                this.addMonomial(power, coefficient);
                continue;
            }

            String coefficientString = numbers[0];

            if (coefficientString.length() == 0)
                coefficient = Decimal.ONE;
            else if (coefficientString.length() == 1 && "+-".indexOf(coefficientString.charAt(0)) != -1) {
                coefficient = Decimal.ONE;

                if (coefficientString.charAt(0) == '-')
                    coefficient = coefficient.multiply(Decimal.valueOf(-1));
            } else
                coefficient = new Decimal(coefficientString);

            power = numbers.length == 1 ? 1 : Integer.parseInt(numbers[1]);

            this.addMonomial(power, coefficient);
        }

        Polynomial.stripTrailingZeros(this.monomials);
        Polynomial.remove0s(this.monomials);
        this.degree = Polynomial.computeDegree(this.monomials);
    }

    private void addMonomial(int power, Decimal coefficient) {
        coefficient = this.getCoefficient(power).add(coefficient);

        this.monomials.put(power, coefficient);
    }

    public Set<Integer> getPowerSet() {
        return this.monomials.keySet();
    }

    public Decimal getCoefficient(int power) {
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

        var sortedMonomials = new ArrayList<>(this.monomials.entrySet());
        sortedMonomials.sort((e1, e2) -> e2.getKey() - e1.getKey());

        for (var entry : sortedMonomials) {
            int power = entry.getKey();
            Decimal coefficient = entry.getValue();

            if (coefficient.greaterThan(Decimal.ZERO) && sb.length() != 0)
                sb.append("+");
            else if (coefficient.equalTo(Decimal.ONE.negate()))
                sb.append("-");

            if (power == 0) {
                sb.append(coefficient);
                continue;
            }

            if (!coefficient.abs().equalTo(Decimal.ONE))
                sb.append(coefficient);

            sb.append("x");

            if (power != 1)
                sb.append("^" + power);
        }

        return sb.toString();
    }
}
