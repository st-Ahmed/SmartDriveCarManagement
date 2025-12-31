import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.table.DefaultTableModel;


public class CarManagementGUI extends JFrame {

    // Labels for car details
    private JLabel speedLabel;
    private JLabel brandLabel;
    private JLabel modelLabel;
    private JLabel yearLabel;
    private JLabel plateLabel;

    // Text fields for user input
    private JTextField speedField;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField plateField;

    // Save button
    private JButton saveButton;

    public CarManagementGUI() {
        // Set up the JFrame
        setTitle("SmartDrive Rentals");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 380);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 245, 249)); // light blue/grey

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Title label
        JLabel titleLabel = new JLabel("SmartDrive Rentals – Car Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(33, 75, 133)); // dark blue

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Reset layout
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Initialize labels
        speedLabel = new JLabel("Speed:");
        brandLabel = new JLabel("Brand:");
        modelLabel = new JLabel("Model:");
        yearLabel = new JLabel("Year:");
        plateLabel = new JLabel("Plate:");

        // Make labels slightly bold
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        speedLabel.setFont(labelFont);
        brandLabel.setFont(labelFont);
        modelLabel.setFont(labelFont);
        yearLabel.setFont(labelFont);
        plateLabel.setFont(labelFont);

        // Initialize text fields
        speedField = new JTextField(18);
        brandField = new JTextField(18);
        modelField = new JTextField(18);
        yearField = new JTextField(18);
        plateField = new JTextField(18);

        // Tooltips
        speedField.setToolTipText("Enter car speed");
        brandField.setToolTipText("Enter car brand");
        modelField.setToolTipText("Enter car model");
        yearField.setToolTipText("Enter manufacturing year");
        plateField.setToolTipText("Enter license plate");

        // Add speed
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(speedLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(speedField, gbc);

        // Add brand
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(brandLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(brandField, gbc);

        // Add model
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(modelLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(modelField, gbc);

        // Add year
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(yearLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(yearField, gbc);

        // Add plate
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(plateLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(plateField, gbc);

        // Save button
        saveButton = new JButton("Save Car");
        saveButton.setFont(new Font("Arial", Font.BOLD, 13));
        saveButton.setBackground(new Color(0, 122, 255)); // blue
        saveButton.setForeground(Color.black);
        saveButton.setFocusPainted(false);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(saveButton, gbc);

        // Button click logic
        saveButton.addActionListener(e -> onSaveClicked());
        
        JButton viewButton = new JButton("View Cars");
        viewButton.setFont(new Font("Arial", Font.BOLD, 13));
        viewButton.setBackground(new Color(0, 123, 255)); // green
        viewButton.setForeground(Color.black);
        viewButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(viewButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(saveButton, gbc);
        
     // View button logic
        viewButton.addActionListener(e -> viewCars());

        // Add panel to frame
        add(mainPanel);
    }

    // Called when the Save button is clicked
    private void onSaveClicked() {
        try {
            int speed = Integer.parseInt(speedField.getText());
            int year = Integer.parseInt(yearField.getText());

            String brand = brandField.getText();
            String model = modelField.getText();
            String plate = plateField.getText();

            Car car = new Car(plate, brand, model, year,speed);
            saveCar(car);

            JOptionPane.showMessageDialog(this,
                    "Car registered successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Speed and Year must be numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewCars() {
        try {
            carDB DB = new carDB();
            // Get raw data for table
            String[][] data = DB.getCarsTableData();
            String[] columns = {"ID", "Plate", "Brand", "Model", "Year", "Speed"};
            
            // Create table model
            DefaultTableModel tableModel = new DefaultTableModel(data, columns);
            JTable table = new JTable(tableModel);
            table.setEnabled(false); // Read-only
            
            // Scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            
            // Dialog window
            JDialog dialog = new JDialog(this, "All Cars", true);
            dialog.setLayout(new BorderLayout());
            dialog.add(new JLabel("Saved Cars:", JLabel.CENTER), BorderLayout.NORTH);
            dialog.add(scrollPane, BorderLayout.CENTER);
            
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dialog.dispose());
            dialog.add(closeButton, BorderLayout.SOUTH);
            
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void saveCar(Car car) {
        carDB DB= new carDB();
       DB.connectToAndQueryDatabase(car);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            CarManagementGUI frame = new CarManagementGUI();
            frame.setVisible(true);
        });
    }

}