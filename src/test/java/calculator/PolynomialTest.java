package calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import calculator.model.Polynomial;

public class PolynomialTest {
    @Test(expected = IllegalArgumentException.class)
    public void mustHaveDigitsBeforeDecimalPoint() {
        Polynomial.fromString(".2x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void firstMonomialStartingWithPlusShouldThrow() {
        Polynomial.fromString("+2x^2");
    }

    @Test
    public void firstMonomialStartingWithMinusShouldBeAccepted() {
        var p = Polynomial.fromString("-2x^2");

        assertEquals("-2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void firstMonomialWithoutSignShouldBeAccepted() {
        var p = Polynomial.fromString("2x^2");

        assertEquals("2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithRealCoefficientShouldBeAccepted() {
        var p = Polynomial.fromString("2.2x^2");

        assertEquals("2.2x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithoutCoefficientShouldBeAccepted() {
        var p = Polynomial.fromString("x^2");

        assertEquals("x^2", p.toString());
        assertEquals(2, p.getDegree());
    }

    @Test
    public void monomialWithoutPowerShouldBeAccepted() {
        var p = Polynomial.fromString("2x");

        assertEquals("2x", p.toString());
        assertEquals(1, p.getDegree());
    }

    @Test
    public void xShouldBeAccepted() {
        var p = Polynomial.fromString("x");

        assertEquals("x", p.toString());
        assertEquals(1, p.getDegree());
    }

    @Test(expected = IllegalArgumentException.class)
    public void coefficientStartingWithPlusShouldThrow() {
        Polynomial.fromString("+2");
    }

    @Test
    public void coefficientStartingWithMinusShouldBeAccepted() {
        var p = Polynomial.fromString("-2");

        assertEquals("-2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void coefficientWithoutSignShouldBeAccepted() {
        var p = Polynomial.fromString("2");

        assertEquals("2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void realCoefficientWithoutSignShouldBeAccepted() {
        var p = Polynomial.fromString("2.2");

        assertEquals("2.2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void readCoefficientShouldBeStrippedOfZeros() {
        var p = Polynomial.fromString("2.00");

        assertEquals("2", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void strippingZerosFromCoefficientsShouldNotAffectMultiplesOfTen() {
        var p = Polynomial.fromString("40");

        assertEquals("40", p.toString());
        assertEquals(0, p.getDegree());
    }

    @Test
    public void polynomialShouldBeCorrectlyParsed() {
        var p = Polynomial.fromString("x^2+2x+1");

        assertEquals("x^2+2x+1", p.toString());
    }

    @Test
    public void polynomialWithRealAndNegativeCoefficientShouldBeCorrectlyParsed() {
        var p = Polynomial.fromString("-x^2+2.2x+1");

        assertEquals("-x^2+2.2x+1", p.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingConsecutiveSignsShouldThrow() {
        Polynomial.fromString("x^2++2x+1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingExplicitMultiplicationShouldThrow() {
        Polynomial.fromString("x^2+2*x+1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialContainingInvalidPowerShouldThrow() {
        Polynomial.fromString("x^a+1");
    }
}
