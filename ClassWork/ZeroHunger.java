import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class User {
    private static int idCounter = 1000;
    private int userId;
    private String name;
    private String role;
    private String contact;

    public User(String name, String role, String contact) {
        this.userId = idCounter++;
        this.name = name;
        this.role = role;
        this.contact = contact;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getContact() { return contact; }
}

class FoodListing {
    private static int listingIdCounter = 5000;
    private int listingId;
    private String foodTitle;
    private int quantity;
    private String location;
    private int donorId;
    private String donorContact;
    private String status;
    private Integer reservedBy;
    private String reservedByName;
    private String reservedByContact;

    public FoodListing(String foodTitle, int quantity, String location, int donorId, String donorContact) {
        this.listingId = listingIdCounter++;
        this.foodTitle = foodTitle;
        this.quantity = quantity;
        this.location = location;
        this.donorId = donorId;
        this.donorContact = donorContact;
        this.status = "Available";
    }

    public int getListingId() { return listingId; }
    public String getFoodTitle() { return foodTitle; }
    public int getQuantity() { return quantity; }
    public String getLocation() { return location; }
    public int getDonorId() { return donorId; }
    public String getDonorContact() { return donorContact; }
    public String getStatus() { return status; }
    public Integer getReservedBy() { return reservedBy; }
    public String getReservedByName() { return reservedByName; }
    public String getReservedByContact() { return reservedByContact; }

    public void reserve(int customerId, String customerName, String customerContact) {
        // Decrease quantity by 1
        this.quantity -= 1;
        
        // If quantity reaches 0, mark as reserved
        if (this.quantity == 0) {
            this.status = "Reserved";
            this.reservedBy = customerId;
            this.reservedByName = customerName;
            this.reservedByContact = customerContact;
        }
    }

    public boolean isAvailable() {
        return quantity > 0 && "Available".equals(status);
    }
}

class Reservation {
    private static int reservationIdCounter = 8000;
    private int reservationId;
    private int listingId;
    private String foodTitle;
    private String location;
    private int customerId;
    private String customerName;
    private String customerContact;
    private String donorContact;
    private int quantity;

    public Reservation(int listingId, String foodTitle, String location, 
                      int customerId, String customerName, String customerContact, 
                      String donorContact, int quantity) {
        this.reservationId = reservationIdCounter++;
        this.listingId = listingId;
        this.foodTitle = foodTitle;
        this.location = location;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.donorContact = donorContact;
        this.quantity = quantity;
    }

    public int getReservationId() { return reservationId; }
    public int getListingId() { return listingId; }
    public String getFoodTitle() { return foodTitle; }
    public String getLocation() { return location; }
    public int getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerContact() { return customerContact; }
    public String getDonorContact() { return donorContact; }
    public int getQuantity() { return quantity; }
}

class ZeroHungerApp extends JFrame {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<FoodListing> foodListings = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private User currentUser = null;

    public ZeroHungerApp() {
        setTitle("Zero Hunger App - Fighting Food Waste");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showMainMenu();
    }

    private void showMainMenu() {
        try {
            currentUser = null;
            getContentPane().removeAll();
            setLayout(new BorderLayout(10, 10));

            JPanel headerPanel = new JPanel();
            headerPanel.setBackground(new Color(46, 125, 50));
            JLabel titleLabel = new JLabel("🌾 ZERO HUNGER APP 🌾");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(Color.WHITE);
            headerPanel.add(titleLabel);

            JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

            JLabel welcomeLabel = new JLabel("Fighting Food Waste, Feeding Communities", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.ITALIC, 14));

            JButton registerBtn = createStyledButton("Register", new Color(76, 175, 80));
            JButton loginBtn = createStyledButton("Login", new Color(33, 150, 243));
            JButton viewListingsBtn = createStyledButton("View All Listings", new Color(255, 152, 0));
            JButton exitBtn = createStyledButton("Exit", new Color(244, 67, 54));

            registerBtn.addActionListener(e -> safeRun(this::showRegisterDialog));
            loginBtn.addActionListener(e -> safeRun(this::showLoginDialog));
            viewListingsBtn.addActionListener(e -> safeRun(this::showAllListings));
            exitBtn.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Thank you for using Zero Hunger App!\nAre you sure you want to exit?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) System.exit(0);
            });

            centerPanel.add(welcomeLabel);
            centerPanel.add(registerBtn);
            centerPanel.add(loginBtn);
            centerPanel.add(viewListingsBtn);
            centerPanel.add(exitBtn);

            add(headerPanel, BorderLayout.NORTH);
            add(centerPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
        } catch (Exception e) {
            showError("Error displaying main menu", e);
        }
    }

    private void showRegisterDialog() {
        try {
            JDialog dialog = new JDialog(this, "User Registration", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JTextField nameField = new JTextField();
            JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Donor", "Customer"});
            JTextField contactField = new JTextField();

            formPanel.add(new JLabel("Name:"));
            formPanel.add(nameField);
            formPanel.add(new JLabel("Role:"));
            formPanel.add(roleCombo);
            formPanel.add(new JLabel("Contact:"));
            formPanel.add(contactField);

            JButton registerBtn = createStyledButton("Register", new Color(76, 175, 80));
            JButton cancelBtn = createStyledButton("Cancel", new Color(158, 158, 158));

            registerBtn.addActionListener(e -> {
                try {
                    String name = nameField.getText().trim();
                    String role = (String) roleCombo.getSelectedItem();
                    String contact = contactField.getText().trim();

                    if (name.isEmpty() || contact.isEmpty()) {
                        throw new IllegalArgumentException("All fields must be filled!");
                    }

                    User newUser = new User(name, role, contact);
                    users.add(newUser);
                    JOptionPane.showMessageDialog(dialog,
                            "✓ Registration Successful!\nYour User ID: " + newUser.getUserId(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (IllegalArgumentException ex) {
                    showError("Invalid registration data", ex);
                } catch (Exception ex) {
                    showError("Unexpected error during registration", ex);
                }
            });

            cancelBtn.addActionListener(e -> dialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(registerBtn);
            buttonPanel.add(cancelBtn);

            dialog.add(formPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        } catch (Exception e) {
            showError("Error showing registration dialog", e);
        }
    }

    private void showLoginDialog() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Your User ID:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (input == null || input.trim().isEmpty()) return;

            int userId = Integer.parseInt(input.trim());
            for (User user : users) {
                if (user.getUserId() == userId) {
                    currentUser = user;
                    JOptionPane.showMessageDialog(this,
                            "Welcome, " + user.getName() + " (" + user.getRole() + ")",
                            "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    if (user.getRole().equalsIgnoreCase("Donor"))
                        showDonorMenu();
                    else
                        showCustomerMenu();
                    return;
                }
            }
            throw new IllegalStateException("User not found! Please register first.");
        } catch (NumberFormatException e) {
            showError("Invalid User ID format", e);
        } catch (IllegalStateException e) {
            showError(e.getMessage(), e);
        } catch (Exception e) {
            showError("Unexpected error during login", e);
        }
    }

    private void showDonorMenu() {
        try {
            getContentPane().removeAll();
            setLayout(new GridLayout(5, 1, 10, 10));
            
            JButton createBtn = createStyledButton("Create New Listing", new Color(76, 175, 80));
            JButton myListingsBtn = createStyledButton("My Listings", new Color(33, 150, 243));
            JButton reservationsBtn = createStyledButton("View Reservations", new Color(255, 152, 0));
            JButton logoutBtn = createStyledButton("Logout", new Color(244, 67, 54));

            createBtn.addActionListener(e -> safeRun(this::showCreateListingDialog));
            myListingsBtn.addActionListener(e -> safeRun(this::showMyListings));
            reservationsBtn.addActionListener(e -> safeRun(this::showReservations));
            logoutBtn.addActionListener(e -> safeRun(this::showMainMenu));

            add(createBtn);
            add(myListingsBtn);
            add(reservationsBtn);
            add(logoutBtn);
            
            revalidate();
            repaint();
        } catch (Exception e) {
            showError("Error displaying donor menu", e);
        }
    }

    private void showCreateListingDialog() {
        try {
            JDialog dialog = new JDialog(this, "Create Food Listing", true);
            dialog.setSize(450, 350);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JTextField titleField = new JTextField();
            JTextField quantityField = new JTextField();
            JTextField locationField = new JTextField();

            formPanel.add(new JLabel("Food Title:"));
            formPanel.add(titleField);
            formPanel.add(new JLabel("Quantity:"));
            formPanel.add(quantityField);
            formPanel.add(new JLabel("Location:"));
            formPanel.add(locationField);

            JButton createBtn = createStyledButton("Create Listing", new Color(76, 175, 80));
            JButton cancelBtn = createStyledButton("Cancel", new Color(158, 158, 158));

            createBtn.addActionListener(e -> {
                try {
                    String title = titleField.getText().trim();
                    String qtyStr = quantityField.getText().trim();
                    String location = locationField.getText().trim();

                    if (title.isEmpty() || qtyStr.isEmpty() || location.isEmpty()) {
                        throw new IllegalArgumentException("All fields must be filled!");
                    }

                    int quantity = Integer.parseInt(qtyStr);
                    if (quantity <= 0) {
                        throw new IllegalArgumentException("Quantity must be positive!");
                    }

                    FoodListing listing = new FoodListing(title, quantity, location,
                            currentUser.getUserId(), currentUser.getContact());
                    foodListings.add(listing);

                    JOptionPane.showMessageDialog(dialog,
                            "✓ Listing Created Successfully!\nListing ID: " + listing.getListingId(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    showError("Invalid quantity format", ex);
                } catch (IllegalArgumentException ex) {
                    showError("Invalid listing data", ex);
                } catch (Exception ex) {
                    showError("Unexpected error creating listing", ex);
                }
            });

            cancelBtn.addActionListener(e -> dialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(createBtn);
            buttonPanel.add(cancelBtn);

            dialog.add(formPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        } catch (Exception e) {
            showError("Error showing create listing dialog", e);
        }
    }

    private void showMyListings() {
        try {
            ArrayList<FoodListing> myListings = new ArrayList<>();
            for (FoodListing listing : foodListings) {
                if (listing.getDonorId() == currentUser.getUserId()) {
                    myListings.add(listing);
                }
            }

            if (myListings.isEmpty()) {
                throw new IllegalStateException("You haven't created any listings yet!");
            }

            JDialog dialog = new JDialog(this, "My Food Listings", true);
            dialog.setSize(900, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            String[] columns = {"ID", "Food", "Qty", "Location", "Status"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for (FoodListing listing : myListings) {
                model.addRow(new Object[]{
                        listing.getListingId(),
                        listing.getFoodTitle(),
                        listing.getQuantity(),
                        listing.getLocation(),
                        listing.getStatus()
                });
            }

            JTable table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFillsViewportHeight(true);
            dialog.add(new JScrollPane(table), BorderLayout.CENTER);

            JButton closeBtn = createStyledButton("Close", new Color(158, 158, 158));
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeBtn);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            showError("Error displaying your listings", e);
        }
    }

    private void showReservations() {
        try {
            ArrayList<Reservation> myReservations = new ArrayList<>();
            for (Reservation reservation : reservations) {
                // Find the listing to check if it's owned by current donor
                for (FoodListing listing : foodListings) {
                    if (listing.getListingId() == reservation.getListingId() && 
                        listing.getDonorId() == currentUser.getUserId()) {
                        myReservations.add(reservation);
                        break;
                    }
                }
            }

            if (myReservations.isEmpty()) {
                throw new IllegalStateException("No reservations for your listings yet!");
            }

            JDialog dialog = new JDialog(this, "Reservations for My Listings", true);
            dialog.setSize(900, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            String[] columns = {"Reservation ID", "Listing ID", "Food", "Qty", "Reserved By", "Contact"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for (Reservation reservation : myReservations) {
                model.addRow(new Object[]{
                        reservation.getReservationId(),
                        reservation.getListingId(),
                        reservation.getFoodTitle(),
                        reservation.getQuantity(),
                        reservation.getCustomerName(),
                        reservation.getCustomerContact()
                });
            }

            JTable table = new JTable(model);
            table.setFillsViewportHeight(true);
            dialog.add(new JScrollPane(table), BorderLayout.CENTER);

            JButton closeBtn = createStyledButton("Close", new Color(158, 158, 158));
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeBtn);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            showError("Error displaying reservations", e);
        }
    }

    private void showCustomerMenu() {
        try {
            getContentPane().removeAll();
            setLayout(new GridLayout(4, 1, 10, 10));
            
            JButton viewBtn = createStyledButton("View Available Listings", new Color(76, 175, 80));
            JButton requestBtn = createStyledButton("Request Food", new Color(255, 152, 0));
            JButton myRequestsBtn = createStyledButton("My Requests", new Color(156, 39, 176));
            JButton logoutBtn = createStyledButton("Logout", new Color(244, 67, 54));

            viewBtn.addActionListener(e -> safeRun(this::showAvailableListings));
            requestBtn.addActionListener(e -> safeRun(this::showRequestFoodDialog));
            myRequestsBtn.addActionListener(e -> safeRun(this::showMyRequests));
            logoutBtn.addActionListener(e -> safeRun(this::showMainMenu));

            add(viewBtn);
            add(requestBtn);
            add(myRequestsBtn);
            add(logoutBtn);
            
            revalidate();
            repaint();
        } catch (Exception e) {
            showError("Error displaying customer menu", e);
        }
    }

    private void showAvailableListings() {
        try {
            ArrayList<FoodListing> availableListings = new ArrayList<>();
            for (FoodListing listing : foodListings) {
                if (listing.isAvailable()) {
                    availableListings.add(listing);
                }
            }

            if (availableListings.isEmpty()) {
                throw new IllegalStateException("No available food listings at the moment!");
            }

            JDialog dialog = new JDialog(this, "Available Food Listings", true);
            dialog.setSize(900, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            String[] columns = {"ID", "Food", "Qty", "Location", "Donor Contact"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for (FoodListing listing : availableListings) {
                model.addRow(new Object[]{
                        listing.getListingId(),
                        listing.getFoodTitle(),
                        listing.getQuantity(),
                        listing.getLocation(),
                        listing.getDonorContact()
                });
            }

            JTable table = new JTable(model);
            table.setFillsViewportHeight(true);
            dialog.add(new JScrollPane(table), BorderLayout.CENTER);

            JButton closeBtn = createStyledButton("Close", new Color(158, 158, 158));
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeBtn);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            showError("Error displaying available listings", e);
        }
    }

    private void showRequestFoodDialog() {
        try {
            String input = JOptionPane.showInputDialog(this,
                    "Enter Listing ID to request:",
                    "Request Food",
                    JOptionPane.QUESTION_MESSAGE);

            if (input == null || input.trim().isEmpty()) return;

            int listingId = Integer.parseInt(input.trim());
            FoodListing targetListing = null;

            for (FoodListing listing : foodListings) {
                if (listing.getListingId() == listingId) {
                    targetListing = listing;
                    break;
                }
            }

            if (targetListing == null) {
                throw new IllegalArgumentException("Listing ID not found!");
            }

            if (!targetListing.isAvailable()) {
                throw new IllegalStateException("This listing is not available or out of stock!");
            }

            // Create a reservation record
            Reservation newReservation = new Reservation(
                targetListing.getListingId(),
                targetListing.getFoodTitle(),
                targetListing.getLocation(),
                currentUser.getUserId(),
                currentUser.getName(),
                currentUser.getContact(),
                targetListing.getDonorContact(),
                1  // Always reserve quantity of 1
            );
            reservations.add(newReservation);

            // Decrease quantity in the listing
            targetListing.reserve(currentUser.getUserId(),
                    currentUser.getName(),
                    currentUser.getContact());

            JOptionPane.showMessageDialog(this,
                    "✓ Food Request Successful!\n\n" +
                            "Food: " + targetListing.getFoodTitle() + "\n" +
                            "Quantity Reserved: 1\n" +
                            "Remaining Quantity: " + targetListing.getQuantity() + "\n" +
                            "Location: " + targetListing.getLocation() + "\n" +
                            "Donor Contact: " + targetListing.getDonorContact(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            showError("Invalid Listing ID format", e);
        } catch (IllegalArgumentException | IllegalStateException e) {
            showError(e.getMessage(), e);
        } catch (Exception e) {
            showError("Unexpected error requesting food", e);
        }
    }

    private void showMyRequests() {
        try {
            ArrayList<Reservation> myRequests = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (reservation.getCustomerId() == currentUser.getUserId()) {
                    myRequests.add(reservation);
                }
            }

            if (myRequests.isEmpty()) {
                throw new IllegalStateException("You haven't requested any food yet!");
            }

            JDialog dialog = new JDialog(this, "My Food Requests", true);
            dialog.setSize(900, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            String[] columns = {"Reservation ID", "Listing ID", "Food", "Qty", "Location", "Donor Contact"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            for (Reservation reservation : myRequests) {
                model.addRow(new Object[]{
                        reservation.getReservationId(),
                        reservation.getListingId(),
                        reservation.getFoodTitle(),
                        reservation.getQuantity(),
                        reservation.getLocation(),
                        reservation.getDonorContact()
                });
            }

            JTable table = new JTable(model);
            table.setFillsViewportHeight(true);
            dialog.add(new JScrollPane(table), BorderLayout.CENTER);

            JButton closeBtn = createStyledButton("Close", new Color(158, 158, 158));
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeBtn);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            showError("Error displaying your requests", e);
        }
    }

    private void showAllListings() {
        try {
            JDialog dialog = new JDialog(this, "All Food Listings", true);
            dialog.setSize(900, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            String[] columns = {"ID", "Food", "Qty", "Location", "Status", "Donor Contact"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            if (foodListings.isEmpty()) {
                JTable table = new JTable(model);
                table.setFillsViewportHeight(true);
                
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JScrollPane(table), BorderLayout.CENTER);
                
                JLabel noDataLabel = new JLabel("No data available", SwingConstants.CENTER);
                noDataLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                noDataLabel.setForeground(Color.GRAY);
                panel.add(noDataLabel, BorderLayout.NORTH);
                
                dialog.add(panel, BorderLayout.CENTER);
            } else {
                for (FoodListing listing : foodListings) {
                    model.addRow(new Object[]{
                            listing.getListingId(),
                            listing.getFoodTitle(),
                            listing.getQuantity(),
                            listing.getLocation(),
                            listing.getStatus(),
                            listing.getDonorContact()
                    });
                }

                JTable table = new JTable(model);
                table.setFillsViewportHeight(true);
                dialog.add(new JScrollPane(table), BorderLayout.CENTER);
            }

            JButton closeBtn = createStyledButton("Close", new Color(158, 158, 158));
            closeBtn.addActionListener(e -> dialog.dispose());
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeBtn);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        } catch (Exception e) {
            showError("Error displaying listings", e);
        }
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void safeRun(Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            showError("An unexpected error occurred", e);
        }
    }

    private void showError(String message, Exception e) {
        JOptionPane.showMessageDialog(this,
                message + "\n\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

public class ZeroHunger {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ZeroHungerApp app = new ZeroHungerApp();
                app.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Critical startup error: " + e.getMessage(),
                        "Fatal Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}