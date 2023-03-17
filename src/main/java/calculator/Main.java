package calculator;

import calculator.operations.*;

public class Main {
    public static void main(String[] args) {
        var p1 = new Polynomial("x^2");
        var p2 = new Polynomial("2x");

        System.out.println(Addition.apply(p1, p2));
        System.out.println(Subtraction.apply(p1, p2));
        System.out.println(Multiplication.apply(p1, p2));
        System.out.println(Division.apply(p1, p2));
        System.out.println(Differentiation.apply(p1));
        System.out.println(Integration.apply(p2));
    }
}
