public class QuantityMeasurementApp {

    public static void main(String[] args) {

        double value1 = 5.0;
        double value2 = 5.0;

        boolean result = isEqual(value1, value2);

        System.out.println("Quantity Measurement App - Feet Equality Check");
        System.out.println("Value 1: " + value1 + " ft");
        System.out.println("Value 2: " + value2 + " ft");
        System.out.println("Are both values equal? " + result);
    }

    public static boolean isEqual(double a, double b) {

        if (Double.isNaN(a) || Double.isNaN(b)) {
            System.out.println("Invalid input: Values must be numeric.");
            return false;
        }

        if (Double.isInfinite(a) || Double.isInfinite(b)) {
            System.out.println("Invalid input: Values must be finite.");
            return false;
        }

        return a == b;
    }
}