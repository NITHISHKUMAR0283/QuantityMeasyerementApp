package com.quantity;

public enum VolumeUnit implements IMeasurable {
    LITRE(1.0, "Litre"),
    MILLILITRE(0.001, "Millilitre"),
    GALLON(3.78541, "Gallon");

    private final double conversionFactorToLitre;
    private final String unitName;

    VolumeUnit(double conversionFactorToLitre, String unitName) {
        this.conversionFactorToLitre = conversionFactorToLitre;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactorToLitre;
    }

    @Override
    public double convertToBaseUnit(double value) {
        // Convert any unit to litres
        return value * conversionFactorToLitre;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        // Convert from litres to this unit
        return baseValue / conversionFactorToLitre;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }
}