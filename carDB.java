import java.sql.*;

public class carDB {
    
    public void connectToAndQueryDatabase(Car car) {
        String url = "jdbc:mysql://localhost:3306/car_db";
        String username = "root";
        String password = "password"; 
        
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO car (plate, brand, model, year, speed) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, car.getPlate());
            pstmt.setString(2, car.getBrand());
            pstmt.setString(3, car.getModel());
            pstmt.setInt(4, car.getYear());
            pstmt.setInt(5, car.getSpeed());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("Car saved successfully!");
            
        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
    
    public String[][] getCarsTableData() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/car_db?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "password";
        
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM car ORDER BY id DESC");
        
        // Use ArrayList to collect data (SIMPLE & SAFE)
        java.util.ArrayList<String[]> rows = new java.util.ArrayList<>();
        
        while (rs.next()) {
            String[] row = new String[6];
            row[0] = String.valueOf(rs.getInt("id"));
            row[1] = rs.getString("plate");
            row[2] = rs.getString("brand");
            row[3] = rs.getString("model");
            row[4] = String.valueOf(rs.getInt("year"));
            row[5] = String.valueOf(rs.getInt("speed"));
            rows.add(row);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        // Convert to 2D array
        if (rows.isEmpty()) return new String[0][6];
        return rows.toArray(new String[0][]);
    }


}
