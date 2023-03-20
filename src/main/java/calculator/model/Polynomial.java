package calculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private final static String unsignedMonomialRegex = "(\\d+(\\.\\d+)?)?x(\\^\\d+)?|\\d+(\\.\\d+)?";

    private final static Pattern monomialPattern = Pattern
            .compile(String.format("[-+]?(%s)", unsignedMonomialRegex));

    private final static Pattern polynomialPattern = Pattern
            .compile(String.format("^-?(%s)([-+](%s))*$", unsignedMonomialRegex, unsignedMonomialRegex));

    private final Map<Integer, BigDecimal> monomials;
    private final int degree;

    private static void stripTrailingZeros(Map<Integer, BigDecimal> monomials) {
        for (var entry : monomials.entrySet())
            entry.setValue(entry.getValue().stripTrailingZeros());
    }

    private static void remove0s(Map<Integer, BigDecimal> monomials) {
        monomials.entrySet().removeIf(e -> e.getValue().equals(BigDecimal.ZERO));
    }

    private static int computeDegree(Map<Integer, BigDecimal> monomials) {
        int degree = 0;

        for (var key : monomials.keySet())
            if (key > degree)
                degree = key;

        return degree;
    }

    public static Polynomial clone(Polynomial p) {
        return new Polynomial(p.monomials);
    }

    public Polynomial() {
        this.monomials = new HashMap<>();
        this.degree = 0;
    }

    public Polynomial(final Map<Integer, BigDecimal> monomials) {
        this.monomials = new HashMap<>(monomials);
        Polynomial.stripTrailingZeros(this.monomials);
        Polynomial.remove0s(this.monomials);
        this.degree = Polynomial.computeDegree(this.monomials);
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
            BigDecimal coefficient;

            if (positionOfX == -1) {
                power = 0;
                coefficient = new BigDecimal(monomial);

                this.addMonomial(power, coefficient);
                continue;
            }

            String[] numbers = monomial.split("x\\^?");

            if (numbers.length == 0) {
                power = 1;
                coefficient = BigDecimal.ONE;

                this.addMonomial(power, coefficient);
                continue;
            }

            String coefficientString = numbers[0];

            if (coefficientString.length() == 0)
                coefficient = BigDecimal.ONE;
            else if (coefficientString.length() == 1 && "+-".indexOf(coefficientString.charAt(0)) != -1) {
                coefficient = BigDecimal.ONE;

                if (coefficientString.charAt(0) == '-')
                    coefficient = coefficient.multiply(BigDecimal.valueOf(-1));
            } else
                coefficient = new BigDecimal(coefficientString);

            power = numbers.length == 1 ? 1 : Integer.parseInt(numbers[1]);

            this.addMonomial(power, coefficient);
        }

        Polynomial.stripTrailingZeros(this.monomials);
        Polynomial.remove0s(this.monomials);
        this.degree = Polynomial.computeDegree(this.monomials);
    }

    private void addMonomial(int power, BigDecimal coefficient) {
        coefficient = this.getCoefficient(power).add(coefficient);

        this.monomials.put(power, coefficient);
    }

    public Set<Integer> getPowerSet() {
        return this.monomials.keySet();
    }

    public BigDecimal getCoefficient(int power) {
        return this.monomials.getOrDefault(power, BigDecimal.ZERO);
    }

    public int getDegree() {
        return this.degree;
    }

    @Override
    public String toString() {
        // TODO: refactor to StringBuilder
        String out = "";

        for (var entry : this.monomials.entrySet()) {
            int power = entry.getKey();
            BigDecimal coefficient = entry.getValue();

            if (power == 0) {
                out += coefficient.toPlainString();
                continue;
            }

            if (coefficient.compareTo(BigDecimal.ZERO) > 0 && out.length() != 0)
                out += "+";
            else if (coefficient.equals(BigDecimal.ONE.negate()))
                out += "-";

            if (!coefficient.abs().equals(BigDecimal.ONE))
                out += coefficient.toPlainString();

            out += "x";

            if (power != 1)
                out += "^" + power;
        }

        if (out == "")
            return "0";

        return out;
    }
}
