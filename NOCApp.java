import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Main controller and Graphical User Interface (GUI) for the Nepal Oil
 * Corporation LPG Management System. Built using Java Swing components,
 * this class manages an ArrayList of LPGCylinder objects and provides
 * staff members with controls to add bookings, calculate subsidies and
 * discounts, and handle file operations for data persistence.
 */
public class NOCApp extends JFrame implements ActionListener {

    // Combo Boxes
    private JComboBox<String> cmbMonth;
    private JComboBox<String> cmbType;
    private JComboBox<String> cmbWeight;
    
    // Text Fields
    private JTextField txtBookingId;
    private JTextField txtCylinderId;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTextField txtSubsidy;
    private JTextField txtCitizenship;
    private JTextField txtBusinessLicense;

    // Buttons
    private JButton btnAddDomestic;
    private JButton btnAddCommercial;
    private JButton btnPriceAfterSubsidy;
    private JButton btnBulkDiscount;
    private JButton btnDisplay;
    private JButton btnIdentify;
    private JButton btnClear;
    private JButton btnExport;
    private JButton btnLoad;

  
    // Table,Text Area,ArrayList
    private JTable table;
    private DefaultTableModel model;
    private JTextArea txtLog;
    private ArrayList<LPGCylinder> cylinders;

    /**
     * Constructs the NOCApp main window, initializing the ArrayList of
     * cylinders, building all GUI components (input fields, buttons,
     * table, log area), laying them out in the frame, wiring up action
     * listeners, and making the window visible.
     */
    public NOCApp() {
        cylinders = new ArrayList<>();
        setTitle("Nepal Oil Corporation LPG Management System");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // INPUT PANEL
        JPanel inputPanel =
                new JPanel(new GridLayout(5, 4, 10, 10));

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Cylinder Details"
                )
        );
        // NEPALI MONTHS
        String[] months = {
                "Baishakh",
                "Jestha",
                "Ashadh",
                "Shrawan",
                "Bhadra",
                "Ashwin",
                "Kartik",
                "Mangsir",
                "Poush",
                "Magh",
                "Falgun",
                "Chaitra"
        };
        cmbMonth = new JComboBox<>(months);
        // CYLINDER TYP
        String[] types = {
                "Domestic",
                "Commercial"
        };
        cmbType = new JComboBox<>(types);
        // CYLINDER WEIGHT
        String[] weights = {
                "14.2",
                "19.0"
        };
        cmbWeight = new JComboBox<>(weights);

        // TEXT FIELDs
        txtBookingId = new JTextField();
        txtCylinderId = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        txtSubsidy = new JTextField();
        txtCitizenship = new JTextField();
        txtBusinessLicense = new JTextField();
       
        // ADD INPUT COMPONENTS
        inputPanel.add(
                new JLabel("Booking Month")
        );
        inputPanel.add(cmbMonth);

        inputPanel.add(
                new JLabel("Cylinder Type")
        );
        inputPanel.add(cmbType);

        inputPanel.add(
                new JLabel("Cylinder Weight")
        );
        inputPanel.add(cmbWeight);

        inputPanel.add(
                new JLabel("Booking ID")
        );
        inputPanel.add(txtBookingId);

        inputPanel.add(
                new JLabel("Cylinder ID")
        );
        inputPanel.add(txtCylinderId);

        inputPanel.add(
                new JLabel("Price")
        );
        inputPanel.add(txtPrice);

        inputPanel.add(
                new JLabel("Quantity")
        );
        inputPanel.add(txtQuantity);

        inputPanel.add(
                new JLabel("Subsidy Amount")
        );
        inputPanel.add(txtSubsidy);

        inputPanel.add(
                new JLabel("Citizenship Number")
        );
        inputPanel.add(txtCitizenship);

        inputPanel.add(
                new JLabel("Business License")
        );
        inputPanel.add(txtBusinessLicense);

        // BUTTON PANEL
        JPanel buttonPanel =
                new JPanel(new GridLayout(5, 2, 10, 10));

        buttonPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Actions"
                )
        );
        btnAddDomestic =
                new JButton("Add Domestic");

        btnAddCommercial =
                new JButton("Add Commercial");

        btnPriceAfterSubsidy =
                new JButton("Price After Subsidy");

        btnBulkDiscount =
                new JButton("Bulk Discount");

        btnDisplay =
                new JButton("Display All");

        btnIdentify =
                new JButton("Identify Type");

        btnClear =
                new JButton("Clear");

        btnExport =
                new JButton("Export File");

        btnLoad =
                new JButton("Load File");


        buttonPanel.add(btnAddDomestic);
        buttonPanel.add(btnAddCommercial);

        buttonPanel.add(btnPriceAfterSubsidy);
        buttonPanel.add(btnBulkDiscount);

        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnIdentify);

        buttonPanel.add(btnClear);
        buttonPanel.add(btnExport);

        buttonPanel.add(btnLoad);

        // TABLE
        String[] columns = {
                "Booking ID",
                "Cylinder ID",
                "Type",
                "Weight",
                "Base Price",
                "Subsidy",
                "Citizenship No.",
                "Business License",
                "Quantity",
                "Month"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane tableScroll =
                new JScrollPane(table);

        tableScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Cylinder Records"
                )
        );
        
        // LOG AREA
        
        txtLog = new JTextArea();

        txtLog.setEditable(false);

        JScrollPane logScroll =
                new JScrollPane(txtLog);

        logScroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Messages"
                )
        );

        logScroll.setPreferredSize(
                new Dimension(0, 150)
        );
       
        // RIGHT PANEL

        JPanel rightPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        rightPanel.add(
                tableScroll,
                BorderLayout.CENTER
        );

        rightPanel.add(
                logScroll,
                BorderLayout.SOUTH
        );


        
        // ADD PANELS TO FRAME
        add(
                inputPanel,
                BorderLayout.NORTH
        );

        add(
                buttonPanel,
                BorderLayout.WEST
        );

        add(
                rightPanel,
                BorderLayout.CENTER
        );

        // ACTION LISTENERS
        btnAddDomestic.addActionListener(this);
        btnAddCommercial.addActionListener(this);
        btnPriceAfterSubsidy.addActionListener(this);
        btnBulkDiscount.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnIdentify.addActionListener(this);
        btnClear.addActionListener(this);
        btnExport.addActionListener(this);
        btnLoad.addActionListener(this);


        setVisible(true);
    }

    /**
     * Searches the cylinders ArrayList for a cylinder matching the
     * given cylinder ID, ignoring case.
     */
    private LPGCylinder findCylinderById(String cylinderId) {

        for (LPGCylinder c : cylinders) {

            if (c.getCylinderId()
                    .equalsIgnoreCase(cylinderId)) {

                return c;
            }
        }

        return null;
    }

    /**
     * Checks whether a booking with the given booking ID already
     * exists in the cylinders ArrayList.
     */
    private boolean bookingIdExists(int bookingId) {

        for (LPGCylinder c : cylinders) {

            if (c.getBookingId() == bookingId) {

                return true;
            }
        }

        return false;
    }


    /**
     * Checks whether adding the given quantity would push the
     * specified citizen's domestic bookings for the given month above
     * the monthly quota of two cylinders.
     */
    private boolean domesticQuotaExceeded(
            String citizenship,
            String month,
            int quantity) {

        int total = 0;

        for (LPGCylinder c : cylinders) {

            if (c instanceof DomesticCylinder) {

                DomesticCylinder dc =
                        (DomesticCylinder) c;

                if (dc.getCitizenshipNumber()
                        .equals(citizenship)
                        && dc.getBookingMonth()
                        .equals(month)) {

                    total += dc.getQuantity();
                }
            }
        }

        return (total + quantity) > 2;
    }

    /**
     * Validates that a cylinder ID matches the required format: the
     * prefix "NOC-" followed by one or more digits.
     */
    private boolean validCylinderId(
            String cylinderId) {

        return cylinderId.matches(
                "^NOC-\\d+$"
        );
    }

    /**
     * Handles the "Add Domestic" button action. Reads and validates
     * all input field values, checks business rules (unique booking
     * and cylinder IDs, positive price/weight/quantity, valid
     * citizenship number, subsidy not exceeding price, and monthly
     * quota), then creates a DomesticCylinder, adds it to the
     * ArrayList and the table, and shows a confirmation message.
     * Displays an appropriate error message for any invalid input.
     */
    private void addDomesticCylinder() {

        // Check selected type
        

        if (!cmbType.getSelectedItem()
                .toString()
                .equals("Domestic")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Can't add Domestic type. "
                    + "Please select Domestic."
            );

            return;
        }

        try {

            // Read values

            String bookingText =
                    txtBookingId.getText().trim();

            String cylinderId =
                    txtCylinderId.getText().trim();

            String priceText =
                    txtPrice.getText().trim();

            String quantityText =
                    txtQuantity.getText().trim();

            String subsidyText =
                    txtSubsidy.getText().trim();

            String citizenship =
                    txtCitizenship.getText().trim();

            String month =
                    cmbMonth.getSelectedItem()
                            .toString();

            double weight =
                    Double.parseDouble(
                            cmbWeight
                                    .getSelectedItem()
                                    .toString()
                    );

            // Empty field validation
           
            if (bookingText.isEmpty()
                    || cylinderId.isEmpty()
                    || priceText.isEmpty()
                    || quantityText.isEmpty()
                    || subsidyText.isEmpty()
                    || citizenship.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all required fields."
                );

                return;
            }

            // Booking ID
            int bookingId =
                    Integer.parseInt(
                            bookingText
                    );
            // Cylinder ID
            if (!validCylinderId(cylinderId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cylinder ID must start with "
                        + "NOC- followed by numbers.\n"
                        + "Example: NOC-001"
                );

                txtCylinderId.requestFocus();

                return;
            }

            // Duplicate Booking ID
            if (bookingIdExists(bookingId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID already exists."
                );

                return;
            }

            // Duplicate Cylinder ID
            if (findCylinderById(cylinderId)
                    != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cylinder ID already exists."
                );

                return;
            }

            // Price
            double price =  Double.parseDouble(priceText);

            if (price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero."
                );

                return;
            }
            // Weight
        
            if (weight <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Weight must be greater than zero."
                );

                return;
            }
            // Quantity

            int quantity =
                    Integer.parseInt(quantityText);

            if (quantity <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than zero."
                );

                return;
            }
            // Citizenship

            if (!citizenship.matches(
                    "\\d{12}")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Citizenship Number must be "
                        + "exactly 12 digits."
                );

                txtCitizenship.requestFocus();

                return;
            }
            // Subsidy
            double subsidy =
                    Double.parseDouble(
                            subsidyText
                    );


            if (subsidy < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Subsidy cannot be negative."
                );

                return;
            }


            if (subsidy > price) {

                JOptionPane.showMessageDialog(
                        this,
                        "Subsidy amount cannot be "
                        + "greater than base price."
                );

                return;
            }
            // Domestic monthly quota

            if (domesticQuotaExceeded(
                    citizenship,
                    month,
                    quantity)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Monthly quota exceeded.\n"
                        + "A customer can book a maximum "
                        + "of 2 domestic cylinders per month."
                );

                return;
            }

            // CREATE OBJECT ONLY AFTER VALIDATION
        
            DomesticCylinder dc =
                    new DomesticCylinder(
                            bookingId,
                            cylinderId,
                            "Domestic",
                            price,
                            weight,
                            month,
                            subsidy,
                            citizenship,
                            quantity
                    );


            // Add to ArrayList

            cylinders.add(dc);

            // Add to table

            model.addRow(
                    new Object[] {
                            dc.getBookingId(),
                            dc.getCylinderId(),
                            dc.getCylinderType(),
                            dc.getWeight(),
                            dc.getBasePrice(),
                            dc.getSubsidyAmount(),
                            dc.getCitizenshipNumber(),
                            "-",
                            dc.getQuantity(),
                            dc.getBookingMonth()
                    }
            );


            txtLog.append(
                    "Domestic Cylinder Added Successfully.\n"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Domestic Cylinder Added Successfully."
            );

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking ID, Price, Quantity, "
                    + "Subsidy and Weight must contain "
                    + "valid numbers."
            );
        }

        catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    /**
     * Handles the "Add Commercial" button action. Reads and validates
     * all input field values, checks business rules (unique booking
     * and cylinder IDs, positive price/weight/quantity, and a valid
     * business license), then creates a CommercialCylinder, adds it to
     * the ArrayList and the table, and shows a confirmation message.
     * Displays an appropriate error message for any invalid input.
     */
    private void addCommercialCylinder() {

        // Check selected type

        if (!cmbType.getSelectedItem()
                .toString()
                .equals("Commercial")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Can't add Commercial type. "
                    + "Please select Commercial."
            );

            return;
        }


        try {

            // Read values
          
            String bookingText =
                    txtBookingId.getText().trim();

            String cylinderId =
                    txtCylinderId.getText().trim();

            String priceText =
                    txtPrice.getText().trim();

            String quantityText =
                    txtQuantity.getText().trim();

            String businessLicense =
                    txtBusinessLicense
                            .getText()
                            .trim();

            String month =
                    cmbMonth.getSelectedItem()
                            .toString();

            double weight =
                    Double.parseDouble(
                            cmbWeight
                                    .getSelectedItem()
                                    .toString()
                    );


            
            // Empty fields

            if (bookingText.isEmpty()
                    || cylinderId.isEmpty()
                    || priceText.isEmpty()
                    || quantityText.isEmpty()
                    || businessLicense.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all required fields."
                );

                return;
            }


            
            // Booking ID

            int bookingId =
                    Integer.parseInt(
                            bookingText
                    );


            // Cylinder ID
           
            if (!validCylinderId(cylinderId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cylinder ID must start with "
                        + "NOC- followed by numbers.\n"
                        + "Example: NOC-001"
                );

                txtCylinderId.requestFocus();

                return;
            }


            
            // Duplicate Booking ID

            if (bookingIdExists(bookingId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID already exists."
                );

                return;
            }


            // -----------------------------------------
            // Duplicate Cylinder ID
            // -----------------------------------------

            if (findCylinderById(cylinderId)
                    != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cylinder ID already exists."
                );

                return;
            }

            // Price
       

            double price =
                    Double.parseDouble(
                            priceText
                    );


            if (price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero."
                );

                return;
            }


            
            // Weight

            if (weight <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Weight must be greater than zero."
                );

                return;
            }


          
            // Quantity
            
            int quantity =
                    Integer.parseInt(
                            quantityText
                    );


            if (quantity <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than zero."
                );

                return;
            }


            
            // Business License

            if (businessLicense.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Business License Number is required."
                );

                return;
            }


          
            // CREATE COMMERCIAL OBJECT
           
            CommercialCylinder cc =
                    new CommercialCylinder(
                            bookingId,
                            cylinderId,
                            "Commercial",
                            price,
                            weight,
                            month,
                            businessLicense,
                            quantity
                    );

            // Add to ArrayList

            cylinders.add(cc);

            // Add to table
    
            model.addRow(
                    new Object[] {
                            cc.getBookingId(),
                            cc.getCylinderId(),
                            cc.getCylinderType(),
                            cc.getWeight(),
                            cc.getBasePrice(),
                            "-",
                            "-",
                            cc.getBusinessLicense(),
                            cc.getQuantity(),
                            cc.getBookingMonth()
                    }
            );

            txtLog.append(
                    "Commercial Cylinder Added Successfully.\n"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Commercial Cylinder Added Successfully."
            );

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking ID, Price, Quantity "
                    + "and Weight must contain valid numbers."
            );
        }

        catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    /**
     * Prompts the user to enter a Cylinder ID and returns the matching
     * cylinder from the ArrayList. Used by the "Price After Subsidy"
     * and "Bulk Discount" actions to identify which cylinder to
     * operate on.
     */
    private LPGCylinder getSelectedCylinder() {

        String cylinderId =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Cylinder ID:"
                );

        if (cylinderId == null ||
                cylinderId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cylinder ID is required."
            );

            return null;
        }


        LPGCylinder cylinder =
                findCylinderById(
                        cylinderId.trim()
                );


        if (cylinder == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cylinder ID not found."
            );
        }


        return cylinder;
    }


    
    /**
     * Handles the "Price After Subsidy" button action. Prompts for a
     * Cylinder ID, and if it identifies a DomesticCylinder, displays
     * the price after subsidy. If it identifies a CommercialCylinder,
     * displays a message directing the user to the Bulk Discount
     * action instead, since commercial cylinders receive no subsidy.
     */
    private void calculatePriceAfterSubsidy() {

        LPGCylinder cylinder =
                getSelectedCylinder();


        if (cylinder == null) {

            return;
        }


        if (cylinder instanceof DomesticCylinder) {

            DomesticCylinder dc =
                    (DomesticCylinder) cylinder;


           JOptionPane.showMessageDialog(
    this,
    "Subsidy Amount = Rs. " + dc.getSubsidyAmount()
    + "\nPrice After Subsidy = Rs. " + dc.priceAfterSubsidy()
    + "\nFinal Price = Rs. " + dc.calculateFinalPrice()
        );
        }


        else if (
                cylinder instanceof CommercialCylinder) {

            JOptionPane.showMessageDialog(
                    this,
                    "Commercial cylinders do not "
                    + "receive subsidy.\n"
                    + "Please use Calculate Bulk Discount."
            );
        }


        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unknown cylinder type."
            );
        }
    }


    
    /**
     * Handles the "Bulk Discount" button action. Prompts for a
     * Cylinder ID, and if it identifies a CommercialCylinder, casts
     * the reference and displays the calculated bulk discount. If it
     * identifies a DomesticCylinder, displays a message explaining
     * that bulk discounts only apply to commercial cylinders.
     */
    private void calculateBulkDiscount() {

        LPGCylinder cylinder =
                getSelectedCylinder();


        if (cylinder == null) {

            return;
        }


        if (cylinder instanceof CommercialCylinder) {

            CommercialCylinder cc =
                    (CommercialCylinder) cylinder;


             JOptionPane.showMessageDialog(
                this,
                 "Bulk Discount = Rs. " + cc.calculateBulkDiscount()
                + "\nFinal Price After Discount = Rs. " + cc.calculateFinalPrice()
                );
        }


        else if (
                cylinder instanceof DomesticCylinder) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bulk discount is only available "
                    + "for Commercial cylinders."
            );
        }


        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unknown cylinder type."
            );
        }
    }


  
    /**
     * Handles the "Display All" button action. Clears the log area and
     * appends the display details of every cylinder currently held in
     * the ArrayList. Shows a message if there are no records.
     */
    private void displayAll() {

        txtLog.setText("");


        if (cylinders.isEmpty()) {

            txtLog.append(
                    "No cylinder records available.\n"
            );

            return;
        }


        for (LPGCylinder c : cylinders) {

            txtLog.append(
                    c.display()
                    + "\n\n"
            );
        }
    }


  
    /**
     * Handles the "Identify Type" button action. Prompts the user for
     * a Cylinder ID, looks it up in the ArrayList, and uses the
     * instanceof operator to determine and display whether it is a
     * DomesticCylinder or a CommercialCylinder.
     */
    private void identifyCylinderType() {

        String cylinderId =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Cylinder ID:"
                );


        if (cylinderId == null ||
                cylinderId.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cylinder ID is required."
            );

            return;
        }


        LPGCylinder cylinder =
                findCylinderById(
                        cylinderId.trim()
                );


        if (cylinder == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cylinder ID not found."
            );

            return;
        }


        if (cylinder instanceof DomesticCylinder) {

            JOptionPane.showMessageDialog(
                    this,
                    "Domestic Cylinder"
            );
        }


        else if (
                cylinder instanceof CommercialCylinder) {

            JOptionPane.showMessageDialog(
                    this,
                    "Commercial Cylinder"
            );
        }


        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unknown Cylinder Type"
            );
        }
    }


   
    /**
     * Handles the "Clear" button action. Resets all text fields to
     * empty, resets all combo boxes to their first item, clears the
     * log area, and shows a confirmation message.
     */
    private void clearFields() {

        txtBookingId.setText("");
        txtCylinderId.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtSubsidy.setText("");
        txtCitizenship.setText("");
        txtBusinessLicense.setText("");


        cmbMonth.setSelectedIndex(0);
        cmbType.setSelectedIndex(0);
        cmbWeight.setSelectedIndex(0);


        txtLog.setText("");


        JOptionPane.showMessageDialog(
                this,
                "Fields Cleared Successfully."
        );
    }


    /**
     * Handles the "Export File" button action. Writes the display
     * details of every cylinder in the ArrayList to a character-stream
     * text file named "CylinderData.txt", separating each record with
     * a divider line. Displays a confirmation message on success, or
     * an error message if an IOException occurs.
     */
    private void exportFile() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    "CylinderData.txt"
                            )
                    );


            for (LPGCylinder c : cylinders) {

                writer.println(
                        c.display()
                );

                writer.println(
                        "----------------------------------------"
                );
            }


            writer.close();


            txtLog.append(
                    "Data Exported Successfully.\n"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Data Exported Successfully."
            );
        }

        catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while exporting file."
            );
        }
    }



    /**
     * Handles the "Load File" button action. Reads the contents of
     * "CylinderData.txt" line by line using a character-stream reader
     * and displays them in the log area. Displays an error message if
     * the file cannot be found or read.
     */
    private void loadFile() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "CylinderData.txt"
                            )
                    );


            txtLog.setText("");


            String line;


            while (
                    (line = reader.readLine())
                            != null
            ) {

                txtLog.append(
                        line + "\n"
                );
            }


            reader.close();


            JOptionPane.showMessageDialog(
                    this,
                    "Data Loaded Successfully."
            );
        }

        catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "File not found or cannot be loaded."
            );
        }
    }

    /**
     * Handles all button click events for the application, dispatching
     * to the appropriate handler method based on the event source.
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnAddDomestic) {

            addDomesticCylinder();
        }


        else if (
                e.getSource() == btnAddCommercial) {

            addCommercialCylinder();
        }


        else if (
                e.getSource()
                        == btnPriceAfterSubsidy) {

            calculatePriceAfterSubsidy();
        }


        else if (
                e.getSource()
                        == btnBulkDiscount) {

            calculateBulkDiscount();
        }


        else if (
                e.getSource() == btnDisplay) {

            displayAll();
        }


        else if (
                e.getSource() == btnIdentify) {

            identifyCylinderType();
        }


        else if (
                e.getSource() == btnClear) {

            clearFields();
        }


        else if (
                e.getSource() == btnExport) {

            exportFile();
        }


        else if (
                e.getSource() == btnLoad) {

            loadFile();
        }
    }



    /**
     * Application entry point. Launches the NOCApp GUI on the Swing
     * event dispatch thread.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        new NOCApp();
                    }
                }
        );
    }
}