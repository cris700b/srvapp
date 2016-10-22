package it.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Birzu
 *
 */
public class DbConnectionManager {

	private Connection con;
	
	/**
	 * 
	 * @param strDbUrl
	 * @param strUser
	 * @param strPass
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public	DbConnectionManager(String strDbUrl, String strUser, String strPass) throws ClassNotFoundException, SQLException {
		
		// create db connection here
		Class.forName("com.mysql.jdbc.Driver");
		this.con = DriverManager.getConnection(strDbUrl, strUser, strPass);
	}
	
	/**
	 * 
	 * @return
	 */
	public Connection getConnection() {
		
		return this.con;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public void closeConnection() throws SQLException {
		
		// close db connection here
		if(null != this.con && !this.con.isClosed()){
			
			this.con.close();
		}
	}
}
