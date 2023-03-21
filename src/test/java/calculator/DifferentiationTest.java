package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Differentiation;

public class DifferentiationTest {
    @Test
    public void differentiateConstant() {
        var p = new Polynomial("2");

        assertEquals("0", Differentiation.apply(p).toString());
    }

    @Test
    public void differentiateZero() {
        var p = new Polynomial("0");

        assertEquals("0", Differentiation.apply(p).toString());
    }

    @Test
    public void differentiateMonomial() {
        var p = new Polynomial("x^2");

        assertEquals("2x", Differentiation.apply(p).toString());
    }

    @Test
    public void differentiationShouldBeCorrect() {
        var p = new Polynomial("x^7+4x^6");

        assertEquals("7x^6+24x^5", Differentiation.apply(p).toString());
    }
}
