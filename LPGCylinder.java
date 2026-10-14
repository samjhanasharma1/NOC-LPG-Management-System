/**
 * Abstract parent class representing a generic LPG cylinder booking
 * managed by Nepal Oil Corporation. Holds the attributes and validation
 * logic common to both domestic and commercial cylinder types.
 */
public abstract class LPGCylinder {

    // Private attributes
    private int bookingId;
    private String cylinderId;
    private String cylinderType;
    private double basePrice;
    private double weight;
    private String bookingMonth;

    /**
     * Constructs an LPGCylinder with the common attributes shared by
     * all cylinder types.
     */
    public LPGCylinder(int bookingId, String cylinderId,
                       String cylinderType, double basePrice,
                       double weight, String bookingMonth) {

        // Initialize common attributes
        setBookingId(bookingId);
        setCylinderId(cylinderId);
        setCylinderType(cylinderType);
        setBasePrice(basePrice);
        setWeight(weight);
        setBookingMonth(bookingMonth);
    }

    /**
     * Returns the booking ID of this cylinder.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID, ensuring it is a positive value.
     */
    public void setBookingId(int bookingId) {

        if (bookingId > 0) {
            this.bookingId = bookingId;
        } else {
            throw new IllegalArgumentException("Invalid Booking ID. Must be positive.");
        }
    }

    /**
     * Returns the cylinder ID.
     */
    public String getCylinderId() {
        return cylinderId;
    }

    /**
     * Sets the cylinder ID, ensuring it matches the required format
     * "NOC-" followed by one or more digits.
     */
    public void setCylinderId(String cylinderId) {

        if (cylinderId == null || cylinderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cylinder ID cannot be empty.");
        }

        if (cylinderId.matches("^NOC-\\d+$")) {

            this.cylinderId = cylinderId;

        } else {

            throw new IllegalArgumentException("Invalid Cylinder ID format.");
        }
    }

    /**
     * Returns the cylinder type.
     */
    public String getCylinderType() {
        return cylinderType;
    }

    /**
     * Sets the cylinder type, ensuring it is not null or empty
     */
    public void setCylinderType(String cylinderType) {

        if (cylinderType != null && !cylinderType.trim().isEmpty()) {
            this.cylinderType = cylinderType;
        } else {
            throw new IllegalArgumentException("Cylinder Type cannot be empty.");
        }
    }

    /**
     * Returns the base price of the cylinder.
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price, ensuring it is greater than zero.
     */
    public void setBasePrice(double basePrice) {

        if (basePrice > 0) {
            this.basePrice = basePrice;
        } else {
            throw new IllegalArgumentException("Price must be positive.");
        }
    }

    /**
     * Returns the weight of the cylinder.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight, ensuring it is greater than zero.
     */
    public void setWeight(double weight) {

        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight must be positive.");
        }
    }

    /**
     * Returns the booking month.
     */
    public String getBookingMonth() {
        return bookingMonth;
    }

    /**
     * Sets the booking month, ensuring it is not null or empty.
     */
    public void setBookingMonth(String bookingMonth) {

        if (bookingMonth != null && !bookingMonth.trim().isEmpty()) {
            this.bookingMonth = bookingMonth;
        } else {
            throw new IllegalArgumentException("Booking month cannot be empty.");
        }
    }

    /**
     * Calculates the final price for this cylinder booking, applying
     * whatever pricing rule is specific to the concrete subclass.
     */
    public abstract double calculateFinalPrice();

    /**
     * Produces a formatted string containing the full details of this
     * cylinder, as implemented by each concrete subclass.
     */
    public abstract String display();
}