package com.quantity;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {
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
            double feetValue = unit.toFeet(value);
            double convertedValue = targetUnit.fromFeet(feetValue);
            return new QuantityLength(convertedValue, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Invalid operand");
            }
            double sumFeet = this.unit.toFeet(this.value) + other.unit.toFeet(other.value);
            double resultValue = this.unit.fromFeet(sumFeet);
            return new QuantityLength(resultValue, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }
            double sumFeet = this.unit.toFeet(this.value) + other.unit.toFeet(other.value);
            double resultValue = targetUnit.fromFeet(sumFeet);
            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.unit.toFeet(this.value), other.unit.toFeet(other.value)) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toFeet(value));
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        double feetValue = source.toFeet(value);
        return target.fromFeet(feetValue);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        return q1.add(q2);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit targetUnit) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        return q1.add(q2, targetUnit);
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q1.add(q2, LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.YARD));

        System.out.println(add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.INCH));
    }
}