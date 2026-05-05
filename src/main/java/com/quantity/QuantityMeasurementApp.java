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

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0), // 1 yard = 3 feet
        CENTIMETER(1.0 / 30.48); // 1 cm = 0.393701 in = 1/2.54 in, 1 in = 1/12 ft, so 1 cm = 1/30.48 ft

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            // Compare after converting both to feet
            return Double.compare(this.unit.toFeet(this.value), other.unit.toFeet(other.value)) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toFeet(value));
        }
    }

    public static boolean areLengthsEqual(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        return q1.equals(q2);
    }

    public static void main(String[] args) {
        // Feet to Feet
        System.out.println("Feet: 1.0 and 1.0 Equal? " + areLengthsEqual(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET));
        // Inch to Inch
        System.out.println("Inch: 1.0 and 1.0 Equal? " + areLengthsEqual(1.0, LengthUnit.INCH, 1.0, LengthUnit.INCH));
        // Feet to Inch
        System.out.println("Feet: 1.0 and Inch: 12.0 Equal? " + areLengthsEqual(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH));
        // Inch to Feet
        System.out.println("Inch: 12.0 and Feet: 1.0 Equal? " + areLengthsEqual(12.0, LengthUnit.INCH, 1.0, LengthUnit.FEET));
        // Yard to Feet
        System.out.println("Yard: 1.0 and Feet: 3.0 Equal? " + areLengthsEqual(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET));
        // Yard to Inch
        System.out.println("Yard: 1.0 and Inch: 36.0 Equal? " + areLengthsEqual(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH));
        // Centimeter to Inch
        System.out.println("Centimeter: 1.0 and Inch: 0.393701 Equal? " + areLengthsEqual(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH));
        // Centimeter to Centimeter
        System.out.println("Centimeter: 2.0 and Centimeter: 2.0 Equal? " + areLengthsEqual(2.0, LengthUnit.CENTIMETER, 2.0, LengthUnit.CENTIMETER));
    }
}
