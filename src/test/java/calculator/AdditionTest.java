package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Addition;

public class AdditionTest {
    @Test
    public void additionShouldBeCorrect() {
        var p1 = Polynomial.fromString("x^5+10x^3+5x^2+6.8x+9.2");
        var p2 = Polynomial.fromString("0.8x^4+8x^2+x+2.8");

        // x^5 ________ + 10x^3 + _5x^2 + 6.8x + 9.2 +
        // _____ 0.8x^4 _______ + _8x^2 + ___x + 2.8
        // ------------------------------------------
        // x^5 + 0.8x^4 + 10x^3 + 13x^2 + 7.8x + 11

        assertEquals("x^5+0.8x^4+10x^3+13x^2+7.8x+12", Addition.apply(p1, p2).toString());
    }

    @Test
    public void additionShouldBeCorrect2() {
        var p1 = Polynomial.fromString("-x^5-0.0714x^4-4x^3-1.2857x^2-x+45.0");
        var p2 = Polynomial.fromString("-0.5x^3-2.629x^2+x+2");

        // -x^5 - 0.0714x^4 - __4x^3 - 1.2857x^2 - x + 45.0 +
        // ________________ - 0.5x^3 - _2.629x^2 + x + _2
        // ------------------------------------------------
        // -x^5 - 0.0714x^4 - 4.5x^3 - 3.9147x^2 ___ + 47

        assertEquals("-x^5-0.071x^4-4.5x^3-3.915x^2+47", Addition.apply(p1, p2).toString());
    }
}
