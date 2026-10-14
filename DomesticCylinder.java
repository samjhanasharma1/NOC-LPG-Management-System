/**
 * Child class of LPGCylinder representing a domestic (household) LPG
 * cylinder booking. Handles subsidy calculation and citizenship
 * validation specific to household customers.
 */
public class DomesticCylinder extends LPGCylinder {

    // Private attributes
    private double subsidyAmount;
    private String citizenshipNumber;
    private int quantity;

    // Maximum monthly quota
    private static final int MAX_QUOTA = 2;

    /**
     * Constructs a DomesticCylinder, initializing the shared attributes
     * via the parent constructor and the domestic-specific attributes.
     */
    public DomesticCylinder(int bookingId, String cylinderId, String cylinderType,
                            double basePrice, double weight, String bookingMonth,
                            double subsidyAmount, String citizenshipNumber, int quantity) {

        // Call parent class constructor
        super(bookingId, cylinderId, cylinderType,
              basePrice, weight, bookingMonth);

        // Initialize child class attributes
        setSubsidyAmount(subsidyAmount);
        setCitizenshipNumber(citizenshipNumber);
        setQuantity(quantity);
    }

    /**
     * Returns the subsidy amount for this booking.
     */
    public double getSubsidyAmount() {
        return subsidyAmount;
    }

    /**
     * Sets the subsidy amount, ensuring it is not negative and does not
     * exceed the base price.
     */
    public void setSubsidyAmount(double subsidyAmount) {

        if (subsidyAmount < 0 || subsidyAmount > getBasePrice()) {
            throw new IllegalArgumentException("Invalid subsidy amount.");
        } else {
            this.subsidyAmount = subsidyAmount;
        }
    }

    /**
     * Returns the citizenship number of the customer.
     */
    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    /**
     * Sets the citizenship number, ensuring it is exactly 12 digits.
     */
    public void setCitizenshipNumber(String citizenshipNumber) {

        if (citizenshipNumber != null &&
                citizenshipNumber.matches("\\d{12}")) {

            this.citizenshipNumber = citizenshipNumber;

        } else {

            throw new IllegalArgumentException("Citizenship Number must be exactly 12 digits.");
        }
    }

    /**
     * Returns the quantity of cylinders booked.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity, ensuring it is positive and does not exceed
     * the monthly quota of two cylinders.
     */
    public void setQuantity(int quantity) {

        if (quantity <= 0) {

            throw new IllegalArgumentException("Quantity must be positive.");

        } else if (quantity > MAX_QUOTA) {

            throw new IllegalArgumentException("Maximum quota is " + MAX_QUOTA + " cylinders.");

        } else {

            this.quantity = quantity;
        }
    }

    /**
     * Checks whether this booking is eligible for the government
     * subsidy, based on citizenship number validity and quota.
     */
    public boolean isEligibleForSubsidy() {

        return citizenshipNumber.matches("\\d{12}")
                && quantity <= MAX_QUOTA;
    }

    /**
     * Calculates the per-unit price after subsidy deduction, if the
     * booking is eligible for subsidy.
     */
    public double priceAfterSubsidy() {

        if (isEligibleForSubsidy()) {
            return getBasePrice() - subsidyAmount;
        }

        return getBasePrice();
    }

    /**
     * Calculates the final total price for this booking, being the
     * subsidised unit price multiplied by the quantity booked.
     */
    @Override
    public double calculateFinalPrice() {

        return priceAfterSubsidy() * quantity;
    }

    /**
     * Produces a formatted string containing the full details of this
     * domestic cylinder booking.
     */
    @Override
    public String display() {

        return "----- Domestic Cylinder -----"
                + "\nBooking ID: " + getBookingId()
                + "\nCylinder ID: " + getCylinderId()
                + "\nCylinder Type: " + getCylinderType()
                + "\nBase Price: " + getBasePrice()
                + "\nWeight: " + getWeight()
                + "\nBooking Month: " + getBookingMonth()
                + "\nSubsidy Amount: " + subsidyAmount
                + "\nCitizenship Number: " + citizenshipNumber
                + "\nQuantity: " + quantity
                + "\nPrice After Subsidy: " + priceAfterSubsidy()
                + "\nFinal Price: " + calculateFinalPrice();
    }
}