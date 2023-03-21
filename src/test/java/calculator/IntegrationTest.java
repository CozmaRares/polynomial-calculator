package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Integration;

public class IntegrationTest {
    @Test
    public void integrateConstant() {
        var p = new Polynomial("2");

        assertEquals("2x", Integration.apply(p).toString());
    }

    @Test
    public void integrateZero() {
        var p = new Polynomial("0");

        assertEquals("0", Integration.apply(p).toString());
    }


    @Test
    public void integrateMonomial() {
        var p = new Polynomial("2x");

        assertEquals("x^2", Integration.apply(p).toString());
    }

    @Test
    public void integrationShouldBeCorrect() {
        var p = new Polynomial("x^2+2x+1");

        assertEquals("0.333x^3+x^2+x", Integration.apply(p).toString());
    }

}
