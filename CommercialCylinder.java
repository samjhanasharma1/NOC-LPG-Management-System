/**
 * Child class of LPGCylinder representing a commercial (business) LPG
 * cylinder booking. Handles bulk discount calculation and business
 * license validation specific to commercial customers.
 */
public class CommercialCylinder extends LPGCylinder {

    // Private attributes
    private String businessLicense;
    private int quantity;

    /**
     * Constructs a CommercialCylinder, initializing the shared
     * attributes via the parent constructor and the commercial-specific
     * attributes.
     */
    public CommercialCylinder(int bookingId, String cylinderId, String cylinderType, double basePrice,
         double weight, String bookingMonth,  String businessLicense, int quantity) {

        // Call parent class constructor
        super(bookingId, cylinderId, cylinderType,  basePrice, weight, bookingMonth);

        // Initialize child class attributes
        setBusinessLicense(businessLicense);
        setQuantity(quantity);
    }

    /**
     * Returns the business license number.
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * Sets the business license number, ensuring it is not null or empty.
     */
    public void setBusinessLicense(String businessLicense) {

        if (businessLicense == null || businessLicense.trim().isEmpty()) {
            throw new IllegalArgumentException("Business license cannot be empty.");
        } else {
            this.businessLicense = businessLicense;
        }
    }

    /**
     * Returns the quantity of cylinders booked.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity, ensuring it is a positive value.
     */
    public void setQuantity(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        } else {
            this.quantity = quantity;
        }
    }

    /**
     * Calculates the bulk discount applicable to this commercial
     * booking. Applies 5% for quantities of 10 or more, 3% for
     * quantities of 5 to 9, and no discount below that.
     */
    public double calculateBulkDiscount() {

        double totalPrice = getBasePrice() * quantity;

        if (quantity >= 10) {
            return totalPrice * 0.05;
        } else if (quantity >= 5) {
            return totalPrice * 0.03;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the final total price for this booking, applying the
     * bulk discount where the quantity qualifies.
     */
    @Override
    public double calculateFinalPrice() {

        double totalPrice = getBasePrice() * quantity;

        if (quantity >= 5) {
            totalPrice -= calculateBulkDiscount();
        }

        return totalPrice;
    }

    /**
     * Produces a formatted string containing the full details of this
     * commercial cylinder booking.
     */
    @Override
    public String display() {

        return "----- Commercial Cylinder -----"
                + "\nBooking ID: " + getBookingId()
                + "\nCylinder ID: " + getCylinderId()
                + "\nCylinder Type: " + getCylinderType()
                + "\nBase Price: " + getBasePrice()
                + "\nWeight: " + getWeight()
                + "\nBooking Month: " + getBookingMonth()
                + "\nBusiness License: " + businessLicense
                + "\nQuantity: " + quantity
                + "\nBulk Discount: " + calculateBulkDiscount()
                + "\nFinal Price: " + calculateFinalPrice();
    }
}