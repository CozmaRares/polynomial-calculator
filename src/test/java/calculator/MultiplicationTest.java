package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Multiplication;

public class MultiplicationTest {
    @Test
    public void multiplicationWithOne() {
        var p1 = Polynomial.fromString("x");
        var p2 = Polynomial.fromString("1");

        assertEquals("x", Multiplication.apply(p1, p2).toString());
    }

    @Test
    public void multiplicationWithZero() {
        var p1 = Polynomial.fromString("x^4+8x^3+5.6x^2-x-2.629");
        var p2 = Polynomial.fromString("0");

        assertEquals("0", Multiplication.apply(p1, p2).toString());
    }

    @Test
    public void multiplicationShouldBeCorrect() {
        var p1 = Polynomial.fromString("x+1");
        var p2 = Polynomial.fromString("x+2");

        assertEquals("x^2+3x+2", Multiplication.apply(p1, p2).toString());
    }
}
