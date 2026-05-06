package com.quantity;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Invalid target unit");
        }
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Invalid operand");
        }
        double sumBase = unit.convertToBaseUnit(value) + other.unit.convertToBaseUnit(other.value);
        double result = unit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        double sumBase = unit.convertToBaseUnit(value) + other.unit.convertToBaseUnit(other.value);
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(
                this.unit.convertToBaseUnit(this.value),
                other.unit.convertToBaseUnit(other.value)
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}