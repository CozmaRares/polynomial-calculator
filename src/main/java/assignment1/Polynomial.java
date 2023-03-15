package assignment1;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    public final static Pattern monomialPattern = Pattern.compile("[-+]?((\\d+(\\.\\d+)?)?x(\\^\\d+)?|\\d+(\\.\\d+)?)");

    private Map<Integer, Double> equation;

    private static <K, V> Map<K, V> copyHashMap(Map<K, V> map) {
        Map<K, V> copy = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet())
            copy.put(entry.getKey(), entry.getValue());

        return copy;
    }

    public Polynomial(String polynomial) {
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
    }

    private Polynomial(Map<Integer, Double> equation) {
        this.equation = equation;
    }

    public Polynomial add(Polynomial other) {
        var result = copyHashMap(this.equation);

        for (var entry : other.equation.entrySet()) {
            double value = result.getOrDefault(entry.getKey(), 0d) + entry.getValue();

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial other) {
        var result = copyHashMap(this.equation);

        for (var entry : other.equation.entrySet()) {
            double value = result.getOrDefault(entry.getKey(), 0d) - entry.getValue();

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry1 : this.equation.entrySet())
            for (var entry2 : other.equation.entrySet()) {
                int power = entry1.getKey() + entry2.getKey();
                double coefficient = entry1.getValue() * entry2.getValue() + result.getOrDefault(power, 0d);

                result.put(power, coefficient);
            }

        return new Polynomial(result);
    }

    public Polynomial divide(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    public Polynomial differentiate() {
        Map<Integer, Double> result = new HashMap<>();

        for (var entry : this.equation.entrySet()) {
            int power = entry.getKey() - 1;

            if (power < 0)
                continue;

            double coefficient = entry.getKey() * entry.getValue();

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

        return out;
    }
}
