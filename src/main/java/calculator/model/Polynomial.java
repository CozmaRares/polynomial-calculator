package calculator.model;

import java.text.DecimalFormat;
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

    private Map<Integer, Double> monomials;
    private int degree;

    public static Polynomial clone(Polynomial p) {
        return new Polynomial(p.monomials);
    }

    public Polynomial() {
        this.monomials = new HashMap<>();
        this.computeDegree();
    }

    public Polynomial(final Map<Integer, Double> equation) {
        this.monomials = new HashMap<>(equation);
        this.remove0s();
        this.computeDegree();
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
            double coefficient;

            if (positionOfX == -1) {
                power = 0;
                coefficient = Double.parseDouble(monomial) + this.monomials.getOrDefault(power, 0d);
                this.monomials.put(power, coefficient);
                continue;
            }

            String[] numbers = monomial.split("x\\^?");

            if (numbers.length == 0) {
                power = 1;
                coefficient = 1 + this.monomials.getOrDefault(power, 0d);
                this.monomials.put(power, coefficient);
                continue;
            }

            String coefficientString = numbers[0];

            if (coefficientString.length() == 0)
                coefficient = 1;
            else if (coefficientString.length() == 1 && "+-".indexOf(coefficientString.charAt(0)) != -1)
                coefficient = coefficientString.charAt(0) == '+' ? 1 : -1;
            else
                coefficient = Double.parseDouble(coefficientString);

            power = numbers.length == 1 ? 1 : Integer.parseInt(numbers[1]);
            coefficient += this.monomials.getOrDefault(power, 0d);

            this.monomials.put(power, coefficient);
        }

        this.remove0s();
        this.computeDegree();
    }

    private void computeDegree() {
        this.degree = 0;

        for (var key : this.monomials.keySet())
            if (key > this.degree)
                this.degree = key;

    }

    private void remove0s() {
        for (var entry : this.monomials.entrySet())
            if (entry.getValue() == 0)
                this.monomials.remove(entry.getKey());
    }

    public Set<Integer> getPowerSet() {
        return this.monomials.keySet();
    }

    public double getCoefficient(int power) {
        return this.monomials.getOrDefault(power, 0d);
    }

    public int getDegree() {
        return this.degree;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#");

        String out = "";

        for (var entry : this.monomials.entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue();
            String formattedCoefficient = format.format(coefficient);

            if (power == 0) {
                out += formattedCoefficient;
                continue;
            }

            if (coefficient > 0 && out.length() != 0)
                out += "+";

            if (coefficient != 1)
                out += formattedCoefficient;

            out += "x";

            if (power != 1)
                out += "^" + power;
        }

        if (out == "")
            return "0";

        return out;
    }
}
