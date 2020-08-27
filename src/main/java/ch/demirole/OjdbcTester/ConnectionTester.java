package ch.demirole.OjdbcTester;

import java.sql.*;
import java.util.Properties;

public class ConnectionTester {

	public void invoke(String connectionUrl, Properties connectionProperties, String testStatement) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =
				DriverManager.getConnection(connectionUrl, connectionProperties);
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		ResultSet rset =
				stmt.executeQuery(testStatement);
		while (rset.next()) {
			System.out.println (rset.getString(1));
		}
		stmt.close();

		System.out.println ("Success!");
	}
}
