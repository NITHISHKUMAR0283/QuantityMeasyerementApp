import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        var q1 = new Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_NullComparison() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        var q1 = new Quantity<>(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        var q1 = new Quantity<>(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_CentimeterToCentimeter_SameValue() {
        var q1 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CentimeterToInch_EquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new Quantity<>(0.393701, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q3 = new Quantity<>(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }

    @Test
    void testEquality_YardWithNullUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_YardSameReference() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_YardNullComparison() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        var q1 = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        var q1 = new Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new Quantity<>(6.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q3 = new Quantity<>(72.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }
    // --- UC11: Volume Measurement Tests ---
    @Test
    void testEquality_LitreToLitre_SameValue() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        var q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        var q1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        var q1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        var q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        var v = new Quantity<>(1.0, VolumeUnit.LITRE);
        var l = new Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(v.equals(l));
        assertFalse(l.equals(v));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        var v = new Quantity<>(1.0, VolumeUnit.LITRE);
        var w = new Quantity<>(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        assertFalse(v.equals(w));
        assertFalse(w.equals(v));
    }

    @Test
    void testEquality_NullComparison_Volume() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_SameReference_Volume() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_NullUnit_Volume() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_TransitiveProperty_Volume() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var q3 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }

    @Test
    void testEquality_ZeroValue_Volume() {
        var q1 = new Quantity<>(0.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_NegativeVolume() {
        var q1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LargeVolumeValue() {
        var q1 = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SmallVolumeValue() {
        var q1 = new Quantity<>(0.001, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_LitreToMillilitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        var q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var result = q1.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_GallonToLitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        var result = q1.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), 1e-5);
    }

    @Test
    void testConversion_LitreToGallon() {
        var q1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), 1e-5);
    }

    @Test
    void testConversion_MillilitreToGallon() {
        var q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var result = q1.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, result.getValue(), 1e-5);
    }

    @Test
    void testConversion_SameUnit_Volume() {
        var q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.LITRE);
        assertEquals(5.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_ZeroValue_Volume() {
        var q1 = new Quantity<>(0.0, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_NegativeValue_Volume() {
        var q1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_RoundTrip_Volume() {
        var q1 = new Quantity<>(1.5, VolumeUnit.LITRE);
        var result = q1.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertEquals(1.5, result.getValue(), 1e-6);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        var sum = q1.add(q2);
        assertEquals(3.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre() {
        var q1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        var q2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2);
        assertEquals(1000.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.MILLILITRE, sum.getUnit());
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2);
        assertEquals(2.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        var q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var sum = q1.add(q2);
        assertEquals(2000.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.MILLILITRE, sum.getUnit());
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        var q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var sum = q1.add(q2);
        assertEquals(2.0, sum.getValue(), 1e-5);
        assertEquals(VolumeUnit.GALLON, sum.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Litre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2, VolumeUnit.LITRE);
        assertEquals(2.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2, VolumeUnit.MILLILITRE);
        assertEquals(2000.0, sum.getValue(), 1e-6);
        assertEquals(VolumeUnit.MILLILITRE, sum.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        var q1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var q2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        var sum = q1.add(q2, VolumeUnit.GALLON);
        assertEquals(2.0, sum.getValue(), 1e-5);
        assertEquals(VolumeUnit.GALLON, sum.getUnit());
    }

    @Test
    void testAddition_Commutativity_Volume() {
        var q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        var sum1 = q1.add(q2);
        var sum2 = q2.add(q1);
        assertEquals(sum1.getValue(), sum2.convertTo(sum1.getUnit()).getValue(), 1e-6);
    }

    @Test
    void testAddition_WithZero_Volume() {
        var q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2);
        assertEquals(5.0, sum.getValue(), 1e-6);
    }

    @Test
    void testAddition_NegativeValues_Volume() {
        var q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        var q2 = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        var sum = q1.add(q2);
        assertEquals(3.0, sum.getValue(), 1e-6);
    }

    @Test
    void testAddition_LargeValues_Volume() {
        var q1 = new Quantity<>(1e6, VolumeUnit.LITRE);
        var q2 = new Quantity<>(1e6, VolumeUnit.LITRE);
        var sum = q1.add(q2);
        assertEquals(2e6, sum.getValue(), 1e-2);
    }
}
