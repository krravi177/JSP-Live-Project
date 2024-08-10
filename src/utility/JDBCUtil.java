package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	private static Connection con;
	static {
		try {
			String url = "jdbc:postgresql://localhost:5432/MYDB";
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, "postgres", "tiger");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnnn() {
		return con;
	}
}
