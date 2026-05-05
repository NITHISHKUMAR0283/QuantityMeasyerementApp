    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592); // 1 lb ≈ 0.453592 kg

        private final double toKgFactor;

        WeightUnit(double toKgFactor) {
            this.toKgFactor = toKgFactor;
        }

        public double toKilogram(double value) {
            return value * toKgFactor;
        }

        public double fromKilogram(double kgValue) {
            return kgValue / toKgFactor;
        }
    }

    public static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Value must be a finite number");
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double kgValue = unit.toKilogram(value);
            double targetValue = targetUnit.fromKilogram(kgValue);
            return new QuantityWeight(targetValue, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            return add(other, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double sumKg = this.unit.toKilogram(this.value) + other.unit.toKilogram(other.value);
            double resultValue = targetUnit.fromKilogram(sumKg);
            return new QuantityWeight(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(this.unit.toKilogram(this.value), other.unit.toKilogram(other.value)) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toKilogram(value));
        }

        @Override
        public String toString() {
            return String.format("%.6f %s", value, unit);
        }
    }

package com.quantity;

public class QuantityMeasurementApp {

    public enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        @Override
        public double getConversionFactor() {
            return toFeetFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * toFeetFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toFeetFactor;
        }

        @Override
        public String getUnitName() {
            return name();
        }
    }


    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double toKgFactor;

        WeightUnit(double toKgFactor) {
            this.toKgFactor = toKgFactor;
        }

        @Override
        public double getConversionFactor() {
            return toKgFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * toKgFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / toKgFactor;
        }

        @Override
        public String getUnitName() {
            return name();
        }
    }

    public static void main(String[] args) {
        // Length equality
        var l1 = new Quantity<>(1.0, LengthUnit.FEET);
        var l2 = new Quantity<>(12.0, LengthUnit.INCH);
        System.out.println("1.0 FEET == 12.0 INCH? " + l1.equals(l2));

        // Length conversion
        var l3 = l1.convertTo(LengthUnit.INCH);
        System.out.println("1.0 FEET in INCHES: " + l3);

        // Length addition
        var l4 = l1.add(l2, LengthUnit.FEET);
        System.out.println("1.0 FEET + 12.0 INCHES in FEET: " + l4);

        // Weight equality
        var w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        System.out.println("1.0 KILOGRAM == 1000.0 GRAM? " + w1.equals(w2));

        // Weight conversion
        var w3 = w1.convertTo(WeightUnit.GRAM);
        System.out.println("1.0 KILOGRAM in GRAMS: " + w3);

        // Weight addition
        var w4 = w1.add(w2, WeightUnit.KILOGRAM);
        System.out.println("1.0 KILOGRAM + 1000.0 GRAM in KILOGRAM: " + w4);

        // Cross-category comparison (should be false)
        System.out.println("1.0 FEET == 1.0 KILOGRAM? " + l1.equals(w1));
    }
}
