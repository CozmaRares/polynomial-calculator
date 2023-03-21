package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Subtraction;

public class SubtractionTest {
    @Test
    public void subtractionShouldBeCorrect() {
        var p1 = new Polynomial("x^5+10x^3+5x^2+6.8x+9.2");
        var p2 = new Polynomial("0.8x^4+8x^2+x+2.8");

        // x^5 ________ + 10x^3 + 5x^2 + 6.8x + 9.2 -
        // _____ 0.8x^4 _______ + 8x^2 + ___x + 2.8
        // ------------------------------------------
        // x^5 - 0.8x^4 + 10x^3 - 3x^2 + 5.8x + 6.4

        assertEquals("x^5-0.8x^4+10x^3-3x^2+5.8x+6.4", Subtraction.apply(p1, p2).toString());
    }

    @Test
    public void subtractionShouldBeCorrect2() {
        var p1 = new Polynomial("-0.5x^3-2.629x^2+x+2");
        var p2 = new Polynomial("-x^5-0.0714x^4-4x^3-1.2857x^2-x+45");

        // ________________ - 0.5x^3 - _2.629x^2 + _x + _2 -
        // -x^5 - 0.0714x^4 - __4x^3 - 1.2857x^2 - _x + 45
        // ------------------------------------------------
        // _x^5 + 0.0714x^4 + 3.5x^3 - 1.3433x^2 + 2x - 43

        assertEquals("x^5+0.0714x^4+3.5x^3-1.3433x^2+2x-43", Subtraction.apply(p1, p2).toString());
    }
}
