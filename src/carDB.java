import java.sql.*;

public class carDB {
    
    public void connectToAndQueryDatabase(Car car) {
        
        // 1. Connection Details
        // Added '/car_db' to the URL
        String url = "jdbc:mysql://localhost:3306/car_db";
        String username = "root";
        String password = "password"; // Check your Workbench password
        
        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect
            Connection con = DriverManager.getConnection(url, username, password);
            
            // 2. The SQL Query
            // Matches the 5 inputs from your GUI: Plate, Brand, Model, Year, Speed
            String sql = "INSERT INTO car (plate, brand, model, year, speed) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            // 3. Set the values from the Car object
            pstmt.setString(1, car.getPlate());
            pstmt.setString(2, car.getBrand());
            pstmt.setString(3, car.getModel());
            pstmt.setInt(4, car.getYear());
            pstmt.setInt(5, car.getSpeed());
            
            // Execute
            pstmt.executeUpdate();
            
            // Clean up
            pstmt.close();
            con.close();
            
            System.out.println("Car saved successfully!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}