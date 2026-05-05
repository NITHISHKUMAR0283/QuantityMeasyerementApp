package com.quantity;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    // Centralized enum for arithmetic operations
    private enum ArithmeticOperation {
        ADD {
            @Override
            double compute(double a, double b) { return a + b; }
        },
        SUBTRACT {
            @Override
            double compute(double a, double b) { return a - b; }
        },
        DIVIDE {
            @Override
            double compute(double a, double b) {
                if (b == 0.0) throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };
        abstract double compute(double a, double b);
    }
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
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultValue = targetUnit.convertFromBaseUnit(baseResult);
        resultValue = roundToTwoDecimals(resultValue);
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

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultValue = targetUnit.convertFromBaseUnit(baseResult);
        resultValue = roundToTwoDecimals(resultValue);
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
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // Centralized validation for arithmetic operations
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
        if (targetUnitRequired && targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException("Cannot operate on quantities of different categories");
        if (Double.isNaN(this.value) || Double.isInfinite(this.value)) throw new IllegalArgumentException("Value must be a finite number");
        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) throw new IllegalArgumentException("Other value must be a finite number");
    }

    // Centralized arithmetic logic for all operations
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return op.compute(thisBase, otherBase);
    }

    // Helper for rounding to two decimal places
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
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