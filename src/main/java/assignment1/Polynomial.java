package assignment1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    public final static Pattern monomialPattern = Pattern.compile("[-+]?((\\d+(\\.\\d+)?)?x(\\^\\d+)?|\\d+(\\.\\d+)?)");

    private Map<Integer, Double> equation;
    private int degree;

    public Polynomial(final String polynomial) {
        this.equation = new HashMap<>();

        Matcher matcher = monomialPattern.matcher(polynomial);

        while (matcher.find()) {
            String monomial = matcher.group();

            int positionOfX = monomial.indexOf("x");

            int power;
            double coefficient;

            if (positionOfX == -1) {
                power = 0;
                coefficient = Double.parseDouble(monomial) + this.equation.getOrDefault(power, 0d);
                this.equation.put(power, coefficient);
                continue;
            }

            String[] numbers = monomial.split("x\\^?");

            if (numbers.length == 0) {
                power = 1;
                coefficient = 1 + this.equation.getOrDefault(power, 0d);
                this.equation.put(power, coefficient);
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
            coefficient += this.equation.getOrDefault(power, 0d);

            this.equation.put(power, coefficient);
        }

        this.remove0s();
        this.computeDegree();
    }

    private Polynomial(final Map<Integer, Double> equation) {
        this.equation = new HashMap<>(equation);
        this.remove0s();
        this.computeDegree();
    }

    private Polynomial() {
        this.equation = new HashMap<>();
    }

    private void computeDegree() {
        for (var key : this.equation.keySet())
            if (key > this.degree)
                this.degree = key;

    }

    private void remove0s() {
        for (var entry : this.equation.entrySet())
            if (entry.getValue() == 0)
                this.equation.remove(entry.getKey());
    }

    public Polynomial add(final Polynomial other) {
        var result = new HashMap<>(this.equation);

        for (var entry : other.equation.entrySet()) {
            double value = result.getOrDefault(entry.getKey(), 0d) + entry.getValue();

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial subtract(final Polynomial other) {
        var result = new HashMap<>(this.equation);

        for (var entry : other.equation.entrySet()) {
            double value = result.getOrDefault(entry.getKey(), 0d) - entry.getValue();

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial multiply(final Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry1 : this.equation.entrySet())
            for (var entry2 : other.equation.entrySet()) {
                int power = entry1.getKey() + entry2.getKey();
                double coefficient = entry1.getValue() * entry2.getValue() + result.getOrDefault(power, 0d);

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }

    public Collection<Polynomial> divide(final Polynomial other) {
        Polynomial reminder = new Polynomial(this.equation);
        Polynomial quotient = new Polynomial();
        Polynomial t;

        while (reminder.degree != 0 && reminder.degree >= other.degree) {
            int power = reminder.degree - other.degree;
            double coefficient = reminder.equation.get(reminder.degree) / other.equation.get(other.degree);

            t = new Polynomial(coefficient + "x^" + power);
            quotient = quotient.add(t);
            t = t.multiply(other);
            reminder = reminder.subtract(t);
        }

        Collection<Polynomial> c = new ArrayList<>();
        c.add(quotient);
        c.add(reminder);
        return c;
    }

    public Polynomial differentiate() {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry : this.equation.entrySet()) {
            int power = entry.getKey() - 1;

            if (power < 0)
                continue;

            double coefficient = entry.getKey() * entry.getValue() + result.getOrDefault(entry.getKey(), 0d);

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }

    public Polynomial integrate() {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry : this.equation.entrySet()) {
            int power = entry.getKey() + 1;

            if (power < 0)
                continue;

            double coefficient = entry.getValue() / power;

            result.put(power, coefficient);
        }

        return new Polynomial(result);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#");

        String out = "";

        for (var entry : this.equation.entrySet()) {
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
