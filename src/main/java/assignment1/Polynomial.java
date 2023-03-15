package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    Map<Integer, Double> equation;

    Polynomial(String polynomial) {
        this.equation = new HashMap<>();
    }

    Polynomial(Map<Integer, Double> equation) {
        this.equation = equation;
    }

    Polynomial add(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    Polynomial subtract(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    Polynomial multiply(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    Polynomial divide(Polynomial other) {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    Polynomial differentiate() {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }

    Polynomial integrate() {
        Map<Integer, Double> result = new HashMap<>();

        return new Polynomial(result);
    }
}
