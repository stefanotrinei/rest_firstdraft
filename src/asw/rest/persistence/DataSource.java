package asw.rest.persistence;

import java.sql.*;

import javax.persistence.PersistenceException;

public class DataSource {
	private String dbURI = "jdbc:postgresql://localhost/asw";
	private String userName = "postgres1";
	private String password = "postgres1";

	public Connection getConnection() throws PersistenceException {
		Connection connection;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}