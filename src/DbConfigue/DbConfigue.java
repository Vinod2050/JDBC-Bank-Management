package DbConfigue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfigue {

public static Connection getConnection() throws ClassNotFoundException, SQLException {

	// step 1 : Load Driver class
	Class.forName("com.mysql.cj.jdbc.Driver");

	// step 2: Establish Database Connection
	String url = "jdbc:mysql://localhost:3306/Sbi";
	String username = "root";
	String password = "Root@1996";

	Connection con = DriverManager.getConnection(url, username, password);

	return con;
}
}