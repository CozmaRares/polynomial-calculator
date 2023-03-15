package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private final static String regex = "(\\+|-)?([1-9]\\d*(\\.\\d+)?)?x?(\\^[1-9]\\d*)?";

    private Map<Integer, Double> equation;

    public Polynomial(String polynomial) {
        this.equation = new HashMap<>();
    }

    private Polynomial(Map<Integer, Double> equation) {
        this.equation = equation;
    }

    public Polynomial add(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

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
