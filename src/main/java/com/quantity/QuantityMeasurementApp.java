package com.quantity;

public class QuantityMeasurementApp {

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return new QuantityLength(v1, u1).add(new QuantityLength(v2, u2));
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit target) {
        return new QuantityLength(v1, u1).add(new QuantityLength(v2, u2), target);
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.convertTo(LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q2.equals(new QuantityLength(1.0, LengthUnit.YARD)));
        System.out.println(q1.add(q2, LengthUnit.YARD));
    }
}