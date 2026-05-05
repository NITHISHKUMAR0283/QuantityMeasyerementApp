
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.quantity.QuantityMeasurementApp.WeightUnit;

class QuantityWeightTest {

class QuantityWeightTest {
    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        var q1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        var q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquality_PoundToKilogram_EquivalentValue() {
        var q1 = new Quantity<>(2.20462, WeightUnit.POUND);
        var q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(q1.convertTo(WeightUnit.KILOGRAM).getValue(), q2.getValue(), 1e-5);
    }

    @Test
    void testEquality_GramToPound_EquivalentValue() {
        var q1 = new Quantity<>(453.592, WeightUnit.GRAM);
        var q2 = new Quantity<>(1.0, WeightUnit.POUND);
        assertEquals(q1.convertTo(WeightUnit.POUND).getValue(), q2.getValue(), 1e-5);
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        var w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var l = new Quantity<>(1.0, com.quantity.QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(w.equals(l));
        assertFalse(l.equals(w));
    }

    @Test
    void testEquality_NullComparison() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(q1.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_NullUnit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_TransitiveProperty() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        var q3 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3));
    }

    @Test
    void testEquality_ZeroValue() {
        var q1 = new Quantity<>(0.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(0.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_NegativeWeight() {
        var q1 = new Quantity<>(-1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(-1000.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_LargeWeightValue() {
        var q1 = new Quantity<>(1000000.0, WeightUnit.GRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_SmallWeightValue() {
        var q1 = new Quantity<>(0.001, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_PoundToKilogram() {
        var q1 = new Quantity<>(2.20462, WeightUnit.POUND);
        var result = q1.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 1e-5);
    }

    @Test
    void testConversion_KilogramToPound() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var result = q1.convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), 1e-5);
    }

    @Test
    void testConversion_SameUnit() {
        var q1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        var result = q1.convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_ZeroValue() {
        var q1 = new Quantity<>(0.0, WeightUnit.KILOGRAM);
        var result = q1.convertTo(WeightUnit.GRAM);
        assertEquals(0.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_NegativeValue() {
        var q1 = new Quantity<>(-1.0, WeightUnit.KILOGRAM);
        var result = q1.convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_RoundTrip() {
        var q1 = new Quantity<>(1.5, WeightUnit.KILOGRAM);
        var result = q1.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, result.getValue(), 1e-6);
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        var sum = q1.add(q2);
        assertEquals(3.0, sum.getValue(), 1e-6);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        var sum = q1.add(q2);
        assertEquals(2.0, sum.getValue(), 1e-6);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        var q1 = new Quantity<>(2.20462, WeightUnit.POUND);
        var q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var sum = q1.add(q2);
        assertEquals(4.40924, sum.getValue(), 1e-5);
        assertEquals(WeightUnit.POUND, sum.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        var sum = q1.add(q2, WeightUnit.GRAM);
        assertEquals(2000.0, sum.getValue(), 1e-6);
        assertEquals(WeightUnit.GRAM, sum.getUnit());
    }

    @Test
    void testAddition_Commutativity() {
        var q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        var sum1 = q1.add(q2);
        var sum2 = q2.add(q1);
        assertEquals(sum1.getValue(), sum2.convertTo(sum1.getUnit()).getValue(), 1e-6);
    }

    @Test
    void testAddition_WithZero() {
        var q1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(0.0, WeightUnit.GRAM);
        var sum = q1.add(q2);
        assertEquals(5.0, sum.getValue(), 1e-6);
    }

    @Test
    void testAddition_NegativeValues() {
        var q1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(-2000.0, WeightUnit.GRAM);
        var sum = q1.add(q2);
        assertEquals(3.0, sum.getValue(), 1e-6);
    }

    @Test
    void testAddition_LargeValues() {
        var q1 = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        var q2 = new Quantity<>(1e6, WeightUnit.KILOGRAM);
        var sum = q1.add(q2);
        assertEquals(2e6, sum.getValue(), 1e-2);
    }
}
