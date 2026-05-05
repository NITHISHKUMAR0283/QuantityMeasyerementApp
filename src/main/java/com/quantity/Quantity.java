package com.quantity;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(targetValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumBase = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double resultValue = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(resultValue, targetUnit);
    }


    /**
     * Subtracts another quantity from this quantity, result in this unit.
     * @param other the quantity to subtract
     * @return new Quantity<U> with the difference, in this unit
     * @throws IllegalArgumentException if other is null or not same category
     */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    /**
     * Subtracts another quantity from this quantity, result in target unit.
     * @param other the quantity to subtract
     * @param targetUnit the unit for the result
     * @return new Quantity<U> with the difference, in target unit
     * @throws IllegalArgumentException if other or targetUnit is null or not same category
     */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException("Cannot subtract quantities of different categories");
        double baseDiff = this.unit.convertToBaseUnit(this.value) - other.unit.convertToBaseUnit(other.value);
        double resultValue = targetUnit.convertFromBaseUnit(baseDiff);
        // Round to two decimal places for consistency
        resultValue = Math.round(resultValue * 100.0) / 100.0;
        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Divides this quantity by another quantity, returns a dimensionless scalar (double).
     * @param other the quantity to divide by
     * @return the ratio (this/other) as a double
     * @throws IllegalArgumentException if other is null or not same category
     * @throws ArithmeticException if dividing by zero
     */
    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException("Cannot divide quantities of different categories");
        double otherBase = other.unit.convertToBaseUnit(other.value);
        if (otherBase == 0.0) throw new ArithmeticException("Division by zero");
        double thisBase = this.unit.convertToBaseUnit(this.value);
        return thisBase / otherBase;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        // Prevent cross-category comparison
        if (!unit.getClass().equals(other.unit.getClass())) return false;
        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), unit.getUnitName(), unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.getUnitName());
    }
}