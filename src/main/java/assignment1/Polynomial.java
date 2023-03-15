package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private final static String regex = "(\\+|-)?([1-9]\\d*(\\.\\d+)?)?x?(\\^[1-9]\\d*)?";

    private Map<Integer, Double> equation;

    private static <K, V> Map<K, V> copyHashMap(Map<K, V> map) {
        Map<K, V> copy = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet())
            copy.put(entry.getKey(), entry.getValue());

        return copy;
    }

    public Polynomial(String polynomial) {
        this.equation = new HashMap<>();
    }

    private Polynomial(Map<Integer, Double> equation) {
        this.equation = equation;
    }

    public Polynomial add(Polynomial other) {
        Map<Integer, Double> result = copyHashMap(this.equation);

        for (Map.Entry<Integer, Double> entry : other.equation.entrySet()) {
            double value = entry.getValue() + result.getOrDefault(entry.getKey(), 0d);

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial other) {
        Map<Integer, Double> result = copyHashMap(this.equation);

        for (Map.Entry<Integer, Double> entry : other.equation.entrySet()) {
            double value = entry.getValue() - result.getOrDefault(entry.getKey(), 0d);

            result.put(entry.getKey(), value);
        }

        return new Polynomial(result);
    }

    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    public Polynomial divide(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    public Polynomial differentiate() {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    public Polynomial integrate() {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }
}
