package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Multiplication;

public class MultiplicationTest {
    @Test
    public void multiplicationWithOne() {
        var p1 = new Polynomial("x");
        var p2 = new Polynomial("1");

        assertEquals("x", Multiplication.apply(p1, p2).toString());
    }

    @Test
    public void multiplicationWithZero() {
        var p1 = new Polynomial("x^4+8x^3+5.6x^2-x-2.629");
        var p2 = new Polynomial("0");

        assertEquals("0", Multiplication.apply(p1, p2).toString());
    }

    @Test
    public void multiplicationShouldBeCorrect() {
        var p1 = new Polynomial("x+1");
        var p2 = new Polynomial("x+2");

        assertEquals("x^2+3x+2", Multiplication.apply(p1, p2).toString());
    }
}
