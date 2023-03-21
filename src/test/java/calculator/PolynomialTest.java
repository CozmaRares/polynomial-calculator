package calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import calculator.model.Polynomial;

public class PolynomialTest {
    @Test(expected = IllegalArgumentException.class)
    public void firstMonomialStartingWithPlusShouldThrow() {
        new Polynomial("+2x^2");
    }

    @Test
    public void firstMonomialStartingWithMinusShouldBeAccepted() {
        var p = new Polynomial("-2x^2");

        assertEquals("-2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void firstMonomialWithoutSignShouldBeAccepted() {
        var p = new Polynomial("2x^2");

        assertEquals("2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithRealCoefficientShouldBeAccepted() {
        var p = new Polynomial("2.2x^2");

        assertEquals("2.2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithoutCoefficientShouldBeAccepted() {
        var p = new Polynomial("x^2");

        assertEquals("x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithoutPowerShouldBeAccepted() {
        var p = new Polynomial("2x");

        assertEquals("2x", p.toString());
        assertEquals(1, p.getDegree());
    }

    @Test
    public void monomialWithoutPowerAndWithoutCoefficientShouldBeAccepted() {
        var p = new Polynomial("x");

        assertEquals("x", p.toString());
        assertEquals(1, p.getDegree());
    }

    @Test(expected = IllegalArgumentException.class)
    public void coefficientStartingWithPlusShouldThrow() {
        new Polynomial("+2");
    }

    @Test
    public void coefficientStartingWithMinusShouldBeAccepted() {
        var p = new Polynomial("-2");

        assertEquals("-2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void coefficientWithoutSignShouldBeAccepted() {
        var p = new Polynomial("2");

        assertEquals("2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void realCoefficientWithoutSignShouldBeAccepted() {
        var p = new Polynomial("2.2");

        assertEquals("2.2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void polynomialShouldBeCorrectlyParsed() {
        var p = new Polynomial("x^2+2x+1");

        System.out.println(p);

        assertEquals("x^2+2x+1", p.toString());
    }

    @Test
    public void polynomialWithRealCoefficientShouldBeCorrectlyParsed() {
        var p = new Polynomial("-x^2+2.2x+1");

        System.out.println(p);

        assertEquals("-x^2+2.2x+1", p.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingConsecutiveSignsShouldThrow() {
        new Polynomial("x^2++2x+1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingExplicitMultiplicationShouldThrow() {
        new Polynomial("x^2+2*x+1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingInvalidPowerShouldThrow() {
        new Polynomial("x^a+1");
    }
}
