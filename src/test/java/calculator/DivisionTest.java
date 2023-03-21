package calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculator.model.Polynomial;
import calculator.model.operations.Division;

public class DivisionTest {
    @Test
    public void divisionByOne() {
        var p1 = new Polynomial("x^2+x");
        var p2 = new Polynomial("1");
        var res = Division.apply(p1, p2);

        assertEquals("x^2+x", res.get(0).toString());
        assertEquals("0", res.get(1).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void divisionByZero() {
        var p1 = new Polynomial("x^2+x");
        var p2 = new Polynomial("0");

        Division.apply(p1, p2);
    }

    @Test
    public void divisionShouldBeCorrect() {
        var p1 = new Polynomial("50x^2+40x+15");
        var p2 = new Polynomial("10x+5");
        var res = Division.apply(p1, p2);

        assertEquals("5x+1.5", res.get(0).toString());
        assertEquals("7.5", res.get(1).toString());
    }
}
