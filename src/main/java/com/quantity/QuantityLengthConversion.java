package com.quantity;

import java.util.Locale;

/**
 * UC5: Unit-to-Unit Conversion for same measurement type (Length).
 *
 * Provides explicit conversion operations between supported length units.
 */
public final class QuantityLengthConversion {

    private QuantityLengthConversion() {}

    /**
     * Converts a raw numeric value from one length unit to another.
     *
     * @param value numeric value to convert; must be finite
     * @param sourceUnit source unit (non-null)
     * @param targetUnit target unit (non-null)
     * @return converted value in target unit
     * @throws IllegalArgumentException if any unit is null or value is NaN/Infinite
     */
    public static double convert(double value,
                                   QuantityMeasurementApp.LengthUnit sourceUnit,
                                   QuantityMeasurementApp.LengthUnit targetUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (sourceUnit == null) {
            throw new IllegalArgumentException("Source unit cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Normalize to base unit (feet) then convert to target.
        double baseValue = sourceUnit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        // Default precision handling: round to 6 decimals for stable presentation/tests.
        return roundToSixDecimals(converted);
    }

    /**
     * Converts an existing length quantity to a target unit.
     *
     * @param quantity input quantity (non-null)
     * @param targetUnit target unit (non-null)
     * @return a new Quantity representing the converted value
     */
    public static Quantity<QuantityMeasurementApp.LengthUnit> convert(Quantity<QuantityMeasurementApp.LengthUnit> quantity,
                                                                       QuantityMeasurementApp.LengthUnit targetUnit) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return quantity.convertTo(targetUnit);
    }

    private static double roundToSixDecimals(double v) {
        // Avoid negative zero
        double rounded = Math.round(v * 1_000_000d) / 1_000_000d;
        if (rounded == 0d) return 0d;
        return rounded;
    }

    /**
     * Optional helper for displaying conversion results.
     */
    public static String format(double value) {
        return String.format(Locale.ROOT, "%.6f", value);
    }
}

