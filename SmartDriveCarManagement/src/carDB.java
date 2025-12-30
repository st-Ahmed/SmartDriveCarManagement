import java.sql.*;

public class carDB {
	public void connectToAndQueryDatabase(Car car) {
		
		String url = "";
        String username = "root";
        String password = "password";
				
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,username ,password);
			Statement stmt = con.createStatement();

            
			String sql = "INSERT INTO cars (plate, brand, model, year) VALUES ('"
			        + car.getPlate() + "', '"
			        + car.getBrand() + "', '"
			        + car.getModel() + "', "
			        + car.getYear() + ")";

            stmt.executeUpdate(sql);
            
            stmt.close();
            con.close();
            
			
		} catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database error occurred.");
        }
    }
}


