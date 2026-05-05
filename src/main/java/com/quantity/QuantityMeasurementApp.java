package com.quantity;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

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
    }
}
