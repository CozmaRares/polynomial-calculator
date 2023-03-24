package calculator.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Decimal extends BigDecimal {
    private static final int SCALE = 8;

    private static final Decimal ALMOST_ZERO = new Decimal("0." + "0".repeat(SCALE - 2) + "1");

    public static final Decimal ZERO = new Decimal(BigDecimal.ZERO);
    public static final Decimal ONE = new Decimal(BigDecimal.ONE);

    public Decimal(final int val) {
        super(val);
    }

    public Decimal(final double val) {
        super(Double.toString(val));
    }

    public Decimal(final String val) {
        super(val);
    }

    public Decimal(final BigDecimal val) {
        super(val.unscaledValue(), val.scale());
    }

    public boolean equalTo(final BigDecimal decimal) {
        return this
                .subtract(decimal)
                .abs()
                .compareTo(Decimal.ALMOST_ZERO) < 0;
    }

    public boolean greaterThan(final BigDecimal decimal) {
        return this.compareTo(decimal) > 0;
    }

    public boolean greaterOrEqualTo(final BigDecimal decimal) {
        return this.greaterThan(decimal) || this.equalTo(decimal);
    }

    public boolean lessThan(final BigDecimal decimal) {
        return this.compareTo(decimal) < 0;
    }

    public boolean lessOrEqualTo(final BigDecimal decimal) {
        return this.lessThan(decimal) || this.equalTo(decimal);
    }

    @Override
    public Decimal add(final BigDecimal augend) {
        return new Decimal(super.add(augend));
    }

    @Override
    public Decimal subtract(final BigDecimal augend) {
        return new Decimal(super.subtract(augend));
    }

    @Override
    public Decimal multiply(final BigDecimal augend) {
        return new Decimal(super.multiply(augend));
    }

    @Override
    public Decimal divide(final BigDecimal augend) {
        return new Decimal(super.divide(augend, Decimal.SCALE, RoundingMode.HALF_UP));
    }

    @Override
    public Decimal abs() {
        return new Decimal(super.abs());
    }

    @Override
    public Decimal negate() {
        return new Decimal(super.negate());
    }

    @Override
    public Decimal stripTrailingZeros() {
        return new Decimal(super.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return this.toPlainString();
    }
}
