package ass.data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection 
{
	private Connection conn;
	
	public void start() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sd", "root", "");
			//System.out.print("Database is connected !");
		} catch (SQLException e) {
			System.out.println("Connection not stablished:\n " + e);
		}
	}
	public void close() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn.close();
			//System.out.print("Database is closed !");
		} catch (SQLException e) {
			System.out.println("Connection not closed:\n " + e);
		}
	}
	public Connection getConn() {
		return conn;
	}
	
	
}
